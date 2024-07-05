package com.growstats.api.fyta.impl.sync;

import com.growstats.api.ServiceGenerator;
import com.growstats.api.fyta.api.FytaApiService;
import com.growstats.api.fyta.api.sync.FytaRestClient;
import com.growstats.api.fyta.objects.AuthResponse;
import com.growstats.api.fyta.objects.GetUserPlantsResponse;

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

}
