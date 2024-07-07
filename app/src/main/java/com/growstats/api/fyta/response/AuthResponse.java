package com.growstats.api.fyta.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("access_token")
    String access_token;
    @JsonProperty("token_type")
    String token_type;
    @JsonProperty("expires_in")
    long expires_in;
    @JsonProperty("refresh_token")
    String refresh_token;
    @JsonProperty("scope")
    String scope;
}
