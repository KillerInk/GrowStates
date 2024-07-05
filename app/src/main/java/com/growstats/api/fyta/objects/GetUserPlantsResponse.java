package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
{
  "gardens": [
    {
      "id": 5567,
      "garden_name": "Zelt",
      "origin_path": null,
      "thumb_path": null,
      "mac_address": null
    }
  ],
  "plants": [
    {
      "id": 52059,
      "nickname": "traini",
      "scientific_name": "Cannabis sativa",
      "common_name": "Cannabis sativa",
      "status": 1,
      "plant_id": 85,
      "family_id": null,
      "is_shared": false,
      "owner": {},
      "peers": [],
      "wifi_status": 1,
      "thumb_path": "https://api.prod.fyta-app.de/user-plant/52059/thumb_path?timestamp=2024-06-24%2014%3A51%3A20",
      "origin_path": "https://api.prod.fyta-app.de/user-plant/52059/origin_path?timestamp=2024-06-24%2014%3A51%3A20",
      "plant_thumb_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/thumb/4909/thumb_1651708837476.jpeg",
      "plant_origin_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/origin/4909/origin_1651708836252.jpeg",
      "received_data_at": "2024-07-03 16:33:21",
      "temperature_optimal_hours": 0,
      "light_optimal_hours": 0,
      "eligibility": true,
      "temperature_status": 3,
      "light_status": 3,
      "moisture_status": 3,
      "salinity_status": 3,
      "nutrients_status": 3,
      "fertilisation": {
        "last_fertilised_at": "2024-06-20",
        "fertilise_at": "2024-08-01",
        "was_repotted": true
      },
      "notifications": {
        "light": false,
        "temperature": true,
        "water": true,
        "nutrition": true
      },
      "care_tips_count": 0,
      "has_remote_hub": false,
      "has_remote_sensor": false,
      "garden": {
        "id": 5567
      },
      "sensor": {
        "id": "C4:7D:E3:B9:0E:36",
        "has_sensor": true,
        "status": 1,
        "uuid_android": null,
        "uuid_ios": null,
        "version": "1.5.0",
        "is_battery_low": false,
        "received_data_at": "2024-07-03 16:33:21"
      },
      "hub": {
        "id": 11106,
        "hub_id": "34:B7:DA:E8:48:26",
        "hub_name": "Zelt",
        "version": "0.9.7",
        "status": 1,
        "received_data_at": "2024-07-03 16:33:21",
        "reached_hub_at": "2024-07-03 16:33:21"
      },
      "isDoingGreat": true
    },
    {
      "id": 52061,
      "nickname": "strawby",
      "scientific_name": "Cannabis sativa",
      "common_name": "Cannabis sativa",
      "status": 3,
      "plant_id": 85,
      "family_id": null,
      "is_shared": false,
      "owner": {},
      "peers": [],
      "wifi_status": 1,
      "thumb_path": "https://api.prod.fyta-app.de/user-plant/52061/thumb_path?timestamp=2024-06-24%2014%3A59%3A03",
      "origin_path": "https://api.prod.fyta-app.de/user-plant/52061/origin_path?timestamp=2024-06-24%2014%3A59%3A03",
      "plant_thumb_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/thumb/4909/thumb_1651708837476.jpeg",
      "plant_origin_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/origin/4909/origin_1651708836252.jpeg",
      "received_data_at": "2024-07-03 16:06:26",
      "temperature_optimal_hours": 0,
      "light_optimal_hours": 0,
      "eligibility": true,
      "temperature_status": 3,
      "light_status": 5,
      "moisture_status": 3,
      "salinity_status": 3,
      "nutrients_status": 3,
      "fertilisation": {
        "last_fertilised_at": "2024-06-28",
        "fertilise_at": "2024-08-09",
        "was_repotted": true
      },
      "notifications": {
        "light": false,
        "temperature": true,
        "water": true,
        "nutrition": true
      },
      "care_tips_count": 0,
      "has_remote_hub": false,
      "has_remote_sensor": false,
      "garden": {
        "id": 5567
      },
      "sensor": {
        "id": "D8:DB:43:24:C7:14",
        "has_sensor": true,
        "status": 1,
        "uuid_android": null,
        "uuid_ios": null,
        "version": "1.5.0",
        "is_battery_low": false,
        "received_data_at": "2024-07-03 16:06:26"
      },
      "hub": {
        "id": 11106,
        "hub_id": "34:B7:DA:E8:48:26",
        "hub_name": "Zelt",
        "version": "0.9.7",
        "status": 1,
        "received_data_at": "2024-07-03 16:06:26",
        "reached_hub_at": "2024-07-03 16:30:04"
      },
      "noOfbadge": 0,
      "isSilent": true
    }
  ],
  "plantOverview": {
    "doingGreat": 1,
    "needHelp": 0,
    "silent": 1,
    "analysing": 0,
    "noBeam": 0
  },
  "hubs_with_lost_connection": []
}
 */

public class GetUserPlantsResponse {
    @JsonProperty("gardens")
    public List<Garden> gardens;
    @JsonProperty("plants")
    public List<Plant> plants;
    @JsonProperty("plantOverview")
    public PlantOverview plantOverview;
    @JsonProperty("hubs_with_lost_connection")
    public String[] hubs_with_lost_connection;
}
