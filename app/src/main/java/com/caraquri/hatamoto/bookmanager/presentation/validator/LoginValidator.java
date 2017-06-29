package com.caraquri.hatamoto.bookmanager.presentation.validator;

import android.content.Context;
import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.util.Const;

import java.util.ArrayList;
import java.util.List;

public final class LoginValidator {

    public static List<String> validate(Context context, String email, String password) {
        List errors = new ArrayList<String>();

        if (TextUtils.isEmpty(email)) {
            errors.add(context.getString(R.string.validation_email_require));
        } else if (!email.matches(Const.EMAIL_REGEX)) {
            errors.add(context.getString(R.string.validation_email_invalid));
        }

        if (TextUtils.isEmpty(password)) {
            errors.add(context.getString(R.string.validation_password_require));
        }

        return errors;
    }
}
