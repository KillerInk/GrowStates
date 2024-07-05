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
        switch (status)
        {
            case noData:
                view.setText("No Data");
                view.setTextColor(Color.WHITE);
                break;
            case tooLow:
                view.setText("Too Low");
                view.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.RED);
                break;
            case low:
                view.setText("Low");
                view.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.YELLOW);
                break;
            case perfect:
                view.setText("Perfect");
                view.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.GREEN);
                break;
            case high:
                view.setText("High");
                view.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.YELLOW);
                break;
            case tooHigh:
                view.setText("Too High");
                view.setTextColor(Color.BLACK);
                view.setBackgroundColor(Color.RED);
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
