package com.growstats.api.fyta.objects;

import com.growstats.api.fyta.objects.plantdetails.AbsoluteValues;

public class LiveModeResponse
{
    public LiveModeMeasurement measurements;
    public LiveModeStatuses statuses;
    public LiveModeThresholds thresholds;


    public static final class LiveModeMeasurement {
        public static final int $stable = 0;
        public Integer light;
        public String sensor_id;
        public Float soil_fertility;
        public Integer soil_moisture;
        public Integer temperature;

    }

    public static final class LiveModeStatuses {
        public static final int $stable = 0;
        public Integer light;
        public Integer soil_fertility;
        public Integer soil_moisture;
        public Integer temperature;

    }

    public static final class LiveModeThresholds {
        public static final int $stable = 8;
        public LiveModeMeasurementThresholdData light;
        public LiveModeMeasurementThresholdData moisture;
        public LiveModeMeasurementThresholdData salinity;
        public LiveModeMeasurementThresholdData temperature;

    }

    /* loaded from: classes2.dex */
    public static final class LiveModeMeasurementThresholdData {
        public static final int $stable = 8;
        public AbsoluteValues absolute_values;
        public Integer status;
        public String unit;
        public MeasurementValues values;

    }

    public final class MeasurementValues{
        public String current;
        public String currentFormatted;
        public String max_acceptable;
        public String max_good;
        public String min_acceptable;
        public String min_good;
        public String optimal_hours;
    }


    }
