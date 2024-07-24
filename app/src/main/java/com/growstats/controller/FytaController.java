package com.growstats.controller;

import com.growstats.api.fyta.FytaFactory;
import com.growstats.api.fyta.api.sync.FytaRestClient;

import javax.inject.Inject;

public class FytaController {

    private static FytaRestClient restClient;
    private static String key;
    private static boolean created = false;

    @Inject
    public FytaController()
    {}

    public void createApi(String apiKey)
    {
        if (created && apiKey.equals(key))
            return;
        this.key = apiKey;
        restClient = FytaFactory.newInstance(key,"",FytaFactory.FYTA_INOFFICAL_HTTPS).newRestClient();
        created = true;
    }

    public FytaRestClient getRestClient() {
        if (restClient == null && key != null)
            restClient = FytaFactory.newInstance(key,"",FytaFactory.FYTA_INOFFICAL_HTTPS).newRestClient();
        return restClient;
    }
}
