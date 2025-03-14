package com.growstats.ui.controller;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.growstats.api.ApiCallBack;
import com.growstats.controller.EspFanController;
import com.growstats.controller.EspSocketController;
import com.growstats.ui.home.TentItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.ResponseBody;

@HiltViewModel
public class GrowControllerChartViewModel extends ViewModel implements EspSocketController.Event {
    public MutableLiveData<LineData> chartData = new MutableLiveData<LineData>();
    private EspFanController espFanController;
    private EspSocketController socketController;
    List<Entry> tempData = new ArrayList<>();
    List<Entry> humData = new ArrayList<>();
    List<Entry> fandata = new ArrayList<>();
    List<Entry> co2data = new ArrayList<>();
    List<Entry> lightdata = new ArrayList<>();
    List<Entry> vpddata = new ArrayList<>();

    SimpleDateFormat mFormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    private final Object lock_obj = new Object();

    @Inject
    public GrowControllerChartViewModel(EspFanController espFanController, EspSocketController socketController)
    {
        this.espFanController = espFanController;
        this.socketController = socketController;
    }

    private LineDataSet getLineDataSet(List<Entry> data,String name,int color)
    {
        LineDataSet set1 = new LineDataSet(data,name);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setValueTextSize(10f);
        set1.setFillAlpha(0);
        set1.setDrawFilled(false);
        set1.setFillColor(color);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);
        set1.setColor(color);
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        return set1;
    }

    public void onResume()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                getChartForDay(calendar);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(int i = 0; i < 8; i++)
                {
                    calendar.add(Calendar.HOUR_OF_DAY,-1);
                    getChartForDay(calendar);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        socketController.setEventListner(this);
    }

    private void getChartForDay(Calendar calendar)
    {
        final String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        if(month.length() == 1)
            month = "0"+month;
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if(day.length() == 1)
            day = "0"+day;
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if(hour.length() == 1)
            hour = "0"+hour;
        String finalMonth = month;
        String finalDay = day;
        espFanController.getAsyncRestClient().getCSVData(year, month, day, hour, new ApiCallBack<ResponseBody>() {
            @Override
            public void onResponse(ResponseBody response) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.byteStream()));
                String line ="";

                try {
                    while ((line = reader.readLine()) != null)
                    {
                        String[] split = line.split(", ");
                        String time = split[0];
                        float temp = Float.parseFloat(split[1]);
                        float hum = Float.parseFloat(split[2]);
                        int fanspeed = Integer.parseInt(split[3]);
                        int co2 = Integer.parseInt(split[4]);
                        int lightmv = Integer.parseInt(split[5]);
                        float vpd = Float.parseFloat(split[6]);
                        Date finaltime = mFormatUTC.parse((year+"-"+ finalMonth +"-"+ finalDay + " " + time));
                        addData(finaltime, temp,hum,fanspeed,co2,lightmv,vpd);

                    }
                    reader.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                applyData();

                /*CombinedData combinedData = new CombinedData();
                combinedData.addDataSet(hum);
                combinedData.addDataSet(temperature);
                combinedData.addDataSet(light);
                combinedData.addDataSet(fan);
                combinedData.addDataSet(co2);
                combinedData.addDataSet(vpd);
                chartData.setValue(combinedData);*/

            }
        });
    }

    private void addData(Date time,float temp, float hum, int fanspeed, int co2, int lightmv, float vpd)
    {
        synchronized (lock_obj) {
            long t = time.getTime();

            Entry tempe = new Entry(t, temp);
            tempData.add(tempe);

            Entry hume = new Entry(t, hum);
            humData.add(hume);

            Entry fanspeede = new Entry(t, fanspeed);
            fandata.add(fanspeede);

            Entry co2e = new Entry(t, co2);
            co2data.add(co2e);

            Entry lightmve = new Entry(t, lightmv);
            lightdata.add(lightmve);

            Entry vpde = new Entry(t, vpd);
            vpddata.add(vpde);
        }
    }

    private void applyData()
    {
        synchronized (lock_obj) {
            Collections.sort(tempData, new EntryXComparator());
            Collections.sort(humData, new EntryXComparator());
            Collections.sort(fandata, new EntryXComparator());
            Collections.sort(co2data, new EntryXComparator());
            Collections.sort(lightdata, new EntryXComparator());
            Collections.sort(vpddata, new EntryXComparator());

            LineDataSet temperature = getLineDataSet(tempData, "Temperature", Color.RED);
            temperature.setDrawHighlightIndicators(false);
            LineDataSet hum = getLineDataSet(humData, "Humidity", Color.BLUE);
            hum.setDrawHighlightIndicators(false);
            LineDataSet fan = getLineDataSet(fandata, "Fan", Color.GRAY);
            fan.setDrawHighlightIndicators(false);
            LineDataSet co2 = getLineDataSet(co2data, "Co2", Color.GREEN);
            co2.setDrawHighlightIndicators(false);
            LineDataSet light = getLineDataSet(lightdata, "Light(mv)", Color.YELLOW);
            light.setDrawHighlightIndicators(false);
            LineDataSet vpd = getLineDataSet(vpddata, "Vpd", Color.CYAN);
            vpd.setDrawHighlightIndicators(false);

            LineData data = new LineData();
            data.addDataSet(temperature);
            data.addDataSet(hum);
            data.addDataSet(fan);
            data.addDataSet(co2);
            data.addDataSet(light);
            data.addDataSet(vpd);
            chartData.postValue(data);
        }
    }

    @Override
    public void onNewTent(TentItem tentItem) {

    }

    @Override
    public void onNewData(float temp, float hum, int fanspeed, int light, float vpd) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        addData(date, temp,hum,fanspeed,0,light,vpd);
        applyData();
    }
}