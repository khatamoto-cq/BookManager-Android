package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.BookValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import timber.log.Timber;

public class EditBookPresenter extends BasePresenter<RegisterBookContract.View> implements RegisterBookContract.Action {

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
        BookValidator validator = BookValidator.getInstance(getView());
        List<String> errors = validator.validate(book);
        if (!errors.isEmpty()) {
            getView().showError(TextUtils.join("\n", errors));
            return;
        }

        // TODO: API実装時の登録処理
        Timber.d("[Log] 書籍を編集しました。");

        getView().moveMainActivity();
    }
}