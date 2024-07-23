package com.growstats.ui.home;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.growstats.api.fyta.enums.MeasurementStatus;
import com.growstats.controller.BeamData;

public class PlantItem {
    public String name;
    public String thumbPath;
    public MeasurementStatus light_measure_status;
    public MeasurementStatus temperature_measure_status;
    public MeasurementStatus moisture_measure_status;
    public MeasurementStatus salinity_measure_status;
    public MeasurementStatus nutrients_measure_status;
    public String light_val;
    public String temperature_val;
    public String salinity_val;
    public String moisture_val;
    public String light_unit;
    public String temperatureunit;
    public String salinity_unit;
    public String moisture_unit;
    public String sensor_mac;
    public int id;
    public LiveButtonClick buttonClick;

    public String getTempString()
    {
        return temperature_val + temperatureunit;
    }
    public String getSalinityString()
    {
        return salinity_val + salinity_unit;
    }
    public String getMoistureString()
    {
        return moisture_val + moisture_unit;
    }
    public String getLightString()
    {
        return light_val + light_unit;
    }

    public ObservableInt buttonVisibility = new ObservableInt(View.GONE);
    public ObservableField<BeamData> beamDataObservableField = new ObservableField<>();

    public void onclick()
    {
        if(buttonClick != null)
            buttonClick.onClick(sensor_mac);
    }
}
