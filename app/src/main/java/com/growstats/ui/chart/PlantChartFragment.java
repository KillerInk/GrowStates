package com.growstats.ui.chart;

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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.growstats.R;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.databinding.FragmentPlantChartBinding;
import com.growstats.generated.callback.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlantChartFragment extends Fragment {

    private PlantChartViewModel mViewModel;
    private FragmentPlantChartBinding fragmentBinding;
    private int id;
    private String name;

    public static PlantChartFragment newInstance() {
        return new PlantChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PlantChartViewModel.class);
        fragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_plant_chart, container, false);
        // Create the observer which updates the UI.
        fragmentBinding.textViewPlantName.setText(name);
        fragmentBinding.textViewPlantName.setTextColor(Color.GREEN);

        fragmentBinding.buttonDayly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getPlantStats(id,TimeRange.day);
            }
        });
        fragmentBinding.buttonWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getPlantStats(id,TimeRange.week);
            }
        });
        fragmentBinding.buttonMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getPlantStats(id,TimeRange.month);
            }
        });

        final Observer<ArrayList<ILineDataSet>> nameObserver = new Observer<ArrayList<ILineDataSet>>() {
            @Override
            public void onChanged(ArrayList<ILineDataSet> data) {
                YAxis leftAxis = fragmentBinding.chartMoisture.getAxisLeft();
                //fragmentBinding.chartMoisture.setMaxVisibleValueCount(15);
                leftAxis.setAxisMinimum(0);
                leftAxis.setAxisMaximum(100);
                LineData m = new LineData(data.get(0));
                addLimitLine(leftAxis,mViewModel.getMinGoodMoisture,mViewModel.getMaxGoodMoisture,mViewModel.getMinAcceptMoisture,mViewModel.getMaxAcceptMoisture);
                fragmentBinding.chartMoisture.setData(m);
                fragmentBinding.chartMoisture.invalidate();

                //fragmentBinding.chartTemperature.setMaxVisibleValueCount(15);
                leftAxis = fragmentBinding.chartTemperature.getAxisLeft();
                leftAxis.setAxisMinimum(0);
                leftAxis.setAxisMaximum(50);
                addLimitLine(leftAxis,mViewModel.getMinGoodTemperature,mViewModel.getMaxGoodTemperature, mViewModel.getMinAcceptTemperature, mViewModel.getMaxAcceptTemperature);
                LineData t = new LineData(data.get(1));
                fragmentBinding.chartTemperature.setData(t);
                fragmentBinding.chartTemperature.invalidate();


                //fragmentBinding.chartLight.setMaxVisibleValueCount(15);
                leftAxis = fragmentBinding.chartLight.getAxisLeft();
                leftAxis.setAxisMinimum(0);
                leftAxis.setAxisMaximum(1400);
                addLimitLine(leftAxis,mViewModel.getMinGoodLight,mViewModel.getMaxGoodLight, mViewModel.getMinAcceptLight, mViewModel.getMaxAcceptLight);
                LineData tt = new LineData(data.get(2));
                fragmentBinding.chartLight.setData(tt);
                fragmentBinding.chartLight.invalidate();
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.soil_moistureData.observe(getViewLifecycleOwner(), nameObserver);

        createChart(fragmentBinding.chartMoisture,Color.WHITE);
        createChart(fragmentBinding.chartTemperature,Color.WHITE);
        createChart(fragmentBinding.chartLight,Color.WHITE);
        return fragmentBinding.getRoot();
    }

    private void addLimitLine(YAxis yAxis, int minGood,int maxGood, int minAccept,int maxAccept)
    {
        yAxis.addLimitLine(createLimitLine(minGood,Color.YELLOW));
        yAxis.addLimitLine(createLimitLine(maxGood,Color.YELLOW));
        yAxis.addLimitLine(createLimitLine(maxAccept,Color.RED));
        yAxis.addLimitLine(createLimitLine(minAccept,Color.RED));
    }

    private LimitLine createLimitLine(int val,int color)
    {
        LimitLine limitLineMax = new LimitLine(val,""+val);
        limitLineMax.setLineColor(color);
        limitLineMax.setLineWidth(0.5f);
        limitLineMax.setTextColor(Color.WHITE);
        limitLineMax.enableDashedLine(5f,8f,0);
        return limitLineMax;
    }

   private void createChart(LineChart chart, int color)
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

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume(id);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}