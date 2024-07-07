package com.growstats.ui;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.growstats.api.ApiException;
import com.growstats.api.fyta.objects.plantdetails.PlantDetails;
import com.growstats.api.fyta.objects.plants.Plant;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.controller.FytaController;
import com.growstats.controller.Settings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private Settings settings;
    private HomeCustomAdapter homeCustomAdapter;
    private FytaController fytaController;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Inject
    public HomeViewModel(FytaController fytaController, Settings settings, HomeCustomAdapter homeCustomAdapter) {
        this.fytaController = fytaController;
        this.settings = settings;
        this.homeCustomAdapter = homeCustomAdapter;
    }

    public void onResume() {
        final String k = settings.getSetting(Settings.KEY_APIKEY);
        if (k.equals(""))
            return;
        fytaController.createApi(k);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetUserPlantsResponse plantsResponse = fytaController.getRestClient().getUserPlants();
                    List<PlantItem> plantItemList = new ArrayList<>();
                    for (Plant p : plantsResponse.plants) {
                        PlantItem item = new PlantItem();
                        item.light_measure_status = p.light_status;
                        item.temperature_measure_status = p.temperature_status;
                        item.moisture_measure_status = p.moisture_status;
                        item.nutrients_measure_status = p.nutrients_status;
                        item.salinity_measure_status = p.salinity_status;
                        item.name = p.nickname;
                        item.thumbPath = p.thumb_path;
                        if (p.sensor != null && p.sensor.has_sensor) {
                            GetPlantDetailsResponse r = fytaController.getRestClient().getPlantDetails(p.id);
                            item.light_unit = r.plant.measurements.light.unit;
                            item.light_val = r.plant.measurements.light.values.currentFormatted;
                            item.temperatureunit = r.plant.measurements.temperature.unit;
                            item.temperature_val = r.plant.measurements.temperature.values.currentFormatted;
                            item.moisture_unit = r.plant.measurements.moisture.unit;
                            item.moisture_val = r.plant.measurements.moisture.values.currentFormatted;
                            item.salinity_unit = r.plant.measurements.salinity.unit;
                            item.salinity_val = r.plant.measurements.salinity.values.currentFormatted;
                        }
                        plantItemList.add(item);

                    }
                    handler.post(() -> homeCustomAdapter.setPlants(plantItemList));
                } catch (ApiException e) {
                    e.printStackTrace();
                    Log.i(HomeViewModel.class.getName().toString(), "" + e.toString());
                }

            }
        }).start();
    }
}