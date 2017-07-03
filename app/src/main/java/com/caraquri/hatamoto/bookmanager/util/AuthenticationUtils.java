package com.caraquri.hatamoto.bookmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.caraquri.hatamoto.bookmanager.R;

public class AuthenticationUtils {

    public static String getRequestToken(Context context) {
        SharedPreferences sharedPreferences  = context.getSharedPreferences(
                context.getString(R.string.shared_prefference), Context.MODE_PRIVATE);

        return sharedPreferences.getString(context.getString(R.string.request_token), "");
    }


    public static int getLoginUserId(Context context) {
        SharedPreferences sharedPreferences  = context.getSharedPreferences(
                context.getString(R.string.shared_prefference), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(context.getString(R.string.user_id), 0);
    }
}
