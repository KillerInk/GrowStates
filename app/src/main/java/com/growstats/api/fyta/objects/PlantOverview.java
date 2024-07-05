package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 "plantOverview": {
    "doingGreat": 1,
    "needHelp": 0,
    "silent": 1,
    "analysing": 0,
    "noBeam": 0
  },
 */
public class PlantOverview {
    @JsonProperty("doingGreat")
    int doingGreat;
    @JsonProperty("needHelp")
    int needHelp;
    @JsonProperty("silent")
    int silent;
    @JsonProperty("analysing")
    int analysing;
    @JsonProperty("noBeam")
    int noBeam;
}
