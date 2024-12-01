package com.growstats.ui.controller;

import android.widget.CompoundButton;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.growstats.api.ApiCallBack;
import com.growstats.api.espfancontroller.objects.EspSettingsResponse;
import com.growstats.controller.EspFanController;
import android.widget.CompoundButton;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.ResponseBody;

@HiltViewModel
public class GrowControllerViewModel extends ViewModel {

    public ObservableField<String> fan0max = new ObservableField<>();
    public ObservableInt fan0min = new ObservableInt();
    public ObservableInt fan1max = new ObservableInt();
    public ObservableInt fan1min = new ObservableInt();
    public ObservableBoolean fanautocontrol = new ObservableBoolean();
    public ObservableInt target_temp = new ObservableInt();
    public ObservableInt target_hum = new ObservableInt();
    public ObservableInt fans_speed_difference = new ObservableInt();
    public ObservableInt fans_speed_min = new ObservableInt();
    public ObservableInt fans_speed_max = new ObservableInt();
    public ObservableBoolean fannightmode = new ObservableBoolean();
    public ObservableInt fan_nightmode_on_h = new ObservableInt();
    public ObservableInt fan_nightmode_on_min = new ObservableInt();
    public ObservableInt fan_nightmode_off_h = new ObservableInt();
    public ObservableInt fan_nightmode_off_min = new ObservableInt();
    public ObservableInt fan_nightmode_speed_max = new ObservableInt();
    public ObservableBoolean light_auto_control = new ObservableBoolean();
    public ObservableInt light_min_volt = new ObservableInt();
    public ObservableInt light_max_volt = new ObservableInt();
    public ObservableInt light_on_h = new ObservableInt();
    public ObservableInt light_on_min = new ObservableInt();
    public ObservableInt light_off_h = new ObservableInt();
    public ObservableInt light_off_min = new ObservableInt();
    public ObservableInt light_sunrise_end_h = new ObservableInt();
    public ObservableInt light_sunrise_end_min = new ObservableInt();
    public ObservableInt light_sunset_start_h = new ObservableInt();
    public ObservableInt light_sunset_start_min = new ObservableInt();
    public ObservableBoolean light_sunrise_enable = new ObservableBoolean();
    public ObservableBoolean light_sunset_enable = new ObservableBoolean();


    EspFanController espFanController;
    @Inject
    public GrowControllerViewModel(EspFanController espFanController)
    {
        this.espFanController = espFanController;
        fan0max.set("0");
    }

    public void onResume()
    {
        espFanController.getAsyncRestClient().getSettings(new ApiCallBack<EspSettingsResponse>() {
            @Override
            public void onResponse(EspSettingsResponse response) {
                fan0max.set(String.valueOf(response.fan0max));
                fan0min.set(response.fan0min);
                fan1max.set(response.fan1max);
                fan1min.set(response.fan1min);
                fanautocontrol.set(response.autocontrol);
                target_temp.set(response.targetTemperature);
                target_hum.set(response.targetHumidity);
                fans_speed_difference.set(response.speeddif);
                fans_speed_min.set(response.minspeed);
                fans_speed_max.set(response.maxspeed);
                fannightmode.set(response.nightmodeactive);
                fan_nightmode_on_h.set(response.nightmodeonhour);
                fan_nightmode_on_min.set(response.nightmodeonmin);
                fan_nightmode_off_h.set(response.nightmodeoffhour);
                fan_nightmode_off_min.set(response.nightmodeoffmin);
                fan_nightmode_speed_max.set(response.nightmodemaxspeed);

                light_auto_control.set(response.lightautomode);
                light_sunrise_enable.set(response.lightriseenable);
                light_sunset_enable.set(response.lightsetenable);
                light_min_volt.set(response.lightminvolt);
                light_max_volt.set(response.lightmaxvolt);
                light_on_h.set(response.lightonh);
                light_on_min.set(response.lightonmin);
                light_off_h.set(response.lightoffh);
                light_off_min.set(response.lightoffmin);
                light_sunrise_end_h.set(response.lightriseh);
                light_sunrise_end_min.set(response.lightrisemin);
                light_sunset_start_h.set(response.lightseth);
                light_sunset_start_min.set(response.lightsetmin);
            }
        });
    }

    private ApiCallBack<ResponseBody> dummycallback = new ApiCallBack<ResponseBody>() {
        @Override
        public void onResponse(ResponseBody response) {

        }
    };

    public void fan0_submit()
    {
        espFanController.getAsyncRestClient().setFanVoltage(fan0min.get(), Integer.valueOf(fan0max.get()), 0, dummycallback);
    }

    public void fan1_submit()
    {
        espFanController.getAsyncRestClient().setFanVoltage(fan1min.get(), fan1max.get(), 1, dummycallback);
    }

    public void fan_autotarget_submit()
    {
        espFanController.getAsyncRestClient().setFanAutoControl(fanautocontrol.get(),dummycallback);
        espFanController.getAsyncRestClient().setFanAutoTargetValues(target_temp.get(),target_hum.get(),fans_speed_difference.get(),dummycallback);
    }

    public void fan_auto_speedlimits_submit()
    {
        espFanController.getAsyncRestClient().setFanAutoSpeed(fans_speed_min.get(),fans_speed_max.get(),dummycallback);
    }

    public void fan_nightmode_submit()
    {
        espFanController.getAsyncRestClient().setFanNightModeActive(fannightmode.get(),dummycallback);
        espFanController.getAsyncRestClient().setFanNightModeTimes(fan_nightmode_on_h.get(),fan_nightmode_on_min.get(),fan_nightmode_off_h.get(),fan_nightmode_off_min.get(),fan_nightmode_speed_max.get(),dummycallback);
    }

    public void light_voltage_submit()
    {
        espFanController.getAsyncRestClient().setLightAutoControl(light_auto_control.get(),dummycallback);
        espFanController.getAsyncRestClient().setLightVoltage(light_min_volt.get(),light_max_volt.get(),dummycallback);
    }

    public void light_values_submit()
    {
        espFanController.getAsyncRestClient().setLightTimes(light_on_h.get(),light_on_min.get(),light_off_h.get(),light_off_min.get(),light_sunrise_end_h.get(),light_sunrise_end_min.get(),light_sunset_start_h.get(),light_sunset_start_min.get(),light_sunrise_enable.get(),light_sunset_enable.get(),dummycallback);
    }

    CompoundButton.OnCheckedChangeListener autolistner = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            fanautocontrol.set(isChecked);
        }
    };

    CompoundButton.OnCheckedChangeListener nightlistner = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            fannightmode.set(isChecked);
        }
    };

    CompoundButton.OnCheckedChangeListener lightlistner = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            light_auto_control.set(isChecked);
        }
    };




    public void onPause()
    {

    }
}