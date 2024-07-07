package com.growstats.api.fyta.api;

import com.growstats.api.fyta.response.AuthResponse;
import com.growstats.api.fyta.response.GetPlantDetailsResponse;
import com.growstats.api.fyta.response.GetUserPlantsResponse;
import com.growstats.api.security.AuthenticationInterceptor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface FytaApiService
{
    @Headers(AuthenticationInterceptor.ENDPOINT_SECURITY_TYPE_BEARER)
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    /*
    # FYTA Public API

## Auth API

URL: [https://web.fyta.de/api/auth/login](http://web.fyta.de/api/auth/login)

Method: POST

Body:

```json
{
    "email": "example@example.com",
    "password": "examplepassword"
}
```

Response:

```json
{
    "access_token": "111111111111111111111111111111111111111",
    "token_type": "Bearer",
    "expires_in": 5184000,
    "refresh_token": "2222222222222222222222222222222222222222",
    "scope": "mobile"
}
```

The requests below require a Bearer authentication token.

*/

    @POST("/api/auth/login")
    Call<AuthResponse> auth(String user, String pw);

    /*

## Get User Plants

URL: [https://](http://web.fyta.de/api/auth/login)[web.fyta.de/api/user-plant](https://web.fyta.de/api/user-plant)

Method: GET

Response:

```json
{
  "gardens": [
    {
      "id": 123,
      "garden_name": "Home",
      "origin_path": null,
      "thumb_path": null,
      "mac_address": null
    }
  ],
  "plants": [
    {
      "id": 12,
      "nickname": "Ficus benjamina 1",
      "scientific_name": "Ficus benjamina",
      "status": 2,
      "plant_id": 201,
      "family_id": null,
      "wifi_status": 1,
      "thumb_path": "<url>",
      "origin_path": "<url>",
      "plant_thumb_path": "<url>",
      "plant_origin_path": "<url>",
      "received_data_at": "2023-01-00 10:10:00",
      "temperature_optimal_hours": 22,
      "light_optimal_hours": 0,
      "temperature_status": 2,
      "light_status": 1,
      "moisture_status": 3,
      "salinity_status": 2,
      "garden": {
        "id": 123
      },
      "sensor": {
        "id": "AA:AA:AA:2B:AF:F4",
        "has_sensor": true,
        "status": 1,
        "uuid_android": null,
        "uuid_ios": "4AAAAAA6F-0457-3233-8A43-032B5377E763",
        "version": "0.30.0",
        "is_battery_low": false,
        "received_data_at": "2023-01-01 10:10:00"
      },
      "hub": {
        "id": 123,
        "hub_id": "AA:AA:AA:27:7D:6A",
        "status": 1,
        "received_data_at": "2023-01-01 01:10:01",
        "reached_hub_at": "2023-01-01 10:10:01"
      }
    }
  ]
}
```
*/
    @Headers(AuthenticationInterceptor.ENDPOINT_SECURITY_TYPE_BEARER)
    @GET("/api/user-plant")
    Call<GetUserPlantsResponse> getUserPlants();
    /*

## Get Details by User Plant ID

URL: [https://web.fyta.de/api/user-plant/[plantID]](https://web.fyta.de/api/user-plant/%5BplantID%5D)

Method: GET

```json
{
  "plant": {
    "id": 12,
    "nickname": "Ficus benjamina 1",
    "scientific_name": "Ficus benjamina",
    "genus": null,
    "status": 2,
    "plant_id": 201,
    "family_id": null,
    "thumb_path": "<url>",
    "origin_path": "<url>",
    "plant_thumb_path": "<url>",
    "plant_origin_path": "<url>",
    "received_data_at": "2023-01-01 10:10:01",
    "gathering_data": false,
    "is_illegal": false,
    "not_supported": false,
    "sensor_update_available": false,
    "garden": {
      "id": 123,
      "name": "Home"
    },
    "sensor": {
      "id": "AA:AA:AA:2B:AF:F4",
      "has_sensor": true,
      "status": 1,
      "uuid_android": null,
      "uuid_ios": "AAAAA6BF-0457-3233-8A43-032B5377E763",
      "version": "0.30.0",
      "is_battery_low": false,
      "received_data_at": "2023-01-01 10:10:00",
      "created_at": "2022-01-01 01:10:10"
    },
    "hub": {
      "id": 123,
      "hub_id": "AA:AA:AA:27:7D:6A",
      "status": 1,
      "received_data_at": "2023-01-00 10:10:00",
      "reached_hub_at": "2023-01-00 10:10:00"
    },
    "measurements": {
      "ph": {
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
      "temperature": {
        "status": 2,
        "values": {
          "min_good": "17",
          "max_good": "36",
          "min_acceptable": "10",
          "max_acceptable": "42",
          "current": "18",
          "currentFormatted": "18",
          "optimal_hours": 22
        },
        "unit": "°C/h",
        "absolute_values": {
          "min": "0",
          "max": "50",
          "minText": "0",
          "maxText": "50"
        }
      },
      "light": {
        "status": 1,
        "values": {
          "min_good": "20",
          "max_good": "450",
          "min_acceptable": "18",
          "max_acceptable": "675",
          "current": "2",
          "currentFormatted": "2",
          "optimal_hours": 0
        },
        "dli_values": {
          "min_good": "0.25",
          "max_good": "9",
          "min_acceptable": "0.06",
          "max_acceptable": "9"
        },
        "unit": "μmol/h",
        "dli_unit": "mol/day",
        "absolute_values": {
          "min": "0",
          "max": "700",
          "minText": "0",
          "maxText": "700"
        }
      },
      "moisture": {
        "status": 3,
        "values": {
          "min_good": "35",
          "max_good": "70",
          "min_acceptable": "25",
          "max_acceptable": "80",
          "current": "61",
          "currentFormatted": "61"
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
        "status": 2,
        "values": {
          "min_good": "0.6",
          "max_good": "1",
          "min_acceptable": "0.4",
          "max_acceptable": "1.2",
          "current": "1",
          "currentFormatted": "0.50"
        },
        "unit": "mS/h",
        "absolute_values": {
          "min": "0",
          "max": "1.4",
          "minText": "0",
          "maxText": "1.4"
        }
      },
      "battery": "100"
    },
    "temperature_unit": 1,
    "know_hows": []
  }
}
```

*/
    @Headers(AuthenticationInterceptor.ENDPOINT_SECURITY_TYPE_BEARER)
    @GET("/api/user-plant/{id}")
    Call<GetPlantDetailsResponse> getPlantDetails(@Path(value = "id",encoded = true)int id);
    /*
## Get Measurements by User PlantID

[https://web.fyta.de/api/user-plant/measurements/[plantID]](https://web.fyta.de/api/user-plant/measurements/%5BplantID%5D)

Method: POST

Body:

```json
{
  "search": {
    "timeline": "week"
  }
}
```

Accepted timeline values: “hour”, “day”, “week”, “month”, defaulting to month

Response:

```json
{
  "measurements": [
    {
      "light": 1,
      "temperature": 18,
      "soil_moisture": 61,
      "soil_moisture_anomaly": false,
      "soil_fertility": 0.5,
      "soil_fertility_anomaly": false,
      "date_utc": "2023-01-01 01:00:00"
    }
    .....
  ],
  "dli_light": [
    {
      "dli_light": 0.04,
      "date_utc": "2023-01-01 00:00:00"
    },
    .....
  ],
  "absolute_values": {
    "light": {
      "min": "0",
      "minText": "0",
      "max": "700",
      "maxText": "700"
    },
    "dli_light": {
      "min": "0",
      "minText": "0",
      "max": "12.0",
      "maxText": "12.0"
    },
    "temperature": {
      "min": "0",
      "minText": "0",
      "max": "50",
      "maxText": "50"
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
      "max": "1.4",
      "maxText": "1.4"
    }
  },
  "thresholds": {
    "ph_min": 4,
    "ph_max": 7,
    "temperature_min_good": 17,
    "temperature_max_good": 36,
    "temperature_min_acceptable": 10,
    "temperature_max_acceptable": 42,
    "light_min_good": 20,
    "light_max_good": 450,
    "light_min_acceptable": 18,
    "light_max_acceptable": 675,
    "dli_light_min_good": 0.25,
    "dli_light_max_good": 9,
    "dli_light_min_acceptable": 0.06,
    "dli_light_max_acceptable": 9,
    "moisture_min_good": 35,
    "moisture_max_good": 70,
    "moisture_min_acceptable": 25,
    "moisture_max_acceptable": 80,
    "salinity_min_good": 0.6,
    "salinity_max_good": 1,
    "salinity_min_acceptable": 0.4,
    "salinity_max_acceptable": 1.2
  }
}
```

## FYTA User Plant Status

Field `status` represents overall plant status.

| Value | Description |
| --- | --- |
| 0 | User Plant deleted |
| 1 | User Plant good status |
| 2 | User Plant bad status |
| 3 | User Plant no sensor |

## FYTA Measurement Status

Field `status` represents current measurement for **light**, **temperature**, **moisture**, **salinity** status.

| Value | Description |
| --- | --- |
| 0 | No Data |
| 1 | Too Low |
| 2 | Low |
| 3 | Perfect |
| 4 | High |
| 5 | Too High |

## FYTA Sensor Status

Field `status` represents sensor status.

| Value | Description |
| --- | --- |
| 0 | none - When plant does not have sensor |
| 1 | correct - When plant has sensor, last measurement was up to 1,5h ago OR measurement wasn't sent but sensor was created up to 1,5h ago |
| 2 | error - When measurement wasn't sent, when measurement was sent more than 1,5h ago |

## FYTA Hub Status

Field `status` represents Hub status.

| Value | Description |
| --- | --- |
| 1 | correct - when plant has sensor, last measurement was sent by hub, last measurement was up to 1,5h ago |
| 2 | error - last measurement is less than 1,5h ago
 |

## FYTA Wifi Status

Field `wifi_status` represents wifi status.

| Value | Description |
| --- | --- |
| null | Never connected to any hub or user doesnt have any hub or plant doesnt have sensor |
| 0 | Lost connection to all previously connected hubs |
| 1 | Is connected to at least one hub
 |

## FYTA Temperature unit

Field `temperature_unit` represents unit system.

| Value | Description |
| --- | --- |
| 1 | Celsius |
| 2 | Fahrenheit |
    */

}
