package com.growstats.ui.chart;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.objects.plantstats.Measurement;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.controller.FytaController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlantChartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public MutableLiveData<ArrayList<ILineDataSet>> soil_moistureData = new MutableLiveData<>();
    private FytaController fytaController;
    public int getMaxGoodMoisture;
    public int getMinGoodMoisture;
    public int getMaxGoodTemperature;
    public int getMinGoodTemperature;
    public int getMaxAcceptMoisture;
    public int getMinAcceptMoisture;
    public int getMaxAcceptTemperature;
    public int getMinAcceptTemperature;
    public int getMinGoodLight;
    public int getMaxGoodLight;
    public int getMinAcceptLight;
    public int getMaxAcceptLight;
    public TimeRange activeRange = TimeRange.day;


    SimpleDateFormat mFormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

    @Inject
    public PlantChartViewModel(FytaController fytaController)
    {
        this.fytaController = fytaController;
    }

    public void getPlantStats(int id, TimeRange range)
    {
        activeRange = range;
        new Thread(new Runnable() {
            @Override
            public void run() {

                if(fytaController.getRestClient() == null)
                    return;
                GetPlantStats stats = fytaController.getRestClient().getPlantStats(id, activeRange);
                if (stats == null)
                    return;
                List<Entry> moistureData = new ArrayList<>();
                List<Entry> tempData = new ArrayList<>();
                List<Entry> lightData = new ArrayList<>();


                getMaxGoodMoisture = stats.thresholds.moisture_max_good;
                getMinGoodMoisture = stats.thresholds.moisture_min_good;
                getMaxGoodTemperature = stats.thresholds.temperature_max_good;
                getMinGoodTemperature = stats.thresholds.temperature_min_good;
                getMaxAcceptMoisture = stats.thresholds.moisture_max_acceptable;
                getMinAcceptMoisture = stats.thresholds.moisture_min_acceptable;
                getMaxAcceptTemperature = stats.thresholds.temperature_max_acceptable;
                getMinAcceptTemperature = stats.thresholds.temperature_min_acceptable;
                getMaxGoodLight = stats.thresholds.light_max_good;
                getMinGoodLight = stats.thresholds.light_min_good;
                getMaxAcceptLight = stats.thresholds.light_max_acceptable;
                getMinAcceptLight = stats.thresholds.light_min_acceptable;
                for(Measurement m :stats.measurements)
                {
                    try {
                        //LocalDateTime time = LocalDateTime.parse(m.date_utc,formatter);
                        //ZonedDateTime ztime = time.atZone(ZoneId.systemDefault());
                        //long t = ztime.toEpochSecond();
                        mFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
                        Date d = mFormatUTC.parse(m.date_utc);
                        long t = d.getTime();

                        Entry e = new Entry(t,m.soil_moisture);
                        moistureData.add(e);

                        Entry te = new Entry(t,m.temperature);
                        tempData.add(te);

                        Entry le = new Entry(t,m.light);
                        lightData.add(le);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Collections.sort(moistureData, new EntryXComparator());
                Collections.sort(tempData, new EntryXComparator());
                Collections.sort(lightData, new EntryXComparator());

                LineDataSet moisture = getLineDataSet(moistureData,"Moisture",Color.BLUE);
                moisture.setDrawHighlightIndicators(false);
                LineDataSet temperature = getLineDataSet(tempData,"Temperature",Color.RED);
                temperature.setDrawHighlightIndicators(false);
                LineDataSet light = getLineDataSet(lightData,"Light",Color.YELLOW);
                light.setDrawHighlightIndicators(false);

                ArrayList<ILineDataSet> dataSetsarray = new ArrayList<>();
                dataSetsarray.add(moisture);
                dataSetsarray.add(temperature);
                dataSetsarray.add(light);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        soil_moistureData.setValue(dataSetsarray);
                    }
                });

            }
        }).start();
    }

    private LineDataSet getLineDataSet(List<Entry> data,String name,int color)
    {
        LineDataSet set1 = new LineDataSet(data,name);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setFillAlpha(85);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);
        set1.setColor(color);
        return set1;
    }

    public void onResume(int id) {
        getPlantStats(id, activeRange);
    }


}