package com.growstats.ui.home;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.growstats.BR;
import com.growstats.controller.NavigationController;

public class TentItem  extends BaseObservable {
    private String temp ="";
    private String hum="";
    private String eco2="";
    private String tvoc="";
    private String lightp="";
    private String lightmv="";
    private String fan0speed="";
    private String fan1speed="";
    private String url="";
    private String vdp="";

    private NavigationController navigationController;

    public void setNavigationController(NavigationController navigationController)
    {
        this.navigationController = navigationController;
    }

    public NavigationController getNavigationController() {
        return navigationController;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Bindable
    public String getTemp() {
        return temp;
    }

    public void setTemp(String tmp)
    {
        if(tmp.equals(temp))
            return;
        temp = tmp;
        notifyPropertyChanged(BR.temp);
    }

    @Bindable
    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        if(this.hum.equals(hum))
            return;
        this.hum = hum;
        notifyPropertyChanged(BR.hum);
    }

    @Bindable
    public String getEco2() {
        return eco2;
    }

    public void setEco2(String eco2) {
        if(this.eco2.equals(eco2))
            return;
        this.eco2 = eco2;
        notifyPropertyChanged(BR.eco2);
    }

    @Bindable
    public String getTvoc() {
        return tvoc;
    }

    public void setTvoc(String tvoc) {
        if(this.tvoc.equals(tvoc))
            return;
        this.tvoc = tvoc;
        notifyPropertyChanged(BR.tvoc);
    }

    @Bindable
    public String getLightp() {
        return lightp;
    }

    public void setLightp(String lightp) {
        if(this.lightp.equals(lightp))
            return;
        this.lightp = lightp;
        notifyPropertyChanged(BR.lightp);
    }

    @Bindable
    public String getLightmv() {
        return lightmv;
    }

    public void setLightmv(String lightmv) {
        if(this.lightmv.equals(lightmv))
            return;
        this.lightmv = lightmv;
        notifyPropertyChanged(BR.lightmv);
    }

    @Bindable
    public String getFan0speed() {
        return fan0speed;
    }

    public void setFan0speed(String fan0speed) {
        if(this.fan0speed.equals(fan0speed))
            return;
        this.fan0speed = fan0speed;
        notifyPropertyChanged(BR.fan0speed);
    }

    @Bindable
    public String getFan1speed() {
        return fan1speed;
    }

    public void setFan1speed(String fan1speed) {
        if(this.fan1speed.equals(fan1speed))
            return;
        this.fan1speed = fan1speed;
        notifyPropertyChanged(BR.fan1speed);
    }

    @Bindable
    public String getVdp() {
        return vdp;
    }

    public void setVdp(String vdp) {
        if(vdp.equals(this.vdp))
            return;
        this.vdp = vdp;
        notifyPropertyChanged(BR.vdp);
    }

    void copyFrom(TentItem t)
    {
        setEco2(t.getEco2());
        setHum(t.getHum());
        setLightmv(t.getLightmv());
        setTemp(t.getTemp());
        setTvoc(t.getTvoc());
        setLightp(t.getLightp());
        setFan0speed(t.getFan0speed());
        setFan1speed(t.getFan1speed());
    }
}
