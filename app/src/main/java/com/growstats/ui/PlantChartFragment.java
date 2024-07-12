package com.growstats.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.growstats.R;
import com.growstats.databinding.FragmentPlantChartBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlantChartFragment extends Fragment {

    private PlantChartViewModel mViewModel;
    private FragmentPlantChartBinding fragmentBinding;
    public int id;

    public static PlantChartFragment newInstance() {
        return new PlantChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PlantChartViewModel.class);
        fragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_plant_chart, container, false);
        // Create the observer which updates the UI.
        final Observer<LineData> nameObserver = new Observer<LineData>() {
            @Override
            public void onChanged(LineData lineData) {
                YAxis leftAxis = fragmentBinding.chart2.getAxisLeft();
                leftAxis.setAxisMinimum(8);
                leftAxis.setAxisMaximum(38);
                fragmentBinding.chart2.setData(lineData);
                //fragmentBinding.chart2.bringToFront();
                fragmentBinding.chart2.invalidate();
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.soil_moistureData.observe(getViewLifecycleOwner(), nameObserver);

        createChart();
        return fragmentBinding.getRoot();
    }

   private void createChart()
   {
       // no description text
       fragmentBinding.chart2.getDescription().setEnabled(false);

       // enable touch gestures
       fragmentBinding.chart2.setTouchEnabled(true);

       fragmentBinding.chart2.setDragDecelerationFrictionCoef(0.9f);

       // enable scaling and dragging
       fragmentBinding.chart2.setDragEnabled(true);
       fragmentBinding.chart2.setScaleEnabled(true);
       fragmentBinding.chart2.setDrawGridBackground(false);
       fragmentBinding.chart2.setHighlightPerDragEnabled(true);

       // set an alternative background color
       fragmentBinding.chart2.setBackgroundColor(Color.BLACK);
       //fragmentBinding.chart2.setViewPortOffsets(0f, 0f, 0f, 0f);
       // get the legend (only possible after setting data)
       Legend ll = fragmentBinding.chart2.getLegend();
       ll.setEnabled(true);
       ll.setTextSize(7f);
       ll.setFormSize(7f);
       ll.setFormToTextSpace(1f);
       ll.setForm(Legend.LegendForm.CIRCLE);
       ll.setWordWrapEnabled(true);
       ll.setDrawInside(false);
       ll.setYOffset(3f);
       //ll.setXOffset(35f);
       //ll.setMaxSizePercent(0);
       ll.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT );

       ll.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
       ll.setTextColor(Color.WHITE);

       XAxis xAxis = fragmentBinding.chart2.getXAxis();
       //xAxis.setPosition(XAxis.XAxisPosition.TOP);
       //xAxis.setAvoidFirstLastClipping(true);
       xAxis.setTextSize(5f);
       xAxis.setTextColor(Color.WHITE);
       xAxis.setCenterAxisLabels(true);
       //xAxis.setDrawAxisLine(true);
       //xAxis.setDrawGridLines(true);
       xAxis.setTextColor(Color.rgb(255, 192, 56));
       //xAxis.setCenterAxisLabels(true);
       //xAxis.setGranularity(0.5f); // one hour
       xAxis.setValueFormatter(new IAxisValueFormatter() {
           private final SimpleDateFormat mFormat = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
           @Override
           public String getFormattedValue(float value, AxisBase axis) {
               return mFormat.format(new Date((long) value *1000));
           }
       });

       YAxis leftAxis = fragmentBinding.chart2.getAxisLeft();
       //leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
       //leftAxis.setTypeface(tfLight);
       leftAxis.setTextColor(ColorTemplate.getHoloBlue());
       leftAxis.setDrawAxisLine(true);
       //leftAxis.setDrawGridLines(false);
       //leftAxis.setGranularityEnabled(false);
       leftAxis.setTextColor(Color.WHITE);
       leftAxis.setTextSize(5f);
       leftAxis.setAxisMinimum(0f);
       leftAxis.setAxisMaximum(170f);
       leftAxis.setYOffset(-9f);
       leftAxis.setTextColor(Color.rgb(255, 192, 56));

       YAxis rightAxis = fragmentBinding.chart2.getAxisRight();
       rightAxis.setEnabled(false);
   }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume(id);
    }
}