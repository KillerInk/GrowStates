package com.growstats.api.espfancontroller.impl;

import com.growstats.api.ApiCallBack;
import com.growstats.api.ApiCallBackAdapter;
import com.growstats.api.MySocket;
import com.growstats.api.ServiceGenerator;
import com.growstats.api.espfancontroller.api.EspFanControllerApiService;
import com.growstats.api.espfancontroller.api.EspFanControllerAsyncRestClient;
import com.growstats.api.espfancontroller.objects.EspSettingsResponse;

import okhttp3.ResponseBody;

public class EspFanControllerAsyncRestClientImpl implements EspFanControllerAsyncRestClient {

    private final EspFanControllerApiService apiService;

    public EspFanControllerAsyncRestClientImpl(String apiUrl)
    {
        apiService = ServiceGenerator.createService(EspFanControllerApiService.class,"","",apiUrl);
    }

    @Override
    public void setFanSpeed(int speed,int id, ApiCallBack<ResponseBody> callBack) {
        apiService.setFanSpeed(speed,id).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanVoltage(int min, int max, int id, ApiCallBack<ResponseBody> callBack) {
        apiService.setFanVoltage(min, max, id).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setLightVoltage(int min, int max, ApiCallBack<ResponseBody> callBack) {
      apiService.setLightVoltage(min,max).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanAutoTargetValues(int temp, int hum, int speeddif, ApiCallBack<ResponseBody> callBack) {
        apiService.setFanAutoTargetValues(temp,hum,speeddif).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setTempHumDif(int temp, int hum, ApiCallBack<ResponseBody> callBack) {
        apiService.setTempHumDif(temp,hum).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanAutoControl(boolean autoc, ApiCallBack<ResponseBody> callBack) {
        int t = 0;
        if(autoc)
            t=1;
        apiService.setFanAutoControl(t).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void readDataFromGovee(boolean read, ApiCallBack<ResponseBody> callBack) {
        apiService.readDataFromGovee(read).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanNightModeActive(boolean read, ApiCallBack<ResponseBody> callBack) {
        int t = 0;
        if(read)
            t=1;
        apiService.setFanNightModeActive(t).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setLightAutoControl(boolean read, ApiCallBack<ResponseBody> callBack) {
        int t = 0;
        if(read)
            t=1;
        apiService.setLightAutoControl(t).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanAutoSpeed(int min, int max, ApiCallBack<ResponseBody> callBack) {
        apiService.setFanAutoSpeed(min, max).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setFanNightModeTimes(int onh, int onmin, int offh, int offmin, int max_speed, ApiCallBack<ResponseBody> callBack) {
        apiService.setFanNightModeTimes(onh,onmin,offh,offmin,max_speed).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void setLightTimes(int onh, int onmin, int offh, int offmin, int riseh, int risemin, int seth, int setmin, boolean riseenable, boolean setenable, ApiCallBack<ResponseBody> callBack) {
        apiService.setLightNightModeTimes(onh,onmin,offh,offmin,riseh,risemin,seth,setmin,riseenable,setenable).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void getSettings(ApiCallBack<EspSettingsResponse> callBack) {
        apiService.getSettings().enqueue(new ApiCallBackAdapter<>(callBack));
    }

    public MySocket createWebSocket(String url)
    {
        //Log.i(TAG,"createWebSocket ws://"+url);
        return new MySocket(ServiceGenerator.getSharedClient(),"ws://"+url);
    }
}
