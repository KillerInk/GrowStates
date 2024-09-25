package com.growstats.api.espfancontroller.impl;

import com.growstats.api.ServiceGenerator;
import com.growstats.api.espfancontroller.api.EspFanControllerApiService;
import com.growstats.api.espfancontroller.api.EspFanControllerRestClient;

import okhttp3.ResponseBody;

public class EspFanControllerRestClientImpl implements EspFanControllerRestClient {

    private final EspFanControllerApiService apiService;

    public EspFanControllerRestClientImpl(String apiUrl)
    {
        apiService = ServiceGenerator.createService(EspFanControllerApiService.class,"","",apiUrl);
    }

    @Override
    public ResponseBody setFanSpeed(int speed, int id) {
        return ServiceGenerator.executeSync(apiService.setFanSpeed(speed,id));
    }

    @Override
    public ResponseBody setFanVoltage(int min, int max, int id) {
        return ServiceGenerator.executeSync(apiService.setFanVoltage(min, max, id));
    }

    @Override
    public ResponseBody setLightVoltage(int min, int max) {
        return ServiceGenerator.executeSync(apiService.setLightVoltage(min,max));
    }

    @Override
    public ResponseBody setFanAutoTargetValues(int temp, int hum, int speeddif) {
        return ServiceGenerator.executeSync(apiService.setFanAutoTargetValues(temp,hum,speeddif));
    }

    @Override
    public ResponseBody setTempHumDif(int temp, int hum) {
        return ServiceGenerator.executeSync(apiService.setTempHumDif(temp,hum));
    }

    @Override
    public ResponseBody setFanAutoControl(boolean autoc) {
        return ServiceGenerator.executeSync(apiService.setFanAutoControl(autoc));
    }

    @Override
    public ResponseBody readDataFromGovee(boolean read) {
        return ServiceGenerator.executeSync(apiService.readDataFromGovee(read));
    }

    @Override
    public ResponseBody setFanNightModeActive(boolean read) {
        return ServiceGenerator.executeSync(apiService.setFanNightModeActive(read));
    }

    @Override
    public ResponseBody setLightAutoControl(boolean read) {
        return ServiceGenerator.executeSync(apiService.setLightAutoControl(read));
    }

    @Override
    public ResponseBody setFanAutoSpeed(int min, int max) {
        return ServiceGenerator.executeSync(apiService.setFanAutoSpeed(min, max));
    }

    @Override
    public ResponseBody setFanNightModeTimes(int onh, int onmin, int offh, int offmin, int max_speed) {
        return ServiceGenerator.executeSync(apiService.setFanNightModeTimes(onh,onmin,offh,offmin,max_speed));
    }

    @Override
    public ResponseBody setLightTimes(int onh, int onmin, int offh, int offmin, int riseh, int risemin, int seth, int setmin, boolean riseenable, boolean setenable) {
        return ServiceGenerator.executeSync(apiService.setLightNightModeTimes(onh,onmin,offh,offmin,riseh,risemin,seth,setmin,riseenable,setenable));
    }
}
