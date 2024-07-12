package com.growstats.ui;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.objects.plantstats.Measurement;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.controller.FytaController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlantChartViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private LineData temperatureData;
    private LineData lightData;
    private LineData soil_fertilityData;
    public MutableLiveData<LineData> soil_moistureData = new MutableLiveData<>();
    private FytaController fytaController;

    @Inject
    public PlantChartViewModel(FytaController fytaController)
    {
        this.fytaController = fytaController;
    }

    public void getPlantStats(int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetPlantStats stats = fytaController.getRestClient().getPlantStats(id, TimeRange.day);
                List<Entry> dataSets = new ArrayList<>();
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                for(Measurement m :stats.measurements)
                {
                    try {
                        Date d = mFormat.parse(m.date_utc);
                        float t = d.getTime()/1000;
                        Entry e = new Entry(t,m.soil_moisture);
                        dataSets.add(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                LineDataSet set1 = new LineDataSet(dataSets,"Moisture");
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setValueTextColor(ColorTemplate.getHoloBlue());
                set1.setLineWidth(1f);
                set1.setDrawCircles(false);
                set1.setDrawValues(false);
                set1.setFillAlpha(85);
                set1.setFillColor(ColorTemplate.getHoloBlue());
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(false);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        soil_moistureData.setValue(new LineData(set1));
                    }
                });

            }
        }).start();
    }

    public void onResume(int id) {
        getPlantStats(id);
    }
}