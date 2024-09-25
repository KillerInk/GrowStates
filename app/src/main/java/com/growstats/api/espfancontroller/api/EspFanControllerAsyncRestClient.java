package com.growstats.api.espfancontroller.api;

import com.growstats.api.ApiCallBack;

import okhttp3.ResponseBody;

public interface EspFanControllerAsyncRestClient {
    void setFanSpeed(int speed, int id, ApiCallBack<ResponseBody> callBack);
    void setFanVoltage(int min,int max, int id, ApiCallBack<ResponseBody> callBack);
    void setLightVoltage(int min,int max, ApiCallBack<ResponseBody> callBack);
    void setFanAutoTargetValues(int temp,int hum, int speeddif, ApiCallBack<ResponseBody> callBack);
    void setTempHumDif(int temp,int hum, ApiCallBack<ResponseBody> callBack);
    void setFanAutoControl(boolean autoc, ApiCallBack<ResponseBody> callBack);
    void readDataFromGovee(boolean read, ApiCallBack<ResponseBody> callBack);
    void setFanNightModeActive(boolean read, ApiCallBack<ResponseBody> callBack);
    void setLightAutoControl(boolean read, ApiCallBack<ResponseBody> callBack);
    void setFanAutoSpeed(int min, int max, ApiCallBack<ResponseBody> callBack);
    void setFanNightModeTimes(int onh, int onmin, int offh, int offmin, int max_speed, ApiCallBack<ResponseBody> callBack);
    void setLightTimes(int onh, int onmin, int offh, int offmin,int riseh, int risemin, int seth, int setmin, boolean riseenable, boolean setenable, ApiCallBack<ResponseBody> callBack);
}
