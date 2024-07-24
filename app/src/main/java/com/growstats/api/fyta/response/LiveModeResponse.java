package com.growstats.api.fyta.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growstats.api.fyta.objects.plantdetails.AbsoluteValues;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveModeResponse {
    public LiveModeMeasurement measurements;
    public LiveModeStatuses statuses;
    public LiveModeThresholds thresholds;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LiveModeMeasurement {
        public Integer light;
        public String light_formatted;
        public String sensor_id;
        public Float soil_fertility;
        public Integer soil_moisture;
        public String soil_moisture_formatted;
        public Integer temperature;
        public String temperature_formatted;
        public Integer temperature_unit;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LiveModeStatuses {
        public Integer light;
        public Integer soil_fertility;
        public Integer soil_moisture;
        public Integer temperature;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LiveModeThresholds {
        public LiveModeMeasurementThresholdData light;
        public LiveModeMeasurementThresholdData moisture;
        public LiveModeMeasurementThresholdData salinity;
        public LiveModeMeasurementThresholdData temperature;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LiveModeMeasurementThresholdData {
        public AbsoluteValues absolute_values;
        public Integer status;
        public String unit;
        public MeasurementValues values;

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MeasurementValues {
        public String current;
        public String currentFormatted;
        public String max_acceptable;
        public String max_good;
        public String min_acceptable;
        public String min_good;
        public String optimal_hours;
    }


}
