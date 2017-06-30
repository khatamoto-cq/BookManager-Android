package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.BookListContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BookListPresenter extends BasePresenter<BookListContract.View> implements BookListContract.Action {

    Scheduler scheduler;
    BookRepository bookRepository;

    public BookListPresenter(Scheduler scheduler, BookRepository bookRepository) {
        this.scheduler = scheduler;
        this.bookRepository = bookRepository;
    }

    @Override
    public void load() {
        getView().showProgress();

        bookRepository.getBooks(getView().getLoginUserId(), "0-200")
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull BookResult bookResult) {
                        getView().set(bookResult.getBooks());
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
