package com.growstats.controller;


import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import kotlin.UByte;

public class BeamData
{
    /*
            byte tempbot = UByte.bytes(bArr[0]);
            byte temptop = UByte.bytes(bArr[1]);
            byte tempdie = UByte.bytes(bArr[2]);
            byte tempmin = UByte.bytes(bArr[3]);
            byte tempmax = UByte.bytes(bArr[4]);
            byte salinity = UByte.bytes(bArr[5]);
            byte moisture = UByte.bytes(bArr[6]);
            byte light = UByte.bytes(bArr[7]);
            byte[] copyOfRange = ArraysKt.copyOfRange(bArr, 8, 10);
            byte[] copyOfRange2 = ArraysKt.copyOfRange(bArr, 10, 12);
            byte[] copyOfRange3 = ArraysKt.copyOfRange(bArr, 12, 14);
            byte[] copyOfRange4 = ArraysKt.copyOfRange(bArr, 14, 16);
            byte[] copyOfRange5 = ArraysKt.copyOfRange(bArr, 16, 18);
            byte battery = UByte.bytes(bArr[18]);
            byte powerstate = UByte.bytes(bArr[19]);
            int lightWhite = m9485getUIntTwoAtxfHcF5w(copyOfRange, 0);
            int lightRed = m9485getUIntTwoAtxfHcF5w(copyOfRange2, 0);
            int lightGreen = m9485getUIntTwoAtxfHcF5w(copyOfRange3, 0);
            int lightBlue = m9485getUIntTwoAtxfHcF5w(copyOfRange4, 0);
            int lightNir = m9485getUIntTwoAtxfHcF5w(copyOfRange5, 0);

         */
    //  [126, 124, 0, 70, -84, -126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -49, 32]
    //   7E 7C 00 46 AC 82 00 00 00 00 00 00 00 00 00 00 CF 20
    //  84,82,00,69,BF,1F,4A,46,19,E4,29,65,10,1B,02,41,C9,20,

    /*
     88,88,00,50,BD,A7,16,93,07,66,0D,1F,04,09,00,CC,D1,20,
     0 "temperature_bot":"136"
     1 "temperature_top":"136"
     2 "temperature_die":"0"
     3 "soil_fertility":"80"
     4 "soil_moisture":"189"
     5 "light":"167"
     6+7"white_light":"5779"
     8+9"red_light":"1894"
     10-11"green_light":"3359"
     12-13"blue_light":"1033"
     14-15"nir_light":"204"
     16 "battery":"209"
     17 "power_state":"32"
        {"measurement":{"battery":"209","blue_light":"1033","green_light":"3359","light":"167","nir_light":"204","power_state":"32","red_light":"1894","sensor_id":"11:22:33:44:55:66","soil_fertility":"80","soil_moisture":"189","temperature_bot":"136","temperature_die":"0","temperature_top":"136","white_light":"5779"}}
     */

    private int getIntFromBytes(byte[] data, int one,int two)
    {
        return (((data[one] & 0xFF) << 8) | (data[two] & 0xFF));
    }
    //private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public BeamData(byte[] data,String mac)
    {
        id = mac;
        temperatureBottom = Byte.toUnsignedInt(data[0])&255;
        temperatureTop =  Byte.toUnsignedInt(data[1])&255;
        temperatureDie =  Byte.toUnsignedInt(data[2])&255;
        //not used in final call to http service
        //temperatureMin =  Byte.toUnsignedInt(data[3])&255;
        //temperatureMax =  Byte.toUnsignedInt(data[4])&255;
        salinity =  Byte.toUnsignedInt(data[3])&255;
        moisture =  Byte.toUnsignedInt(data[4])&255;
        light =  Byte.toUnsignedInt(data[5])&255;
        lightWhite = getIntFromBytes (data,6,7);
        lightRed = getIntFromBytes(data,8,9);
        lightGreen = getIntFromBytes(data,10,11);
        lightBlue = getIntFromBytes(data,12,13);
        lightNIR = getIntFromBytes(data,14,15);
        battery = Byte.toUnsignedInt(data[16]);
        powerState = Byte.toUnsignedInt(data[17]);
    }
    public int battery;
    public int light;
    public int lightBlue;
    public int lightGreen;
    public int lightNIR;
    public int lightRed;
    public int lightWhite;
    public int moisture;
    public int powerState;
    public int salinity;
    public int temperatureBottom;
    public int temperatureDie;
    public int temperatureMax;
    public int temperatureMin;
    public int temperatureTop;
    public String id;
    //seems not necessary for the final request to get the translated data due https request
    //public String timestamp;
    //public String timezone;

    @NonNull
    @Override
    public String toString() {
        return " light " +light + " blue " +lightBlue + " red " + lightRed + " green " +lightGreen + " nir " + lightNIR + " moisture " + moisture + " salinity " + salinity + " temp bot " +temperatureBottom + " temp die " + temperatureDie+
                " temp max " + temperatureMax + " temp min " + temperatureMin + " temp top "+ temperatureTop;
    }
}
