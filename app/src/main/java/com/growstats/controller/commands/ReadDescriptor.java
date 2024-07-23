package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;

public class ReadDescriptor implements  BluetoothCommand{

    BluetoothGattDescriptor descriptor;
    @SuppressLint("MissingPermission")
    @Override
    public void execute(BluetoothGatt gatt) {
        gatt.readDescriptor(descriptor);
    }
}
