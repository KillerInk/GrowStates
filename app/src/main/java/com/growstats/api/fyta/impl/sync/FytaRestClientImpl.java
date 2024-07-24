package com.growstats.api.fyta.impl.sync;

import com.growstats.api.ServiceGenerator;
import com.growstats.api.fyta.api.FytaApiService;
import com.growstats.api.fyta.api.sync.FytaRestClient;
import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.objects.LiveModeBody;
import com.growstats.api.fyta.objects.LiveModeResponse;
import com.growstats.api.fyta.request.PlantStatsRequestBody;
import com.growstats.api.fyta.response.AuthResponse;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.api.fyta.response.GetUserPlantsResponse;

import okhttp3.ResponseBody;

public class FytaRestClientImpl implements FytaRestClient {

    private final FytaApiService fytaApiService;

    public FytaRestClientImpl(String apiKey, String secret, String apiUrl)
    {
        fytaApiService = ServiceGenerator.createService(FytaApiService.class,apiKey,secret,apiUrl);
    }

    @Override
    public ResponseBody imageResponse(String url) {
        return ServiceGenerator.executeSync(fytaApiService.downloadFileWithDynamicUrlSync(url));
    }

    @Override
    public AuthResponse authUser(String user, String pw) {
        return ServiceGenerator.executeSync(fytaApiService.auth(user,pw));
    }

    @Override
    public GetUserPlantsResponse getUserPlants() {
        return ServiceGenerator.executeSync(fytaApiService.getUserPlants());
    }

    @Override
    public GetPlantDetailsResponse getPlantDetails(int id) {
        return ServiceGenerator.executeSync(fytaApiService.getPlantDetails(id));
    }

    @Override
    public GetPlantStats getPlantStats(int id, TimeRange range) {
        return ServiceGenerator.executeSync(fytaApiService.getPlantStats(id, new PlantStatsRequestBody(range)));
    }

    @Override
    public LiveModeResponse getLiveModeData(LiveModeBody body) {
        return ServiceGenerator.executeSync(fytaApiService.sendLiveMode(body));
    }


}
