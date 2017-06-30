package com.caraquri.hatamoto.bookmanager.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Base64;

import com.caraquri.hatamoto.bookmanager.presentation.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

import timber.log.Timber;

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

    public static String getBase64EncordedImage(BitmapDrawable bitmapDrawable) {
        if (bitmapDrawable == null) {
            return "";
        }

        ByteArrayOutputStream byteArray = null;

        try {
            byteArray = new ByteArrayOutputStream();
            byte[] bytes = byteArray.toByteArray();

            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (RuntimeException e) {
            Timber.e(e.getMessage());
        } finally {
            if (byteArray != null) {
                try {
                    byteArray.close();
                } catch (IOException e) {
                    Timber.e(e.getMessage());
                }
            }
        }

        return "";
    }
}
