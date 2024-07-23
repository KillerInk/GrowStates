package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.util.Log;

@SuppressLint("MissingPermission")
public class ChangeMtuCmd implements BluetoothCommand {

    @Override
    public void execute(BluetoothGatt gatt) {
        Log.d(ChangeMtuCmd.class.getName(), "ChangeMtu");
        gatt.requestMtu(27);
    }
}
