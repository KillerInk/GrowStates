package com.growstats.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.growstats.GrowStatsApplication;
import com.growstats.controller.FytaController;
import com.growstats.hilt.ApiEntryPoint;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

import dagger.hilt.android.EntryPointAccessors;
import okhttp3.ResponseBody;

public class ImageCache {
    private final String TAG = ImageCache.class.getSimpleName();
    private WeakReference<ImageView> weakReference;
    private String name;
    FytaController fytaController;

    public ImageCache(ImageView imageView, String plant) {
        weakReference = new WeakReference(imageView);
        this.name = plant;
        Context appContext = imageView.getContext().getApplicationContext();
        ApiEntryPoint hiltEntryPoint = EntryPointAccessors.fromApplication(appContext, ApiEntryPoint.class);
        fytaController = hiltEntryPoint.fytaController();

    }

    public void loadBitmap(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmap(url);
                ImageView imageView = weakReference.get();
                if (imageView != null)
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
            }
        }).start();
    }

    private Bitmap getBitmap(String ur) {
        ResponseBody b = fytaController.getRestClient().imageResponse(ur);
        InputStream inputStream = b.byteStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
        return bitmap;
    }

}
