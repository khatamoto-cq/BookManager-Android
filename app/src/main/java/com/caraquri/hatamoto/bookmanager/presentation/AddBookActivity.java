package com.caraquri.hatamoto.bookmanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;
import com.caraquri.hatamoto.bookmanager.util.AttachImageFile;
import com.caraquri.hatamoto.bookmanager.util.BookActivityUtils;

import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;

public class AddBookActivity extends BaseActivity implements RegisterBookContract.View {

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

    @Inject
    AddBookPresenter addBookPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        addBookPresenter.attachView(this);
        toolbar.setTitle(R.string.title_add_book);

        BookActivityUtils.buildToolbar(this, toolbar);

        attachButton.setOnClickListener(view -> {
            BookActivityUtils.showGallary(this);
        });

        purchaseDateEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                BookActivityUtils.showDatePicker(this, calendar, (datePicker, year, month, day) -> {
                    String dateString = BookActivityUtils.getPurchaseDate(calendar, year, month, day);
                    purchaseDateEditText.setText(dateString);
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        addBookPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_book;
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
                addBookPresenter.backScreen();
                return true;
            case R.id.action_save:
                onSaveButtonClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSaveButtonClick() {
        String name = nameEditTest.getText().toString();
        int price = 0;
        if (!TextUtils.isEmpty(priceEditText.getText().toString())) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }
        String purchaseDate = purchaseDateEditText.getText().toString();

        addBookPresenter.save(new Book(name, price, purchaseDate));
    }
}
