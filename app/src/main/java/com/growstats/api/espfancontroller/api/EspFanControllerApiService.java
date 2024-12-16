package com.growstats.api.espfancontroller.api;
import com.growstats.api.espfancontroller.objects.EspSettingsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EspFanControllerApiService {
    @GET("/cmd?var=speed")
    Call<ResponseBody> setFanSpeed(@Query("val") int speed, @Query("id") int id);

    //`${host}/cmd?var=voltage&min=${min}&max=${max}&id=0`;
    @GET("/cmd?var=voltage")
    Call<ResponseBody> setFanVoltage(@Query("min") int min, @Query("max") int max,@Query("id") int id);

    //`${host}/cmd?var=autovals&temp=${temp}&hum=${hum}&speeddif=${speed}`;
    @GET("/cmd?var=autovals")
    Call<ResponseBody> setFanAutoTargetValues(@Query("temp") int temptarget, @Query("hum") int humtarget,@Query("speeddif") int speeddif);

    //`${host}/cmd?var=temphumdif&temp=${temp}&hum=${hum}`;
    @GET("/cmd?var=temphumdif")
    Call<ResponseBody> setTempHumDif(@Query("temp") int temp, @Query("hum") int hum);

    //`${host}/cmd?var=autocontrol&val=${val}`;
    @GET("/cmd?var=autocontrol")
    Call<ResponseBody> setFanAutoControl(@Query("val") int autocontrol);

    //`${host}/cmd?var=readgovee&val=${val}`
    @GET("/cmd?var=readgovee")
    Call<ResponseBody> readDataFromGovee(@Query("val") boolean read);

    //`${host}/cmd?var=autospeed&min=${min}&max=${max}`;
    @GET("/cmd?var=autospeed")
    Call<ResponseBody> setFanAutoSpeed(@Query("min") int min,@Query("max") int max);

    //`${host}/cmd?var=fannightmode&onh=${onhour}&onm=${onmin}&offh=${offhour}&offm=${offmin}&mspeed=${mspeed}`;
    @GET("/cmd?var=fannightmode")
    Call<ResponseBody> setFanNightModeTimes(@Query("onh") int onhour,@Query("onm") int onmin, @Query("offh") int offh,@Query("offm") int offm,@Query("mspeed") int mspeed);
    //`${host}/cmd?var=fannightmodeactive&nighton=${val}`;
    @GET("/cmd?var=fannightmodeactive")
    Call<ResponseBody> setFanNightModeActive(@Query("val") int read);
    //`${host}/cmd?var=lightvoltage&min=${min}&max=${max}`;
    @GET("/cmd?var=lightvoltage")
    Call<ResponseBody> setLightVoltage(@Query("min") int min,@Query("max") int max);

    @GET("/cmd?var=lightlimitsp")
    Call<ResponseBody> setLightLimitsPercent(@Query("min") int min,@Query("max") int max);

    //`${host}/cmd?var=lightsettime&onh=${onh}&onmin=${onmin}&offh=${offh}&offmin=${offmin}&riseh=${riseh}&risemin=${risemin}&seth=${seth}&setmin=${setmin}&riseenable=${enablerise}&setenable=${enableset}`
    @GET("/cmd?var=lightsettime")
    Call<ResponseBody> setLightNightModeTimes(@Query("onh") int onhour,
                                            @Query("onm") int onmin,
                                            @Query("offh") int offh,
                                            @Query("offm") int offm,
                                            @Query("riseh") int riseh,
                                            @Query("risemin") int risemin,
                                            @Query("seth") int seth,
                                            @Query("setmin") int setmin,
                                            @Query("riseenable") boolean riseenable,
                                            @Query("setenable") boolean setenable);

    //`${host}/cmd?var=lightautomode&enable=${val}`;
    @GET("/cmd?var=lightautomode")
    Call<ResponseBody> setLightAutoControl(@Query("val") int autocontrol);

    @GET("/settings")
    Call<EspSettingsResponse> getSettings();

    @GET("/{year}/{month}/{day}/{hour}.csv")
    Call<ResponseBody> getCSVData(@Path("year")String year, @Path("month") String month, @Path("day")String day, @Path("hour")String hour);

}
