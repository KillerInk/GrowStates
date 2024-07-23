package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.util.Log;

@SuppressLint("MissingPermission")
public class DiscoverServices implements BluetoothCommand {

    @Override
    public void execute(BluetoothGatt gatt) {
        Log.d(DiscoverServices.class.getName(), "Discover services");
        gatt.discoverServices();
    }
}
