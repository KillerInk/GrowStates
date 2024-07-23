package com.growstats.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.growstats.MainActivity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.content.IntentFilter;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.Provides;
import dagger.hilt.android.qualifiers.ActivityContext;

public class BtController {
    private final String TAG = BtController.class.getName().toString();
    private BluetoothAdapter btAdapter;
    private AppCompatActivity mainActivity;
    private ActivityResultLauncher<String> mPermissionResult;
    private HashMap<String, BtClient> btClientHashMap;
    public Events eventsListner;

    public interface Events
    {
        void onFoundDevice(String mac);
    }

    public BtClient getClient(String mac)
    {
        return btClientHashMap.get(mac);
    }

    @Inject
    public BtController(@ActivityContext Context context) {
        btClientHashMap = new HashMap<>();
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        mainActivity = (AppCompatActivity) context;
        // Permission to bt
        mPermissionResult = mainActivity.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                           startDiscover();
                        } else {
                            Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                        }
                    }
                });

    }


    public void find() {
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mainActivity,new String[]{Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},2);
            return;
        }
        if (btAdapter != null && !btAdapter.isEnabled()) {
            //mPermissionResult.launch(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        } else {
           startDiscover();
        }
    }

    @SuppressLint("MissingPermission")
    private void startDiscover()
    {
        try {
            btAdapter.startDiscovery();
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            mainActivity.registerReceiver(mReceiver, filter);
        }catch (Exception e){

            e.printStackTrace();

        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device != null && device.getName() != null && device.getName().equals("FYTA BEAM"))
                {
                    BtClient client = new BtClient(btAdapter,device.getName(),device.getAddress(), mainActivity);
                    if (!btClientHashMap.containsKey(device.getAddress()))
                        btClientHashMap.put(device.getAddress(),client);
                    if (eventsListner != null)
                        eventsListner.onFoundDevice(device.getAddress());
                }
                if (device != null && device.getName() != null)
                    Log.i(BtController.class.getName().toString(), "Device: " + device.getName().toString());

            }
        }
    };


}
