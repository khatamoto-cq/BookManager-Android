package com.caraquri.hatamoto.bookmanager.presentation.validator;

import android.content.Context;
import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.util.Const;

import java.util.ArrayList;
import java.util.List;

public final class AccountValidator {

    private Context context;

    public AccountValidator(Context context) {
        this.context = context;
    }

    public List<String> validate(String email, String password, String passwordConfirm) {
        List errors = new ArrayList<String>();

        if (TextUtils.isEmpty(email)) {
            errors.add(context.getString(R.string.validation_email_require));
        } else {
            if (!email.matches(Const.emailRegex)) {
                errors.add(context.getString(R.string.validation_email_invalid));
            }
        }

        if (TextUtils.isEmpty(password)) {
            errors.add(context.getString(R.string.validation_password_require));
        }

        if (TextUtils.isEmpty(password)) {
            errors.add(context.getString(R.string.validation_password_confirm_require));
        }

        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConfirm) && !password.equals(passwordConfirm)) {
            errors.add(context.getString(R.string.validation_password_compare));
        }

        return errors;
    }
}
