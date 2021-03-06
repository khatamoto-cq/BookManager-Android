package com.caraquri.hatamoto.bookmanager.presentation.validator;

import android.content.Context;
import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.Const;

import java.util.ArrayList;
import java.util.List;

public final class BookValidator {

    public static List<String> validate(Context context, Book book) {
        List errors = new ArrayList<String>();

        if (TextUtils.isEmpty(book.getName())) {
            errors.add(context.getString(R.string.validation_book_name_require));
        }
        if (book.getPrice() == 0) {
            errors.add(context.getString(R.string.validation_book_price_require));
        }
        if (!TextUtils.isEmpty(book.getPurchaseDate()) && !book.getPurchaseDate().matches(Const.DATE_REGEX)) {
            errors.add(context.getString(R.string.validation_book_purchase_date_invalid));
        }

        return errors;
    }
}
