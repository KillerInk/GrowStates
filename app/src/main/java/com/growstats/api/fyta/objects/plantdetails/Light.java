package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growstats.api.fyta.enums.MeasurementStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Light extends Temperature{
    @JsonProperty("dli_values")
    public DliValues dliValues;
    @JsonProperty("dli_unit")
    public String dli_unit;
}
