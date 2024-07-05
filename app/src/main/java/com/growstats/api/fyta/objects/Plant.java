package com.growstats.api.fyta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growstats.api.fyta.enums.MeasurementStatus;

import java.util.List;


/*{
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
        }
*/
public class Plant {
    @JsonProperty("id")
    public int id;
    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("scientific_name")
    public String scientific_name;
    @JsonProperty("common_name")
    public String common_name;
    @JsonProperty("status")
    public int status;
    @JsonProperty("plant_id")
    public int plant_id;
    @JsonProperty("family_id")
    public int family_id;
    @JsonProperty("is_shared")
    public boolean is_shared;
    @JsonProperty("owner")
    public Owner owner;
    @JsonProperty("peers")
    public String[] peers;
    @JsonProperty("wifi_status")
    public int wifi_status;
    @JsonProperty("thumb_path")
    public String thumb_path;
    @JsonProperty("origin_path")
    public String origin_path;
    @JsonProperty("plant_thumb_path")
    public String plant_thumb_path;
    @JsonProperty("plant_origin_path")
    public String plant_origin_path;
    @JsonProperty("received_data_at")
    public String received_data_at;
    @JsonProperty("temperature_optimal_hours")
    public int temperature_optimal_hours;
    @JsonProperty("light_optimal_hours")
    public int light_optimal_hours;
    @JsonProperty("eligibility")
    public boolean eligibility;
    @JsonProperty("temperature_status")
    public MeasurementStatus temperature_status;
    @JsonProperty("light_status")
    public MeasurementStatus light_status;
    @JsonProperty("moisture_status")
    public  MeasurementStatus moisture_status;
    @JsonProperty("salinity_status")
    public MeasurementStatus salinity_status;
    @JsonProperty("nutrients_status")
    public MeasurementStatus nutrients_status;
    @JsonProperty("fertilisation")
    public Fertilisation fertilisation;
    @JsonProperty("notifications")
    public Notifications notifications;
    @JsonProperty("care_tips_count")
    public int care_tips_count;
    @JsonProperty("has_remote_hub")
    public boolean has_remote_hub;
    @JsonProperty("has_remote_sensor")
    public boolean has_remote_sensor;
    @JsonProperty("garden")
    public Garden garden;
    @JsonProperty("sensor")
    public Sensor sensor;
    @JsonProperty("hub")
    public Hub hub;
    @JsonProperty("isDoingGreat")
    public boolean isDoingGreat;
    @JsonProperty("noOfbadge")
    public int noOfbadge;
    @JsonProperty("isSilent")
    public boolean isSilent;


}
