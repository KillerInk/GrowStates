package com.growstats.ui.home;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.growstats.api.ApiCallBack;
import com.growstats.api.ApiException;
import com.growstats.api.espfancontroller.objects.EspSettingsResponse;
import com.growstats.api.fyta.request.LiveModeBody;
import com.growstats.api.fyta.response.LiveModeResponse;
import com.growstats.api.fyta.objects.MeasurementData;
import com.growstats.api.fyta.objects.plants.Plant;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.controller.BeamData;
import com.growstats.controller.BtClient;
import com.growstats.controller.BtController;
import com.growstats.controller.EspFanController;
import com.growstats.controller.EspFanControllerServiceDiscover;
import com.growstats.controller.EspSocketController;
import com.growstats.controller.FytaController;
import com.growstats.controller.NavigationController;
import com.growstats.controller.Settings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel implements LiveButtonClick, BtController.Events, BtClient.Events, EspSocketController.Event{

    private final String TAG = HomeViewModel.class.getName();
    @Override
    public void onClick(String mac) {
        BtClient btClient = btController.getClient(mac);
        if (btClient != null && btClient.getState() == BtClient.BtClientState.disconnected)
            btClient.connect();
        else if (btClient != null)
            btClient.disconnect();
    }

    private Settings settings;
    private HomeCustomAdapter homeCustomAdapter;
    private FytaController fytaController;
    private Handler handler = new Handler(Looper.getMainLooper());
    public BtController btController;
    EspFanControllerServiceDiscover espFanControllerServiceDiscover;
    EspFanController espFanController;
    private EspSocketController socketController;
    public NavigationController navigationController;

    @Inject
    public HomeViewModel(FytaController fytaController, Settings settings, HomeCustomAdapter homeCustomAdapter, EspFanController espFanController,EspSocketController socketController) {
        this.fytaController = fytaController;
        this.settings = settings;
        this.homeCustomAdapter = homeCustomAdapter;
        this.espFanController = espFanController;
        this.socketController = socketController;
    }


    public void onResume() {
        btController.eventsListner = this::onFoundDevice;
        espFanControllerServiceDiscover = new EspFanControllerServiceDiscover(btController.getContext());
        espFanControllerServiceDiscover.serviceEvent = url -> {
            espFanController.createApi(url);
            if(!socketController.connected())
                socketController.connect(espFanController.getAsyncRestClient().createWebSocket(url.replace("http://","")+"/ws"),this,url);
            else
                socketController.setEventListner(this);
        };
        espFanControllerServiceDiscover.initializeDiscoveryListener();
        final String k = settings.getSetting(Settings.KEY_APIKEY);
        if (k.equals(""))
            return;
        fytaController.createApi(k);
        Log.i(TAG, "getUserPlants");
        fytaController.getAsyncRestClient().getUserPlants(response -> {
            for (Plant p : response.plants) {
                PlantItem item = new PlantItem();
                item.buttonClick = HomeViewModel.this::onClick;
                item.light_measure_status = p.light_status;
                item.temperature_measure_status = p.temperature_status;
                item.moisture_measure_status = p.moisture_status;
                item.nutrients_measure_status = p.nutrients_status;
                item.salinity_measure_status = p.salinity_status;
                item.name = p.nickname;
                item.thumbPath = p.thumb_path;
                item.sensor_mac = p.sensor.id;
                if (p.sensor != null && p.sensor.has_sensor) {
                    Log.i(TAG, "getPlantDetails " + p.id);
                    fytaController.getAsyncRestClient().getPlantDetails(p.id, r -> {
                        item.light_unit = r.plant.measurements.light.unit;
                        item.setLight_val(r.plant.measurements.light.values.currentFormatted + item.light_unit);
                        item.temperatureunit = r.plant.measurements.temperature.unit;
                        item.setTemperature_val(r.plant.measurements.temperature.values.currentFormatted + item.temperatureunit);
                        item.moisture_unit = r.plant.measurements.moisture.unit;
                        item.setMoisture_val(r.plant.measurements.moisture.values.currentFormatted + item.moisture_unit);
                        item.salinity_unit = r.plant.measurements.salinity.unit;
                        item.setSalinity_val(r.plant.measurements.salinity.values.currentFormatted + item.salinity_unit);
                        item.id = r.plant.id;
                        homeCustomAdapter.addPlant(item);
                    });
                }
            }
        });
        btController.find();
    }

    public void onPause()
    {
        espFanControllerServiceDiscover.stopDiscover();
    }

    @Override
    public void onFoundDevice(String mac, BtClient btClient) {
        handler.post(() -> {
            PlantItem p = homeCustomAdapter.getPlantItem(mac);
            if (p != null) {
                p.setButtonVisibility(View.VISIBLE);
                btClient.setEventsListner(HomeViewModel.this);
            }
        });
    }

    @Override
    public void onStateChanged(BtClient.BtClientState state, String mac) {
        PlantItem p = homeCustomAdapter.getPlantItem(mac);
        if (p != null)
            p.setBtClientState(state);
    }

    @Override
    public void onBeamData(BeamData data) {
        LiveModeBody body = new LiveModeBody();
        body.measurement = new MeasurementData();
        body.measurement.battery = String.valueOf(data.battery);
        body.measurement.light = String.valueOf(data.light);
        body.measurement.blue_light = String.valueOf(data.lightBlue);
        body.measurement.green_light = String.valueOf(data.lightGreen);
        body.measurement.red_light = String.valueOf(data.lightRed);
        body.measurement.white_light = String.valueOf(data.lightWhite);
        body.measurement.nir_light = String.valueOf(data.lightNIR);
        body.measurement.power_state = String.valueOf(data.powerState);
        body.measurement.soil_moisture = String.valueOf(data.moisture);
        body.measurement.temperature_bot = String.valueOf(data.temperatureBottom);
        body.measurement.temperature_die = String.valueOf(data.temperatureDie);
        body.measurement.temperature_top = String.valueOf(data.temperatureTop);
        body.measurement.soil_fertility = String.valueOf(data.salinity);
        body.measurement.sensor_id = data.id;
        fytaController.getAsyncRestClient().getLiveModeData(body, response -> {
            Log.i(HomeViewModel.class.getName(), "Success");
            PlantItem p = homeCustomAdapter.getPlantItem(data.id);
            p.setLight_val(response.measurements.light_formatted + p.light_unit);
            p.setMoisture_val(response.measurements.soil_moisture_formatted + p.moisture_unit);
            p.setTemperature_val(response.measurements.temperature_formatted + p.temperatureunit);
            p.setSalinity_val(response.measurements.soil_fertility + p.salinity_unit);
        });
    }

    @Override
    public void onNewTent(TentItem tentItem) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                tentItem.setNavigationController(navigationController);
                homeCustomAdapter.addTent(tentItem);
            }
        });

    }

    @Override
    public void onNewData(float temp, float hum, int fanspeed, int light, float vpd) {

    }
}