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
    }

    private MySocket webSocket;
    private Event eventListner;
    String url;

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
            if(response.has("temperatur"))
                temp = response.getString("temperatur")+"°C";
            if(response.has("humidity"))
                hum = response.getString("humidity")+"%";
            if(response.has("atemperatur"))
                temp += " A:" + response.getString("atemperatur")+"°C";
            if(response.has("ahumidity"))
                hum += " A:" + response.getString("ahumidity") +"%";
            tentItem.setHum(hum);
            tentItem.setTemp(temp);

            if(response.has("eco2"))
                tentItem.setEco2(response.getString("eco2") +"ppm");

            if(response.has("tvoc"))
                tentItem.setTvoc(response.getString("tvoc") +"ppb");
            if(response.has("voltage0"))
                tentItem.setFan0speed(response.getString("voltage0") +"mv");
            if(response.has("voltage1"))
                tentItem.setFan1speed(response.getString("voltage1") +"mv");
            if(response.has("lightvalP"))
                tentItem.setLightp(response.getString("lightvalP") +"%");
            if(response.has("lightvalmv"))
                tentItem.setLightmv(response.getString("lightvalmv") +"%");
            if(response.has("vpdair"))
                tentItem.setVdp(response.getString("vpdair") +"kPa");
            tentItem.setUrl(url);

            if(eventListner != null)
                eventListner.onNewTent(tentItem);
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
