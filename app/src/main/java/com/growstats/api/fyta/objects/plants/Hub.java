package com.growstats.api.fyta.objects.plants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hub {
    @JsonProperty("id")
    int id;
    @JsonProperty("hub_id")
    String hub_id;
    @JsonProperty("hub_name")
    String hub_name;
    @JsonProperty("version")
    String version;
    @JsonProperty("status")
    int status;
    @JsonProperty("received_data_at")
    String received_data_at;
    @JsonProperty("reached_hub_at")
    String reached_hub_at;
}
