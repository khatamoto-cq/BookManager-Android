package com.caraquri.hatamoto.bookmanager.presentation.validator;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;

import java.util.ArrayList;
import java.util.List;

public final class BookValidator {

    private RegisterBookContract.View view;

    public BookValidator(RegisterBookContract.View mvpView){
        view = mvpView;
    }

    public List<String> validate(Book book) {
        List errors = new ArrayList<String>();

        if (book.getName().isEmpty()) {
            errors.add(view.getErrorMessage(R.string.validation_book_name_require));
        }
        if (book.getPrice() == 0) {
            errors.add(view.getErrorMessage(R.string.validation_book_price_require));
        }
        if (!book.getPurchaseDate().isEmpty() && !book.getPurchaseDate().matches("^[0-9]{4}/[01]?[0-9]/[0123]?[0-9]$")) {
            errors.add(view.getErrorMessage(R.string.validation_book_purchase_date_invalid));
        }

        return errors;
    }
}
