package com.growstats.api.fyta.impl.async;

import com.growstats.api.ApiCallBack;
import com.growstats.api.ApiCallBackAdapter;
import com.growstats.api.ServiceGenerator;
import com.growstats.api.fyta.api.FytaApiService;
import com.growstats.api.fyta.api.async.FytaAsyncRestClient;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.request.LiveModeBody;
import com.growstats.api.fyta.request.PlantStatsRequestBody;
import com.growstats.api.fyta.response.AuthResponse;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.api.fyta.response.LiveModeResponse;

import okhttp3.ResponseBody;

public class FytaAsyncRestClientImpl implements FytaAsyncRestClient {

    private final FytaApiService fytaApiService;

    public FytaAsyncRestClientImpl(String apiKey, String secret, String apiUrl)
    {
        fytaApiService = ServiceGenerator.createService(FytaApiService.class,apiKey,secret,apiUrl);
    }

    @Override
    public void getImageResponse(String url, ApiCallBack<ResponseBody> callBack) {
        fytaApiService.downloadFileWithDynamicUrlSync(url).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void authUser(String user, String pw, ApiCallBack<AuthResponse> callBack) {
        fytaApiService.auth(user,pw).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void getUserPlants(ApiCallBack<GetUserPlantsResponse> callBack) {
        fytaApiService.getUserPlants().enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void getPlantDetails(int id, ApiCallBack<GetPlantDetailsResponse> callBack) {
        fytaApiService.getPlantDetails(id).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void getPlantStats(int id, TimeRange range, ApiCallBack<GetPlantStats> callBack) {
        fytaApiService.getPlantStats(id, new PlantStatsRequestBody(range)).enqueue(new ApiCallBackAdapter<>(callBack));
    }

    @Override
    public void getLiveModeData(LiveModeBody body, ApiCallBack<LiveModeResponse> callBack) {
        fytaApiService.sendLiveMode(body).enqueue(new ApiCallBackAdapter<>(callBack));
    }
}
