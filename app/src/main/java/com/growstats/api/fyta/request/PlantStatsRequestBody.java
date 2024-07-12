package com.growstats.api.fyta.request;

import com.growstats.api.fyta.enums.TimeRange;

public class PlantStatsRequestBody {
    public Search search;
    public PlantStatsRequestBody(TimeRange range)
    {
        search = new Search(range);
    }

    public class Search{

        public String timeline;
        public Search(TimeRange range)
        {
            timeline = range.toString();
        }

    }
}
