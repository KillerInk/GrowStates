package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growstats.api.fyta.enums.MeasurementStatus;

public class Temperature {
    @JsonProperty("type")
    public String type;
    @JsonProperty("status")
    public MeasurementStatus status;
    @JsonProperty("values")
    public Values values;
    @JsonProperty("unit")
    public String unit;
    @JsonProperty("absolute_values")
    AbsoluteValues absolute_values;
}
