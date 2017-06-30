package com.caraquri.hatamoto.bookmanager.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;
import com.caraquri.hatamoto.bookmanager.util.BookActivityUtils;
import com.caraquri.hatamoto.bookmanager.util.DateUtils;
import com.caraquri.hatamoto.bookmanager.util.ImageUtils;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class EditBookActivity extends BaseActivity implements RegisterBookContract.View {

    private static final String EXTRA_BOOK = "EditBookActivity.EXTRA_BOOK";

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
    EditBookPresenter editBookPresenter;

    private Book book;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        editBookPresenter.attachView(this);
        toolbar.setTitle(R.string.title_edit_book);
        BookActivityUtils.buildToolbar(this, toolbar);

        book = getIntent().getParcelableExtra(EXTRA_BOOK);
        initControls(book);

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
        editBookPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_book;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            if (intent == null) {
                return;
            }

            runOnUiThread(() -> {
                try {
                    imageView.setImageBitmap(ImageUtils.getBitmapFromUri(this, intent.getData()));
                } catch (IOException e) {
                    Timber.d(e.getMessage());
                }
            });
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
                editBookPresenter.backScreen();
                return true;
            case R.id.action_save:
                onSaveButtonClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public String getBase64EncordedImage() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        return ImageUtils.getBase64EncordedImage(bitmapDrawable);
    }

    private void onSaveButtonClick() {
        book.setName(nameEditTest.getText().toString());
        if (!TextUtils.isEmpty(priceEditText.getText().toString())) {
            book.setPrice(Integer.parseInt(priceEditText.getText().toString()));
        }
        if (!TextUtils.isEmpty(purchaseDateEditText.getText().toString())) {
            book.setPurchaseDate(DateUtils.getFormatedDate(purchaseDateEditText.getText().toString()));
        }

        editBookPresenter.save(book);
    }

    private void initControls(Book book) {
        if (!TextUtils.isEmpty(book.getImageUrl())) {
            Glide.with(this).load(book.getImageUrl()).into(imageView);
        }
        nameEditTest.setText(book.getName());
        if (book.getPrice() > 0) {
            priceEditText.setText(String.valueOf(book.getPrice()));
        }
        if (!TextUtils.isEmpty(book.getPurchaseDate())) {
            purchaseDateEditText.setText(DateUtils.getFormatedDate(book.getPurchaseDate()));
        }
    }

    public static Intent createIntent(Context context, Book book) {
        Intent intent = new Intent(context, EditBookActivity.class);
        return intent.putExtra(EXTRA_BOOK, book);
    }
}
