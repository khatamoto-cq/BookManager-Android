package com.caraquri.hatamoto.bookmanager.presentation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.util.AttachImageFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;

public abstract class AbstractBookActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.name_edit_text)
    EditText nameEditTest;
    @BindView(R.id.price_edit_text)
    EditText priceEditText;
    @BindView(R.id.purchase_date_edit_text)
    EditText purchaseDateEditText;
    @BindView(R.id.attach_button)
    Button attachButton;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);

        toolbar.inflateMenu(R.menu.menu_save);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        attachButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
        });

        purchaseDateEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                showDatePicker(calendar, (datePicker, year, month, day) -> {
                    updatePurchaseDate(calendar, year, month, day);
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            if (intent == null) {
                return;
            }

            Uri uri = intent.getData();
            new AttachImageFile(this, imageView, uri).run();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackButtonClick();
                return true;
            case R.id.action_save:
                onSaveButtonClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract void onBackButtonClick();

    protected abstract void onSaveButtonClick();

    protected void showDatePicker(Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(this, listener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    protected void updatePurchaseDate(Calendar calendar, int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        purchaseDateEditText.setText(sdf.format(calendar.getTime()));
    }
}
