package com.growstats.api.fyta.objects.plants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {
    @JsonProperty("id")
    public String id;
    @JsonProperty("has_sensor")
    public boolean has_sensor;
    @JsonProperty("status")
    public int status;
    @JsonProperty("uuid_android")
    public String uuid_android;
    @JsonProperty("uuid_ios")
    public String uuid_ios;
    @JsonProperty("version")
    public String version;
    @JsonProperty("is_battery_low")
    public boolean is_battery_low;
    @JsonProperty("received_data_at")
    public String received_data_at;
}
