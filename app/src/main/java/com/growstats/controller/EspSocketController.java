package com.growstats.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growstats.api.MySocket;
import com.growstats.api.MySocketListner;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.growstats.ui.home.TentItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EspSocketController extends MySocketListner {
    private String TAG = EspSocketController.class.getName();

    public interface Event
    {
        void onNewTent(TentItem tentItem);
        void onNewData(float temp, float hum, int fanspeed, int light, float vpd);
    }

    private MySocket webSocket;
    private Event eventListner;
    String url;

    public void setEventListner(Event listner)
    {
        this.eventListner = listner;
    }

    public boolean connected()
    {
        return webSocket != null && webSocket.isOpen();
    }

    public void connect(MySocket socket, Event event,String url)
    {
        Log.d(TAG, "connect to :" + url);
        eventListner = event;
        webSocket = socket;
        webSocket.createNewWebSocket(this);
        this.url = url;
    }

    public void close()
    {
        Log.d(TAG,"disconnect");
        if(webSocket == null)
            return;
        try {
            webSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        webSocket = null;
    }


    @Override
    public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        super.onClosed(webSocket, code, reason);
        Log.d(TAG, "onClosed");
    }

    @Override
    public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        super.onClosing(webSocket, code, reason);
        Log.d(TAG, "onClosing");
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        Log.d(TAG, "onFailure");
        t.printStackTrace();
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
        super.onMessage(webSocket, text);
        try {
            JSONObject response = new JSONObject(text);
            TentItem tentItem = new TentItem();
            String temp ="";
            String hum ="";
            float tempret = 0;
            float humret = 0;
            int lightret =0;
            int fanspeedret = 0;
            float vpdret = 0;
            if(response.has("bme280")) {
                if (response.getJSONObject("bme280").has("temperatur")) {
                    temp = response.getJSONObject("bme280").getString("temperatur") + "°C";
                }
                if (response.getJSONObject("bme280").has("humidity")) {
                    hum = response.getJSONObject("bme280").getString("humidity") + "%";
                }
                if (response.getJSONObject("bme280").has("atemperatur")) {
                    temp += " A:" + response.getJSONObject("bme280").getString("atemperatur") + "°C";
                    tempret = (float) response.getJSONObject("bme280").getDouble("atemperatur");
                }
                if (response.getJSONObject("bme280").has("ahumidity")) {
                    hum += " A:" + response.getJSONObject("bme280").getString("ahumidity") + "%";
                    humret = (float) response.getJSONObject("bme280").getDouble("ahumidity");
                }
                tentItem.setHum(hum);
                tentItem.setTemp(temp);
            }
            if(response.has("ens160aht21")) {
                if (response.getJSONObject("ens160aht21").has("eco2"))
                    tentItem.setEco2(response.getJSONObject("ens160aht21").getString("eco2") + "ppm");
                if (response.getJSONObject("ens160aht21").has("tvoc"))
                    tentItem.setTvoc(response.getJSONObject("ens160aht21").getString("tvoc") + "ppb");
            }
            if(response.has("ens160aht21") && !response.has("bme280")) {
                if (response.getJSONObject("ens160aht21").has("temperatur"))
                    temp = response.getJSONObject("ens160aht21").getString("temperatur") + "°C";
                if (response.getJSONObject("ens160aht21").has("humidity"))
                    hum = response.getJSONObject("ens160aht21").getString("humidity") + "%";
                if (response.getJSONObject("ens160aht21").has("atemperatur"))
                    temp += " A:" + response.getJSONObject("ens160aht21").getString("atemperatur") + "°C";
                if (response.getJSONObject("ens160aht21").has("ahumidity"))
                    hum += " A:" + response.getJSONObject("ens160aht21").getString("ahumidity") + "%";
                tentItem.setHum(hum);
                tentItem.setTemp(temp);
            }
            if(response.has("voltage0"))
                tentItem.setFan0speed(response.getString("voltage0") +"mv");
            if(response.has("voltage1"))
                tentItem.setFan1speed(response.getString("voltage1") +"mv");
            if(response.has("lightvalP")) {
                tentItem.setLightp(response.getString("lightvalP") + "%");

            }
            if(response.has("autocontrolspeed"))
                fanspeedret = response.getInt("autocontrolspeed");
            if(response.has("lightvalmv")) {
                tentItem.setLightmv(response.getString("lightvalmv") + "mv");
                lightret = response.getInt("lightvalmv");
            }
            if(response.has("vpdair")) {
                tentItem.setVdp(response.getString("vpdair") + "kPa");
                vpdret = (float) response.getDouble("vpdair");
            }
            tentItem.setUrl(url);

            if(eventListner != null) {
                eventListner.onNewTent(tentItem);
                eventListner.onNewData(tempret,humret,fanspeedret,lightret,vpdret);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        super.onOpen(webSocket, response);
        Log.d(TAG, "connection open");
    }
}
