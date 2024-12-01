package com.growstats.controller;

import com.growstats.api.espfancontroller.EspFanControllerFactory;
import com.growstats.api.espfancontroller.api.EspFanControllerAsyncRestClient;
import com.growstats.api.espfancontroller.api.EspFanControllerRestClient;
import com.growstats.api.fyta.FytaFactory;

public class EspFanController {
    private static EspFanControllerRestClient restClient;
    private static EspFanControllerAsyncRestClient asyncRestClient;
    private static String url;
    private static boolean created = false;


    public void createApi(String url)
    {
        if (created)
            return;
        this.url = url;
        restClient = EspFanControllerFactory.newInstance(null,"",url).newRestClient();
        created = true;
    }

    public EspFanControllerRestClient getRestClient()
    {
        if (restClient == null && url != null)
            restClient = EspFanControllerFactory.newInstance(null,"",url).newRestClient();
        return restClient;
    }

    public EspFanControllerAsyncRestClient getAsyncRestClient()
    {
        if (asyncRestClient == null && url != null)
            asyncRestClient = EspFanControllerFactory.newInstance(null,"",url).newAsyncRestClient();
        return asyncRestClient;
    }
}
