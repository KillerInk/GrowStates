package com.growstats.ui.controller;

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

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.growstats.R;
import com.growstats.controller.NavigationController;
import com.growstats.databinding.FragmentGrowControllerBinding;
import com.growstats.databinding.FragmentGrowControllerChartBinding;
import com.growstats.ui.chart.ChartMarkerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GrowControllerChartFragment extends Fragment {

    private GrowControllerChartViewModel mViewModel;
    private FragmentGrowControllerChartBinding binding;

    public static GrowControllerChartFragment newInstance() {
        return new GrowControllerChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GrowControllerChartViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grow_controller_chart, container, false);
        createChart(binding.chartGrowcontroller,Color.LTGRAY);
        mViewModel.chartData.observe(getViewLifecycleOwner(), chartObserver);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    private void createChart(CombinedChart chart, int color)
    {
        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);


        // set an alternative background color
        chart.setBackgroundColor(Color.BLACK);
        //chart.setViewPortOffsets(0f, 0f, 0f, 0f);
        // get the legend (only possible after setting data)
        Legend ll = chart.getLegend();
        ll.setEnabled(true);
        ll.setTextSize(10f);
        ll.setFormSize(10f);
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

        XAxis xAxis = chart.getXAxis();
        //xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));

        xAxis.setGranularity(6f); // one hour
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(5,true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd-MM HH:mm", Locale.ENGLISH);
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mFormat.format(new Date((long) value));
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        //leftAxis.setGranularityEnabled(false);
        //leftAxis.setAxisMinimum(0f);
        //leftAxis.setAxisMaximum(170f);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setTextSize(10f);
        leftAxis.setYOffset(-9f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        ChartMarkerView chartMarkerView = new ChartMarkerView(getContext(),R.layout.chartmarkerview,color);
        // create marker to display box when values are selected

        // Set the marker to the chart
        chartMarkerView.setChartView(chart);
        chart.setMarker(chartMarkerView);
    }

    final Observer<CombinedData> chartObserver = new Observer<CombinedData>() {
        @Override
        public void onChanged(CombinedData combinedChart) {
            binding.chartGrowcontroller.setData(combinedChart);
            binding.chartGrowcontroller.invalidate();
        }
    };


}