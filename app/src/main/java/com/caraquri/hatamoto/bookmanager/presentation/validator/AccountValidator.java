package com.caraquri.hatamoto.bookmanager.presentation.validator;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;

import java.util.ArrayList;
import java.util.List;

public final class AccountValidator {

    private AccountSettingContract.View view;

    public AccountValidator(AccountSettingContract.View mvpView){
        view = mvpView;
    }

    public List<String> validate(String email, String password, String passwordConfirm) {
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

        if (passwordConfirm.isEmpty()) {
            errors.add(view.getErrorMessage(R.string.validation_password_confirm_require));
        }

        if (!password.isEmpty() && !passwordConfirm.isEmpty() && !password.equals(passwordConfirm)) {
            errors.add(view.getErrorMessage(R.string.validation_password_compare));
        }

        return errors;
    }
}
