package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growstats.api.fyta.objects.plants.Plant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantDetails extends Plant {
    @JsonProperty("measurements")
    public Measurements measurements;
}
