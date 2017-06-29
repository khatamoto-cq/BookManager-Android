package com.caraquri.hatamoto.bookmanager.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.caraquri.hatamoto.bookmanager.presentation.BaseActivity;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageUtils {
    public static Bitmap getBitmapFromUri(BaseActivity activity, Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = null;

        try {
            parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            return BitmapFactory.decodeFileDescriptor(fileDescriptor);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
}
