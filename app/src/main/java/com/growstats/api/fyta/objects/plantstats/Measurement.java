package com.growstats.api.fyta.objects.plantstats;

public class Measurement {
    /*
        "light": 637,
        "temperature": 26,
        "soil_moisture": 61,
        "soil_moisture_anomaly": false,
        "soil_fertility": 2.3,
        "soil_fertility_anomaly": false,
        "thresholds_id": 185739,
        "date_utc": "2024-07-10 13:02:00"
     */

    public int light;
    public int temperature;
    public int soil_moisture;
    public boolean soil_moisture_anomaly;
    public float soil_fertility;
    public boolean soil_fertility_anomaly;
    public int thresholds_id;
    public String date_utc;
}
