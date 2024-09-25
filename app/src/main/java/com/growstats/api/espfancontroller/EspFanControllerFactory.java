package com.growstats.api.espfancontroller;

import com.growstats.api.BasicFactory;
import com.growstats.api.espfancontroller.api.EspFanControllerAsyncRestClient;
import com.growstats.api.espfancontroller.impl.EspFanControllerAsyncRestClientImpl;
import com.growstats.api.espfancontroller.impl.EspFanControllerRestClientImpl;
import com.growstats.api.fyta.FytaFactory;
import com.growstats.api.fyta.api.async.FytaAsyncRestClient;
import com.growstats.api.fyta.api.sync.FytaRestClient;

public class EspFanControllerFactory implements BasicFactory<EspFanControllerRestClientImpl, EspFanControllerAsyncRestClient> {

    String url;

    private EspFanControllerFactory(String url)
    {
        this.url = url;
    }

    public static EspFanControllerFactory newInstance(String apiKey, String secret, String apiUrl) {
        return new EspFanControllerFactory(apiUrl);
    }

    @Override
    public EspFanControllerAsyncRestClient newAsyncRestClient() {
        return new EspFanControllerAsyncRestClientImpl(url);
    }

    @Override
    public EspFanControllerRestClientImpl newRestClient() {
        return new EspFanControllerRestClientImpl(url);
    }
}
