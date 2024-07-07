package com.growstats.api.fyta.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growstats.api.fyta.objects.plantdetails.PlantDetails;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPlantDetailsResponse {

    @JsonProperty("plant")
    public PlantDetails plant;
    /*
{
	"plant": {
		"id": 52059,
		"airtable_id": 85,
		"nickname": "traini",
		"status": 1,
		"scientific_name": "Cannabis sativa",
		"genus": null,
		"plant_id": 85,
		"is_shared": false,
		"family_id": null,
		"pot_size": 17,
		"drainage": 1,
		"light_factor": 3.3475475311279297,
		"owner": {},
		"peers": [],
		"soil_type_id": 7377,
		"thumb_path": "https://api.prod.fyta-app.de/user-plant/52059/thumb_path?timestamp=2024-06-24%2014%3A51%3A20",
		"origin_path": "https://api.prod.fyta-app.de/user-plant/52059/origin_path?timestamp=2024-06-24%2014%3A51%3A20",
		"plant_thumb_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/thumb/4909/thumb_1651708837476.jpeg",
		"plant_origin_path": "https://s3.eu-central-1.amazonaws.com/de.fyta.prod.media/image/origin/4909/origin_1651708836252.jpeg",
		"received_data_at": "2024-07-07 13:06:33",
		"gathering_data": false,
		"is_illegal": false,
		"not_supported": false,
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
		"sensor_update_available": false,
		"location": "grow_tent",
		"verification": true,
		"is_productive_plant": true,
		"dismissed_sensor_message_at": "2024-06-27",
		"is_harvesting_done": false,
		"available_states": {
			"day_neutral": [
				{
					"id": 185758,
					"grow_stage": "germination",
					"order": 1,
					"projected_date_from": null
				},
				{
					"id": 185722,
					"grow_stage": "seedling",
					"order": 2,
					"projected_date_from": null
				},
				{
					"id": 185671,
					"grow_stage": "vegetative",
					"order": 3,
					"projected_date_from": null
				},
				{
					"id": 185738,
					"grow_stage": "flowering",
					"order": 4,
					"projected_date_from": null
				},
				{
					"id": 185739,
					"grow_stage": "maturing",
					"order": 5,
					"projected_date_from": null
				},
				{
					"id": 185687,
					"grow_stage": "harvest",
					"order": 6,
					"projected_date_from": "2024-07-14T00:00:00.000Z"
				}
			],
			"short_day": [
				{
					"id": 185684,
					"grow_stage": "germination",
					"order": 1
				},
				{
					"id": 185746,
					"grow_stage": "seedling",
					"order": 2
				},
				{
					"id": 185718,
					"grow_stage": "vegetative",
					"order": 3
				},
				{
					"id": 185777,
					"grow_stage": "flowering",
					"order": 4
				},
				{
					"id": 185770,
					"grow_stage": "maturing",
					"order": 5
				},
				{
					"id": 185786,
					"grow_stage": "harvest",
					"order": 6
				}
			],
			"long_day": [
				{
					"id": 185714,
					"grow_stage": "germination",
					"order": 1
				},
				{
					"id": 185672,
					"grow_stage": "seedling",
					"order": 2
				},
				{
					"id": 185744,
					"grow_stage": "vegetative",
					"order": 3
				},
				{
					"id": 185703,
					"grow_stage": "flowering",
					"order": 4
				},
				{
					"id": 185713,
					"grow_stage": "maturing",
					"order": 5
				},
				{
					"id": 185775,
					"grow_stage": "harvest",
					"order": 6
				}
			]
		},
		"growth_stage_updated_at": "2024-06-30 14:32:52",
		"grow_stage": "maturing",
		"seed_type": "day_neutral",
		"garden": {
			"id": 5567,
			"name": "Zelt"
		},
		"sensor": {
			"id": "C4:7D:E3:B9:0E:36",
			"has_sensor": true,
			"status": 1,
			"uuid_android": null,
			"uuid_ios": null,
			"version": "1.5.0",
			"light_factor": 3.3475475311279297,
			"probe_length_id": 2,
			"moisture_factor": 1.036834955215454,
			"is_battery_low": false,
			"received_data_at": "2024-07-07 13:06:33",
			"created_at": "2024-06-26 15:22:31",
			"has_sensor_update": false
		},
		"hub": {
			"id": 11106,
			"hub_id": "34:B7:DA:E8:48:26",
			"hub_name": "Zelt",
			"status": 1,
			"received_data_at": "2024-07-07 13:06:33",
			"reached_hub_at": "2024-07-07 13:10:29"
		},
		"missing": [],
		"measurements": {
			"ph": {
				"type": "ph",
				"status": null,
				"values": {
					"min": "4",
					"max": "7",
					"current": null
				},
				"unit": "pH",
				"absolute_values": {
					"min": "0",
					"max": "7.5",
					"minText": "0",
					"maxText": "7.5"
				}
			},
			"nutrients": {
				"type": "nutrients",
				"status": 3
			},
			"temperature": {
				"type": "temperature",
				"status": 3,
				"values": {
					"min_good": "15",
					"max_good": "30",
					"min_acceptable": "8",
					"max_acceptable": "35",
					"current": "25",
					"currentFormatted": "25",
					"optimal_hours": 0
				},
				"unit": "°C/h",
				"absolute_values": {
					"min": "0",
					"max": "40",
					"minText": "0",
					"maxText": "40"
				}
			},
			"light": {
				"type": "light",
				"status": 3,
				"values": {
					"min_good": "400",
					"max_good": "1000",
					"min_acceptable": "300",
					"max_acceptable": "1200",
					"current": "372",
					"currentFormatted": "372",
					"optimal_hours": 0
				},
				"dli_values": {
					"min_good": "30",
					"max_good": "40",
					"min_acceptable": "20",
					"max_acceptable": "50"
				},
				"unit": "μmol/h",
				"dli_unit": "mol/day",
				"absolute_values": {
					"min": "0",
					"max": "1220",
					"minText": "0",
					"maxText": "1220"
				}
			},
			"moisture": {
				"type": "moisture",
				"status": 3,
				"values": {
					"min_good": "35",
					"max_good": "70",
					"min_acceptable": "25",
					"max_acceptable": "80",
					"current": "44",
					"currentFormatted": "44"
				},
				"unit": "%/h",
				"absolute_values": {
					"min": "0",
					"max": "85",
					"minText": "0",
					"maxText": "85"
				}
			},
			"salinity": {
				"type": "salinity",
				"status": 5,
				"values": {
					"min_good": "1",
					"max_good": "1.3",
					"min_acceptable": "0.6",
					"max_acceptable": "1.6",
					"current": "3",
					"currentFormatted": "2.91"
				},
				"unit": "mS/cm/h",
				"absolute_values": {
					"min": "0",
					"max": "3.2",
					"minText": "0",
					"maxText": "3.2"
				}
			},
			"battery": "75"
		},
		"temperature_unit": 1,
		"know_hows": [],
		"device_menu": {
			"actions": [
				{
					"action": "data_collected",
					"title": "2024-07-07 13:06:33",
					"description": "",
					"priority": 6
				}
			],
			"buttons": [
				"sensor",
				"hub"
			],
			"live_mode_enabled": true,
			"diagnose_enabled": true
		}
	}
}
     */
}
