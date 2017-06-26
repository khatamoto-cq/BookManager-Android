package com.caraquri.hatamoto.bookmanager.presentation.validator;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;
import com.caraquri.hatamoto.bookmanager.util.Const;

import java.util.ArrayList;
import java.util.List;

public final class BookValidator {

    private RegisterBookContract.View view;

    public BookValidator(RegisterBookContract.View mvpView){
        view = mvpView;
    }

    public List<String> validate(Book book) {
        List errors = new ArrayList<String>();

        if (TextUtils.isEmpty(book.getName())) {
            errors.add(view.getResourceString(R.string.validation_book_name_require));
        }
        if (book.getPrice() == 0) {
            errors.add(view.getResourceString(R.string.validation_book_price_require));
        }
        if (!TextUtils.isEmpty(book.getPurchaseDate()) && !book.getPurchaseDate().matches(Const.dateRegex)) {
            errors.add(view.getResourceString(R.string.validation_book_purchase_date_invalid));
        }

        return errors;
    }
}
