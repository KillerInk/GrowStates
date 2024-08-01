package com.growstats.ui.home;

import android.graphics.Color;
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
    private BtClient.BtClientState btClientState = BtClient.BtClientState.disconnected;
    public PlantItem()
    {
    }

    private int buttonVisibility = View.GONE;

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

    public void setBtClientState(BtClient.BtClientState btClientState) {
        if (this.btClientState == btClientState)
            return;
        this.btClientState = btClientState;
        notifyPropertyChanged(BR.btClientState);
    }

    @Bindable
    public BtClient.BtClientState getBtClientState() {
        return btClientState;
    }

    public void setButtonVisibility(int buttonVisibility) {
        if(this.buttonVisibility == buttonVisibility)
            return;
        this.buttonVisibility = buttonVisibility;
        notifyPropertyChanged(BR.buttonVisibility);
    }

    @Bindable
    public int getButtonVisibility() {
        return buttonVisibility;
    }

    public void copyFrom(PlantItem plantItem)
    {
        setTemperature_val(plantItem.getTemperature_val());
        setSalinity_val(plantItem.getSalinity_val());
        setMoisture_val(plantItem.moisture_val);
        setLight_val(plantItem.getLight_val());
    }
}
