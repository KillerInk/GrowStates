package com.growstats.api.fyta.api.sync;

import com.growstats.api.fyta.enums.TimeRange;
import com.growstats.api.fyta.request.LiveModeBody;
import com.growstats.api.fyta.response.LiveModeResponse;
import com.growstats.api.fyta.response.AuthResponse;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetPlantStats;
import com.growstats.api.fyta.response.GetUserPlantsResponse;

import okhttp3.ResponseBody;

public interface FytaRestClient {

    ResponseBody imageResponse(String url);
    AuthResponse authUser(String user, String pw);
    GetUserPlantsResponse getUserPlants();
    GetPlantDetailsResponse getPlantDetails(int id);
    GetPlantStats getPlantStats(int id, TimeRange range);
    LiveModeResponse getLiveModeData(LiveModeBody body);
}
