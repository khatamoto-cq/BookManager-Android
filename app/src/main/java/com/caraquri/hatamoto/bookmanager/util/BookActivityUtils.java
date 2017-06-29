package com.caraquri.hatamoto.bookmanager.util;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.caraquri.hatamoto.bookmanager.presentation.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookActivityUtils {

    public static void buildToolbar(BaseActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
    }

    public static void showDatePicker(BaseActivity activity,  Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(activity, listener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static String getPurchaseDate(Calendar calendar, int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return sdf.format(calendar.getTime());
    }

    public static void showGallary(BaseActivity activity) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(intent, BaseActivity.RESULT_PICK_IMAGEFILE);
    }
}
