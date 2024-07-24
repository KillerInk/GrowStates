package com.growstats.ui.home;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableChar;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.growstats.BR;
import com.growstats.api.fyta.enums.MeasurementStatus;
import com.growstats.controller.BeamData;
import com.growstats.controller.BtClient;

public class PlantItem extends BaseObservable {
    public String name;
    public String thumbPath;
    public MeasurementStatus light_measure_status;
    public MeasurementStatus temperature_measure_status;
    public MeasurementStatus moisture_measure_status;
    public MeasurementStatus salinity_measure_status;
    public MeasurementStatus nutrients_measure_status;
    private String light_val = "";
    private String temperature_val = "";
    private String salinity_val = "";
    private String moisture_val = "";
    public String light_unit;
    public String temperatureunit;
    public String salinity_unit;
    public String moisture_unit;
    public String sensor_mac;
    public int id;
    public LiveButtonClick buttonClick;
    public PlantItem()
    {
    }

    public ObservableInt buttonVisibility = new ObservableInt(View.GONE);

    public void onclick()
    {
        if(buttonClick != null)
            buttonClick.onClick(sensor_mac);
    }

    public void setLight_val(String light_val) {
        if (this.light_val.equals(light_val))
            return;
        this.light_val = light_val;
        notifyPropertyChanged(BR.light_val);
    }

    @Bindable
    public String getLight_val() {
        return light_val;
    }

    @Bindable
    public String getMoisture_val() {
        return moisture_val;
    }

    public void setMoisture_val(String moisture_val) {
        if(this.moisture_val.equals(moisture_val))
            return;
        this.moisture_val = moisture_val;
        notifyPropertyChanged(BR.moisture_val);
    }

    @Bindable
    public String getSalinity_val() {
        return salinity_val;
    }

    public void setSalinity_val(String salinity_val) {
        if (this.salinity_val.equals(salinity_val))
            return;
        this.salinity_val = salinity_val;
        notifyPropertyChanged(BR.salinity_val);
    }

    public void setTemperature_val(String temperature_val) {
        if(this.temperature_val.equals(temperature_val))
            return;
        this.temperature_val = temperature_val;
        notifyPropertyChanged(BR.temperature_val);
    }

    @Bindable
    public String getTemperature_val() {
        return temperature_val;
    }
}
