package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

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
