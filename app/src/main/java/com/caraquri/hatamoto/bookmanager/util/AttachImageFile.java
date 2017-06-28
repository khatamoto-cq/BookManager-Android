package com.caraquri.hatamoto.bookmanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;

import timber.log.Timber;

public class AttachImageFile implements Runnable {
    private Context context;
    private ImageView imageView;
    private Uri uri;

    public AttachImageFile(Context context, ImageView imageView, Uri uri) {
        this.context = context;
        this.imageView = imageView;
        this.uri = uri;
    }

    @Override
    public void run() {
        ParcelFileDescriptor parcelFileDescriptor = null;

        try {
            parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            imageView.setImageBitmap(image);
        } catch (IOException e) {
            Timber.e(e.getMessage());
        } finally {
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
