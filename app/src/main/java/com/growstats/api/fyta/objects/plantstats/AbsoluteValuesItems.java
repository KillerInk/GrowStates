package com.growstats.api.fyta.objects.plantstats;

import com.growstats.api.fyta.objects.plantdetails.AbsoluteValues;

/*
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
 */
public class AbsoluteValuesItems {
    public AbsoluteValues light;
    public AbsoluteValues dli_light;
    public AbsoluteValues temperature;
    public AbsoluteValues soil_moisture;
    public AbsoluteValues soil_fertility;
}
