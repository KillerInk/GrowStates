package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Garden {
    @JsonProperty("id")
    int id;
    @JsonProperty("garden_name")
    String garden_name;
    @JsonProperty("origin_path")
    String origin_path;
    @JsonProperty("thumb_path")
    String thumb_path;
    @JsonProperty("mac_address")
    String mac_address;
}
