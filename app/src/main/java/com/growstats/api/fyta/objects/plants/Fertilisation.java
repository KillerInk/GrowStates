package com.growstats.api.fyta.objects.plants;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fertilisation {
    @JsonProperty("last_fertilised_at")
    String last_fertilised_at;
    @JsonProperty("fertilise_at")
    String fertilise_at;
    @JsonProperty("was_repotted")
    boolean was_repotted;

}
