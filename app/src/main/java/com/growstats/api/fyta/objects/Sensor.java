package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor {
    @JsonProperty("id")
    String id;
    @JsonProperty("has_sensor")
    boolean has_sensor;
    @JsonProperty("status")
    int status;
    @JsonProperty("uuid_android")
    String uuid_android;
    @JsonProperty("uuid_ios")
    String uuid_ios;
    @JsonProperty("version")
    String version;
    @JsonProperty("is_battery_low")
    boolean is_battery_low;
    @JsonProperty("received_data_at")
    String received_data_at;
}
