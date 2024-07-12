package com.growstats.api.fyta.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    @JsonProperty("access_token")
    public String access_token;
    @JsonProperty("token_type")
    public String token_type;
    @JsonProperty("expires_in")
    public long expires_in;
    @JsonProperty("refresh_token")
    public String refresh_token;
    @JsonProperty("scope")
    public String scope;
}
