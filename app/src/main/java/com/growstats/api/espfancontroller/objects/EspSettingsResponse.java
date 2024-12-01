package com.growstats.api.espfancontroller.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EspSettingsResponse {
    public int fan0voltage;
    public int fan0min;
    public int fan0max;
    public int fan1voltage;
    public int fan1min;
    public int fan1max;
    public boolean autocontrol;
    public int targetTemperature;
    public int targetHumidity;
    public boolean readgovee;
    public int speeddif;
    public int tempdif;
    public int humdif;
    public int minspeed;
    public int maxspeed;

    public boolean nightmodeactive;
    public int nightmodeonhour;
    public int nightmodeonmin;
    public int nightmodeoffmin;
    public int nightmodeoffhour;
    public int nightmodemaxspeed;

    public int lightonh;
    public int lightonmin;

    public int lightoffh;
    public int lightoffmin;

    public int lightriseh;
    public int lightrisemin;

    public int lightseth;
    public int lightsetmin;
    public boolean lightriseenable;
    public boolean lightsetenable;
    public boolean lightautomode;
    public int lightminvolt;
    public int lightmaxvolt;
}
