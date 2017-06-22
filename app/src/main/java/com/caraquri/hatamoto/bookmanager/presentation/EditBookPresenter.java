package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.EditBookContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import timber.log.Timber;

public class EditBookPresenter extends BasePresenter<EditBookContract.View> implements EditBookContract.Action {

    @Inject
    Scheduler scheduler;

    public EditBookPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void backScreen() {
        getView().moveMainActivity();
    }

    @Override
    public void save(Book book) {
        List<String> errors = validate(book);
        if (!errors.isEmpty()) {
            getView().showError(TextUtils.join("\n", errors));
            return;
        }

        // TODO: API実装時の登録処理

        Timber.d("[Log] 書籍を編集しました。");
        getView().moveMainActivity();
    }

    private List<String> validate(Book book) {
        List errors = new ArrayList<String>();

        if (book.getName().isEmpty()) {
            errors.add(getView().getErrorMessage(R.string.validation_book_name_require));
        }
        if (book.getPrice() == 0) {
            errors.add(getView().getErrorMessage(R.string.validation_book_price_require));
        }
        if (!book.getPurchaseDate().isEmpty() && !book.getPurchaseDate().matches("^[0-9]{4}/[01]?[0-9]/[0123]?[0-9]$")) {
            errors.add(getView().getErrorMessage(R.string.validation_book_purchase_date_invalid));
        }

        return errors;
    }
}