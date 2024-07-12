package com.growstats.api.fyta.response;

import com.growstats.api.fyta.objects.plants.Fertilisation;
import com.growstats.api.fyta.objects.plantstats.AbsoluteValuesItems;
import com.growstats.api.fyta.objects.plantstats.DliLightItem;
import com.growstats.api.fyta.objects.plantstats.Measurement;
import com.growstats.api.fyta.objects.plantstats.ThresholdsItem;

import java.util.List;

public class GetPlantStats {

    public List<Measurement> measurements;
    public List<DliLightItem> dli_light;
    public AbsoluteValuesItems absolute_values;
    public ThresholdsItem thresholds;
    public Fertilisation fertilisation;
    public List<ThresholdsItem> thresholds_list;
    /*
    {
	"measurements": [
		{
			"light": 637,
			"temperature": 26,
			"soil_moisture": 61,
			"soil_moisture_anomaly": false,
			"soil_fertility": 2.3,
			"soil_fertility_anomaly": false,
			"thresholds_id": 185739,
			"date_utc": "2024-07-10 13:02:00"
		},
		{
			"light": 632,
			"temperature": 27,
			"soil_moisture": 61,
			"soil_moisture_anomaly": false,
			"soil_fertility": 2.3,
			"soil_fertility_anomaly": false,
			"thresholds_id": 185739,
			"date_utc": "2024-07-10 12:01:52"
		},
		{
			"light": 843,
			"temperature": 27,
			"soil_moisture": 38,
			"soil_moisture_anomaly": false,
			"soil_fertility": 2.3,
			"soil_fertility_anomaly": false,
			"thresholds_id": 185739,
			"date_utc": "2024-07-10 11:01:49"
		},
		{
			"light": 853,
			"temperature": 29,
			"soil_moisture": 36,
			"soil_moisture_anomaly": false,
			"soil_fertility": 2.3,
			"soil_fertility_anomaly": false,
			"thresholds_id": 185739,
			"date_utc": "2024-07-10 10:01:48"
		},

        .....

	],
	"dli_light": [
		{
			"dli_light": 29.64,
			"date_utc": "2024-07-10 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 56.45,
			"date_utc": "2024-07-09 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 57.08,
			"date_utc": "2024-07-08 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 52.19,
			"date_utc": "2024-07-07 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 53.47,
			"date_utc": "2024-07-06 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 52.84,
			"date_utc": "2024-07-05 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 59.38,
			"date_utc": "2024-07-04 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 60.55,
			"date_utc": "2024-07-03 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 59.75,
			"date_utc": "2024-07-02 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 51.97,
			"date_utc": "2024-07-01 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 22.19,
			"date_utc": "2024-06-30 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 13.28,
			"date_utc": "2024-06-29 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 13.62,
			"date_utc": "2024-06-28 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 10.96,
			"date_utc": "2024-06-27 00:00:00",
			"thresholds_id": 185739
		},
		{
			"dli_light": 2.74,
			"date_utc": "2024-06-26 00:00:00",
			"thresholds_id": 185739
		}
	],
	"absolute_values": {
		"light": {
			"min": "0",
			"minText": "0",
			"max": "1220",
			"maxText": "1220"
		},
		"dli_light": {
			"min": "0",
			"minText": "0",
			"max": "60.8",
			"maxText": "60.8"
		},
		"temperature": {
			"min": "0",
			"minText": "0",
			"max": "40",
			"maxText": "40"
		},
		"soil_moisture": {
			"min": "0",
			"minText": "0",
			"max": "85",
			"maxText": "85"
		},
		"soil_fertility": {
			"min": "0",
			"minText": "0",
			"max": "2.6",
			"maxText": "2.6"
		}
	},
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
	},
	"fertilisation": {
		"last_fertilised_at": "2024-06-28",
		"fertilise_at": "2024-08-09",
		"was_repotted": true
	},
	"thresholds_list": [
		{
			"ph_min": 0,
			"ph_max": 14,
			"temperature_min_good": 17,
			"temperature_max_good": 36,
			"temperature_min_acceptable": 10,
			"temperature_max_acceptable": 42,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 0.6,
			"salinity_max_good": 1,
			"salinity_min_acceptable": 0.4,
			"salinity_max_acceptable": 1.2,
			"watering_cycles_until_fertilisation": 7,
			"days_until_fertilisation": 21,
			"id": 185758,
			"thresholds_type": "germination",
			"photoperiod": "day_neutral"
		},
		{
			"ph_min": 0,
			"ph_max": 14,
			"temperature_min_good": 17,
			"temperature_max_good": 36,
			"temperature_min_acceptable": 10,
			"temperature_max_acceptable": 42,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 0.6,
			"salinity_max_good": 1,
			"salinity_min_acceptable": 0.4,
			"salinity_max_acceptable": 1.2,
			"watering_cycles_until_fertilisation": 7,
			"days_until_fertilisation": 21,
			"id": 185714,
			"thresholds_type": "germination",
			"photoperiod": "long_day"
		},
		{
			"ph_min": 0,
			"ph_max": 14,
			"temperature_min_good": -10,
			"temperature_max_good": 50,
			"temperature_min_acceptable": -10,
			"temperature_max_acceptable": 50,
			"light_min_good": 0,
			"light_max_good": 3000,
			"light_min_acceptable": 0,
			"light_max_acceptable": 3000,
			"dli_light_min_good": 10,
			"dli_light_max_good": 60,
			"dli_light_min_acceptable": 0,
			"dli_light_max_acceptable": 80,
			"moisture_min_good": 0,
			"moisture_max_good": 85,
			"moisture_min_acceptable": 0,
			"moisture_max_acceptable": 85,
			"salinity_min_good": 0,
			"salinity_max_good": 100,
			"salinity_min_acceptable": 0,
			"salinity_max_acceptable": 100,
			"watering_cycles_until_fertilisation": 100,
			"days_until_fertilisation": 100,
			"id": 185687,
			"thresholds_type": "harvest",
			"photoperiod": "day_neutral"
		},
		{
			"ph_min": 0,
			"ph_max": 14,
			"temperature_min_good": -10,
			"temperature_max_good": 50,
			"temperature_min_acceptable": -10,
			"temperature_max_acceptable": 50,
			"light_min_good": 0,
			"light_max_good": 3000,
			"light_min_acceptable": 0,
			"light_max_acceptable": 3000,
			"dli_light_min_good": 10,
			"dli_light_max_good": 60,
			"dli_light_min_acceptable": 0,
			"dli_light_max_acceptable": 80,
			"moisture_min_good": 0,
			"moisture_max_good": 85,
			"moisture_min_acceptable": 0,
			"moisture_max_acceptable": 85,
			"salinity_min_good": 0,
			"salinity_max_good": 100,
			"salinity_min_acceptable": 0,
			"salinity_max_acceptable": 100,
			"watering_cycles_until_fertilisation": 100,
			"days_until_fertilisation": 100,
			"id": 185786,
			"thresholds_type": "harvest",
			"photoperiod": "short_day"
		},
		{
			"ph_min": 0,
			"ph_max": 14,
			"temperature_min_good": -10,
			"temperature_max_good": 50,
			"temperature_min_acceptable": -10,
			"temperature_max_acceptable": 50,
			"light_min_good": 0,
			"light_max_good": 3000,
			"light_min_acceptable": 0,
			"light_max_acceptable": 3000,
			"dli_light_min_good": 10,
			"dli_light_max_good": 60,
			"dli_light_min_acceptable": 0,
			"dli_light_max_acceptable": 80,
			"moisture_min_good": 0,
			"moisture_max_good": 85,
			"moisture_min_acceptable": 0,
			"moisture_max_acceptable": 85,
			"salinity_min_good": 0,
			"salinity_max_good": 100,
			"salinity_min_acceptable": 0,
			"salinity_max_acceptable": 100,
			"watering_cycles_until_fertilisation": 100,
			"days_until_fertilisation": 100,
			"id": 185775,
			"thresholds_type": "harvest",
			"photoperiod": "long_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 17,
			"temperature_max_good": 36,
			"temperature_min_acceptable": 10,
			"temperature_max_acceptable": 42,
			"light_min_good": 23,
			"light_max_good": 690,
			"light_min_acceptable": 11.5,
			"light_max_acceptable": 920,
			"dli_light_min_good": 6,
			"dli_light_max_good": 30,
			"dli_light_min_acceptable": 0.03,
			"dli_light_max_acceptable": 40,
			"moisture_min_good": 0,
			"moisture_max_good": 0,
			"moisture_min_acceptable": 0,
			"moisture_max_acceptable": 0,
			"salinity_min_good": 0,
			"salinity_max_good": 0,
			"salinity_min_acceptable": 0,
			"salinity_max_acceptable": 0,
			"watering_cycles_until_fertilisation": 190,
			"days_until_fertilisation": 190,
			"id": 181988,
			"thresholds_type": "winter",
			"photoperiod": "ornamental"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 17,
			"temperature_max_good": 36,
			"temperature_min_acceptable": 10,
			"temperature_max_acceptable": 42,
			"light_min_good": 150,
			"light_max_good": 1600,
			"light_min_acceptable": 11.5,
			"light_max_acceptable": 1840,
			"dli_light_min_good": 10,
			"dli_light_max_good": 45,
			"dli_light_min_acceptable": 0.03,
			"dli_light_max_acceptable": 60,
			"moisture_min_good": 35,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 25,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 1,
			"salinity_max_good": 1.3,
			"salinity_min_acceptable": 0.6,
			"salinity_max_acceptable": 1.6,
			"watering_cycles_until_fertilisation": 3,
			"days_until_fertilisation": 15,
			"id": 181987,
			"thresholds_type": "summer",
			"photoperiod": "ornamental"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 17,
			"temperature_max_good": 36,
			"temperature_min_acceptable": 10,
			"temperature_max_acceptable": 42,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 0.6,
			"salinity_max_good": 1,
			"salinity_min_acceptable": 0.4,
			"salinity_max_acceptable": 1.2,
			"watering_cycles_until_fertilisation": 7,
			"days_until_fertilisation": 21,
			"id": 185684,
			"thresholds_type": "germination",
			"photoperiod": "short_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 1,
			"salinity_max_good": 1.3,
			"salinity_min_acceptable": 0.6,
			"salinity_max_acceptable": 1.6,
			"watering_cycles_until_fertilisation": 3,
			"days_until_fertilisation": 15,
			"id": 185722,
			"thresholds_type": "seedling",
			"photoperiod": "day_neutral"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 1,
			"salinity_max_good": 1.3,
			"salinity_min_acceptable": 0.6,
			"salinity_max_acceptable": 1.6,
			"watering_cycles_until_fertilisation": 3,
			"days_until_fertilisation": 15,
			"id": 185746,
			"thresholds_type": "seedling",
			"photoperiod": "short_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 100,
			"light_max_good": 300,
			"light_min_acceptable": 75,
			"light_max_acceptable": 400,
			"dli_light_min_good": 10,
			"dli_light_max_good": 15,
			"dli_light_min_acceptable": 1,
			"dli_light_max_acceptable": 20,
			"moisture_min_good": 40,
			"moisture_max_good": 70,
			"moisture_min_acceptable": 30,
			"moisture_max_acceptable": 80,
			"salinity_min_good": 1,
			"salinity_max_good": 1.3,
			"salinity_min_acceptable": 0.6,
			"salinity_max_acceptable": 1.6,
			"watering_cycles_until_fertilisation": 3,
			"days_until_fertilisation": 15,
			"id": 185672,
			"thresholds_type": "seedling",
			"photoperiod": "long_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 300,
			"light_max_good": 700,
			"light_min_acceptable": 250,
			"light_max_acceptable": 800,
			"dli_light_min_good": 30,
			"dli_light_max_good": 45,
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185671,
			"thresholds_type": "vegetative",
			"photoperiod": "day_neutral"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 300,
			"light_max_good": 700,
			"light_min_acceptable": 250,
			"light_max_acceptable": 800,
			"dli_light_min_good": 30,
			"dli_light_max_good": 45,
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185718,
			"thresholds_type": "vegetative",
			"photoperiod": "short_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 300,
			"light_max_good": 700,
			"light_min_acceptable": 250,
			"light_max_acceptable": 800,
			"dli_light_min_good": 30,
			"dli_light_max_good": 45,
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185744,
			"thresholds_type": "vegetative",
			"photoperiod": "long_day"
		},
		{
			"ph_min": 4,
			"ph_max": 7,
			"temperature_min_good": 15,
			"temperature_max_good": 30,
			"temperature_min_acceptable": 8,
			"temperature_max_acceptable": 35,
			"light_min_good": 500,
			"light_max_good": 700,
			"light_min_acceptable": 400,
			"light_max_acceptable": 800,
			"dli_light_min_good": 30,
			"dli_light_max_good": 45,
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185738,
			"thresholds_type": "flowering",
			"photoperiod": "day_neutral"
		},
		{
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185777,
			"thresholds_type": "flowering",
			"photoperiod": "short_day"
		},
		{
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
			"watering_cycles_until_fertilisation": 1,
			"days_until_fertilisation": 7,
			"id": 185703,
			"thresholds_type": "flowering",
			"photoperiod": "long_day"
		},
		{
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
			"days_until_fertilisation": 15,
			"id": 185739,
			"thresholds_type": "maturing",
			"photoperiod": "day_neutral"
		},
		{
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
			"days_until_fertilisation": 15,
			"id": 185770,
			"thresholds_type": "maturing",
			"photoperiod": "short_day"
		},
		{
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
			"days_until_fertilisation": 15,
			"id": 185713,
			"thresholds_type": "maturing",
			"photoperiod": "long_day"
		}
	]
}
     */
}
