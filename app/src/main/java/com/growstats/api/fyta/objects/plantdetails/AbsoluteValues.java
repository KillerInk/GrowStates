package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbsoluteValues {
    @JsonProperty("min")
    public String min;
    @JsonProperty("max")
    public String max;
    @JsonProperty("minText")
    public String minText;
    @JsonProperty("maxText")
    public String maxText;
}
