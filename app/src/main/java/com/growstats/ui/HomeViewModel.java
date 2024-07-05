package com.growstats.ui;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.growstats.api.ApiException;
import com.growstats.api.fyta.objects.GetUserPlantsResponse;
import com.growstats.controller.FytaController;
import com.growstats.controller.Settings;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private Settings settings;
    private HomeCustomAdapter homeCustomAdapter;
    private FytaController fytaController;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Inject
    public HomeViewModel(FytaController fytaController, Settings settings, HomeCustomAdapter homeCustomAdapter)
    {
        this.fytaController = fytaController;
        this.settings = settings;
        this.homeCustomAdapter = homeCustomAdapter;
    }

    public void onResume()
    {
        final String k = settings.getSetting(Settings.KEY_APIKEY);
        if(k.equals(""))
            return;
        fytaController.createApi(k);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetUserPlantsResponse plantsResponse = fytaController.getRestClient().getUserPlants();
                    handler.post(() -> homeCustomAdapter.setPlants(plantsResponse.plants));
                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                    Log.i(HomeViewModel.class.getName().toString(), ""+ e.toString());
                }

            }
        }).start();
    }
}