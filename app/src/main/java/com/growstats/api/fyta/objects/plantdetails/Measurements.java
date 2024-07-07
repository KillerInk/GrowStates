package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO add ph
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurements {
    @JsonProperty("temperature")
    public Temperature temperature;
    @JsonProperty("light")
    public Light light;
    @JsonProperty("moisture")
    public Temperature moisture;
    @JsonProperty("salinity")
    public Temperature salinity;
}
