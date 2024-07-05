package com.growstats.api.fyta.api.sync;

import com.growstats.api.fyta.objects.AuthResponse;
import com.growstats.api.fyta.objects.GetUserPlantsResponse;

import java.util.List;

import okhttp3.ResponseBody;

public interface FytaRestClient {

    ResponseBody imageResponse(String url);
    AuthResponse authUser(String user, String pw);
    GetUserPlantsResponse getUserPlants();
}
