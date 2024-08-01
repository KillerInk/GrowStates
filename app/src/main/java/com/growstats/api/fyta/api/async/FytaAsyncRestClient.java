package com.growstats.api.fyta.api.async;

import com.growstats.api.ApiCallBack;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.request.LiveModeBody;
import com.growstats.api.fyta.response.AuthResponse;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.api.fyta.response.LiveModeResponse;

import okhttp3.ResponseBody;

public interface FytaAsyncRestClient {
    void getImageResponse(String url, ApiCallBack<ResponseBody> callBack);
    void authUser(String user, String pw, ApiCallBack<AuthResponse> callBack);
    void getUserPlants(ApiCallBack<GetUserPlantsResponse>callBack);
    void getPlantDetails(int id, ApiCallBack<GetPlantDetailsResponse> callBack);
    void getPlantStats(int id, TimeRange range, ApiCallBack<GetPlantStats> callBack);
    void getLiveModeData(LiveModeBody body, ApiCallBack<LiveModeResponse> callBack);
}
