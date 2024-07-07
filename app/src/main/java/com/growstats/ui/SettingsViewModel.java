package com.growstats.ui;

import android.util.Log;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import com.growstats.api.ApiException;
import com.growstats.controller.FytaController;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.controller.Settings;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class SettingsViewModel extends ViewModel {
    private FytaController fytaController;
    public ObservableField<String> apikey = new ObservableField<>();
    Settings settings;
    private HomeCustomAdapter homeCustomAdapter;

    @Inject
    public SettingsViewModel(FytaController fytaController, Settings settings, HomeCustomAdapter homeCustomAdapter)
    {
        this.fytaController = fytaController;
        this.settings = settings;
        this.homeCustomAdapter = homeCustomAdapter;
        apikey.set(settings.getSetting(Settings.KEY_APIKEY));
    }

    public void onTestClick()
    {
        fytaController.createApi(apikey.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetUserPlantsResponse plantsResponse = fytaController.getRestClient().getUserPlants();
                    settings.saveSetting(Settings.KEY_APIKEY, apikey.get());
                    //homeCustomAdapter.setPlants(plantsResponse.plants);
                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                    Log.i(SettingsViewModel.class.getName().toString(), ""+ e.toString());
                }

            }
        }).start();

    }

}