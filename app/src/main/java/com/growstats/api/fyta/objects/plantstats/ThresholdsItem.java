package com.growstats.api.fyta.objects.plantstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
"thresholds": {
		"ph_min": 4,
		"ph_max": 7,
		"temperature_min_good": 15,
		"temperature_max_good": 30,
		"temperature_min_acceptable": 8,
		"temperature_max_acceptable": 35,
		"light_min_good": 400,
		"light_max_good": 1000,
		"light_min_acceptable": 300,
		"light_max_acceptable": 1200,
		"dli_light_min_good": 30,
		"dli_light_max_good": 40,
		"dli_light_min_acceptable": 20,
		"dli_light_max_acceptable": 50,
		"moisture_min_good": 35,
		"moisture_max_good": 70,
		"moisture_min_acceptable": 25,
		"moisture_max_acceptable": 80,
		"salinity_min_good": 1,
		"salinity_max_good": 1.3,
		"salinity_min_acceptable": 0.6,
		"salinity_max_acceptable": 1.6,
		"watering_cycles_until_fertilisation": 3,
		"days_until_fertilisation": 15
		"id": 185758,
        "thresholds_type": "germination",
        "photoperiod": "day_neutral"
	},
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThresholdsItem {
    public int ph_min;
    public int ph_max;
    public int temperature_min_good;
    public int temperature_max_good;
    public int temperature_min_acceptable;
    public int temperature_max_acceptable;
    public int light_min_good;
    public int light_max_good;
    public int light_min_acceptable;
    public int light_max_acceptable;
    public int dli_light_min_good;
    public int dli_light_max_good;
    public int dli_light_min_acceptable;
    public int dli_light_max_acceptable;
    public int moisture_min_good;
    public int moisture_max_good;
    public int moisture_min_acceptable;
    public int moisture_max_acceptable;
    public float salinity_min_good;
    public float salinity_max_good;
    public float salinity_min_acceptable;
    public float salinity_max_acceptable;
    public int watering_cycles_until_fertilisation;
    public int days_until_fertilisation;
    public int id;
    public String thresholds_type;
    public String photoperiod;
}
