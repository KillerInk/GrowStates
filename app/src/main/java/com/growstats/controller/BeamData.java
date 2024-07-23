package com.growstats.controller;

import android.util.Log;

import androidx.annotation.NonNull;

public class BeamData
{
    public BeamData(byte[] data)
    {
        temperatureBottom = Byte.toUnsignedInt(data[0])&255;
        temperatureTop =  Byte.toUnsignedInt(data[1])&255;
        temperatureDie =  Byte.toUnsignedInt(data[2])&255;
        temperatureMin =  Byte.toUnsignedInt(data[3])&255;
        temperatureMax =  Byte.toUnsignedInt(data[4])&255;
        salinity =  Byte.toUnsignedInt(data[5])&255;
        moisture =  Byte.toUnsignedInt(data[6])&255;
        light =  Byte.toUnsignedInt(data[7])&255;
        lightWhite = Byte.toUnsignedInt(data[9])&255 | Byte.toUnsignedInt(data[8])&255 << 8;
        lightRed = Byte.toUnsignedInt(data[11])&255 | Byte.toUnsignedInt(data[10])&255 << 8;
        lightGreen = Byte.toUnsignedInt(data[13])&255 | Byte.toUnsignedInt(data[12])&255 << 8;
        lightBlue = Byte.toUnsignedInt(data[15])&255 | Byte.toUnsignedInt(data[14])&255 << 8;
        lightNIR = Byte.toUnsignedInt(data[17])&255 | Byte.toUnsignedInt(data[16])&255 << 8;
        //battery = data[18];
        //powerState = data[19];
    }
    int battery;
    int light;
    int lightBlue;
    int lightGreen;
    int lightNIR;
    int lightRed;
    int lightWhite;
    int moisture;
    int powerState;
    int salinity;
    int temperatureBottom;
    int temperatureDie;
    int temperatureMax;
    int temperatureMin;
    int temperatureTop;

    @NonNull
    @Override
    public String toString() {
        return " light " +light + " blue " +lightBlue + " red " + lightRed + " green " +lightGreen + " nir " + lightNIR + " moisture " + moisture + " salinity " + salinity + " temp bot " +temperatureBottom + " temp die " + temperatureDie+
                " temp max " + temperatureMax + " temp min " + temperatureMin + " temp top "+ temperatureTop;
    }
}
