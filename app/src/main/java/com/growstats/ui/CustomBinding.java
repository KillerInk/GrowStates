package com.growstats.ui;

import android.graphics.Color;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.growstats.R;
import com.growstats.api.fyta.enums.MeasurementStatus;
import com.growstats.controller.BtClient;

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
            case tooHigh:
                view.setTextColor(view.getResources().getColor(R.color.dark_red));
                break;
            case low:
            case high:
                view.setTextColor(Color.YELLOW);
                break;
            case perfect:
                view.setTextColor(Color.GREEN);
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

    @BindingAdapter("setEnumToImageButton")
    public static void setEnumToImageButton(ImageButton view, BtClient.BtClientState status)
    {
        if (view != null && status != null)
        {
            switch (status) {
                case disconnected:
                    view.setBackgroundColor(Color.RED);
                    view.setImageResource(android.R.drawable.ic_media_play);
                    break;
                case disconneting:
                    view.setBackgroundColor(Color.RED);
                    view.setImageResource(android.R.drawable.ic_media_play);
                    break;
                case connecting:
                    view.setBackgroundColor(Color.YELLOW);
                    break;
                case connected:
                    view.setBackgroundColor(Color.GREEN);
                    break;
                case livemode:
                    view.setImageResource(android.R.drawable.ic_media_pause);
                    break;
            }
        }
    }

}
