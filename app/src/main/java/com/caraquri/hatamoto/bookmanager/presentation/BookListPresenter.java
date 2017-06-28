package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.BookListContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class BookListPresenter extends BasePresenter<BookListContract.View> implements BookListContract.Action {

    @Inject
    Scheduler scheduler;

    @Inject
    BookRepository bookRepository;

    public BookListPresenter(Scheduler scheduler, BookRepository bookRepository) {
        this.scheduler = scheduler;
        this.bookRepository = bookRepository;
    }

    @Override
    public void load() {
        getView().showProgress();

        bookRepository.getBooks(1, "0-200")
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Book> books) {
                        getView().set(books);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().hideProgress();
                        getView().showDialog(getView().getContext().getString(R.string.error_title), e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideProgress();
                    }
                });
    }
}
