package com.growstats.ui;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.growstats.api.fyta.enums.MeasurementStatus;

public class CustomBinding {
    @BindingAdapter("setEnumToTextView")
    public static void setEnumToTextView(TextView view, MeasurementStatus status)
    {
        if(view == null || status == null)
            return;
        switch (status)
        {
            case noData:
                view.setTextColor(Color.WHITE);
                break;
            case tooLow:
                view.setTextColor(Color.RED);
                break;
            case low:
                view.setTextColor(Color.YELLOW);
                break;
            case perfect:
                view.setTextColor(Color.GREEN);
                break;
            case high:
                view.setTextColor(Color.YELLOW);
                break;
            case tooHigh:
                view.setTextColor(Color.RED);
                break;
        }
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String assetname, String url) {
        if(view.getTag() == null || !view.getTag().equals(assetname)) {
            view.setImageBitmap(null);
            view.setTag(assetname);
            new ImageCache(view, assetname).loadBitmap(url);
        }
    }
}
