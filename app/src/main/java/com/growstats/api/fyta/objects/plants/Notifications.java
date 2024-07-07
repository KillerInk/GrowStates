package com.growstats.api.fyta.objects.plants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notifications {
    @JsonProperty("light")
    boolean light;
    @JsonProperty("temperature")
    boolean temperature;
    @JsonProperty("water")
    boolean water;
    @JsonProperty("nutrition")
    boolean nutrition;
}
