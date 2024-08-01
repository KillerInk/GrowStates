package com.growstats.api.fyta;

import com.growstats.api.BasicFactory;
import com.growstats.api.fyta.api.async.FytaAsyncRestClient;
import com.growstats.api.fyta.api.sync.FytaRestClient;
import com.growstats.api.fyta.impl.async.FytaAsyncRestClientImpl;
import com.growstats.api.fyta.impl.sync.FytaRestClientImpl;

public class FytaFactory implements BasicFactory<FytaRestClient, FytaAsyncRestClient> {


    public static final String FYTA_HTTPS = "https://web.fyta.de/api";
    public static final String FYTA_INOFFICAL_HTTPS = "https://api.prod.fyta-app.de";
    /**
     * API Key
     */
    private final String apiKey;

    /**
     * Secret.
     */
    private final String secret;

    private final String apiUrl;

    private FytaFactory(String apiKey, String secret, String apiUrl) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.apiUrl = apiUrl;
    }

    public static FytaFactory newInstance(String apiKey, String secret, String apiUrl) {
        return new FytaFactory(apiKey, secret, apiUrl);
    }

    @Override
    public FytaAsyncRestClient newAsyncRestClient() {
        return new FytaAsyncRestClientImpl(apiKey,secret,apiUrl);
    }

    @Override
    public FytaRestClient newRestClient() {
            return new FytaRestClientImpl(apiKey,secret,apiUrl);
    }
}
