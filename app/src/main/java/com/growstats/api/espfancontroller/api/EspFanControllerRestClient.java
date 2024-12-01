package com.growstats.api.espfancontroller.api;

import com.growstats.api.MySocket;
import com.growstats.api.espfancontroller.objects.EspSettingsResponse;

import okhttp3.ResponseBody;

public interface EspFanControllerRestClient {
    ResponseBody setFanSpeed(int speed, int id);
    ResponseBody setFanVoltage(int min,int max, int id);
    ResponseBody setLightVoltage(int min,int max);
    ResponseBody setFanAutoTargetValues(int temp,int hum, int speeddif);
    ResponseBody setTempHumDif(int temp,int hum);
    ResponseBody setFanAutoControl(boolean autoc);
    ResponseBody readDataFromGovee(boolean read);
    ResponseBody setFanNightModeActive(boolean read);
    ResponseBody setLightAutoControl(boolean read);
    ResponseBody setFanAutoSpeed(int min, int max);
    ResponseBody setFanNightModeTimes(int onh, int onmin, int offh, int offmin, int max_speed);
    ResponseBody setLightTimes(int onh, int onmin, int offh, int offmin,int riseh, int risemin, int seth, int setmin, boolean riseenable, boolean setenable);
    EspSettingsResponse getSettings();
    public MySocket createWebSocket(String url);
}
