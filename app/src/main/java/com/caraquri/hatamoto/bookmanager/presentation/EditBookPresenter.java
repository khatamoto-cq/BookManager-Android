package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBook;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.BookValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class EditBookPresenter extends BasePresenter<RegisterBookContract.View> implements RegisterBookContract.Action {

    @Inject
    Scheduler scheduler;
    @Inject
    BookRepository bookRepository;

    public EditBookPresenter(Scheduler scheduler, BookRepository bookRepository) {
        this.scheduler = scheduler;
        this.bookRepository = bookRepository;
    }

    @Override
    public void backScreen() {
        getView().finish();
    }

    @Override
    public void save(Book book) {
        List<String> errors = BookValidator.validate(getView().getContext(), book);
        if (!errors.isEmpty()) {
            getView().showDialog(getView().getContext().getString(R.string.validation_title), TextUtils.join("\n", errors));
            return;
        }

        EditBook editBook = new EditBook(book);
        editBook.setImageData(getView().getBase64EncordedImage());

        bookRepository.editBook(editBook)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BookResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@NonNull BookResponse bookResponse) {
                        getView().finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e.getMessage());
                        getView().showDialog(getView().getContext().getString(R.string.error_title),
                                getView().getContext().getString(R.string.error_networking));
                    }
                });
    }
}
