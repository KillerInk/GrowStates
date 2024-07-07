package com.growstats.api.fyta.objects.plants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
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
