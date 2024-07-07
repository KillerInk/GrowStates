package com.growstats.api.fyta.objects.plantdetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Values {
    @JsonProperty("min_good")
    public String min_good;
    @JsonProperty("max_good")
    public String max_good;
    @JsonProperty("min_acceptable")
    public String min_acceptable;
    @JsonProperty("max_acceptable")
    public String max_acceptable;
    @JsonProperty("current")
    public String current;
    @JsonProperty("currentFormatted")
    public String currentFormatted;
    @JsonProperty("optimal_hours")
    public String optimal_hours;
}
