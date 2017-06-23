package com.caraquri.hatamoto.bookmanager.presentation.validator;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;

import java.util.ArrayList;
import java.util.List;

public final class LoginValidator {

    private static LoginValidator singleton = new LoginValidator();

    private static LoginContract.View view;

    public static LoginValidator getInstance(LoginContract.View mvpView) {
        view = mvpView;
        return singleton;
    }

    public List<String> validate(String email, String password) {
        List errors = new ArrayList<String>();

        if (email.isEmpty()) {
            errors.add(view.getErrorMessage(R.string.validation_email_require));
        } else {
            String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
            if (!email.matches(regex)) {
                errors.add(view.getErrorMessage(R.string.validation_email_invalid));
            }
        }

        if (password.isEmpty()) {
            errors.add(view.getErrorMessage(R.string.validation_password_require));
        }

        return errors;
    }
}
