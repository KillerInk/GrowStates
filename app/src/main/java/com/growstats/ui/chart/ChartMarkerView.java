package com.growstats.ui.chart;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.growstats.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChartMarkerView extends MarkerView {

    private final SimpleDateFormat mFormat = new SimpleDateFormat("dd-MM HH:mm", Locale.ENGLISH);
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */

    private TextView textView;
    int color;
    public ChartMarkerView(Context context, int layoutResource, int color) {
        super(context, layoutResource);
        textView = findViewById(R.id.textview_value);
        textView.setTextColor(color);
        textView.setBackgroundColor(Color.BLACK);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int val = (int) e.getY();
        textView.setText(mFormat.format(new Date((long) e.getX()))+ "\n     "+val);
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth()/2),-(getHeight()*3));
    }
}
