package com.caraquri.hatamoto.bookmanager.presentation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.caraquri.hatamoto.bookmanager.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import timber.log.Timber;

public abstract class AbstractBookActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.nameEditText)
    EditText nameEditTest;
    @BindView(R.id.priceEditText)
    EditText priceEditText;
    @BindView(R.id.purchaseDateEditText)
    EditText purchaseDateEditText;
    @BindView(R.id.attachButton)
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
                showDatePicker(getBookActivity(), purchaseDateEditText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                tapBackButton();
                break;
            case R.id.action_save:
                tapSaveButton();
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void tapBackButton();
    protected abstract void tapSaveButton();
    protected abstract AbstractBookActivity getBookActivity();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            if (intent == null) {
                return;
            }

            Uri uri = intent.getData();
            try {
                Bitmap bmp = getBitmapFromUri(uri);
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                showDialog(getString(R.string.error_title), getString(R.string.error_read_file));
                Timber.e(e.getMessage());
            }
        }
    }

    protected Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = null;
        Bitmap image = null;

        try {
            parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        } catch (FileNotFoundException e) {
            showDialog(getString(R.string.error_title), getString(R.string.error_file_not_found));
            Timber.e(e.getMessage());
        } finally {
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
        }

        return image;
    }

    protected void showDatePicker(Context context, EditText sourceEditText) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        new DatePickerDialog(context, (datePicker, year, month, day) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
            sourceEditText.setText(sdf.format(cal.getTime()));
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void moveMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_LOAD_FRAGMENT, MainActivity.BOOK_LIST_FRAGMENT);
        startActivity(intent);
        finish();
    }

    public void showError(String message) {
        showDialog(getString(R.string.validation_title), message);
    }

    public String getErrorMessage(int resource) {
        return getResources().getString(resource);
    }
}