package com.growstats.controller.commands;

import android.bluetooth.BluetoothGatt;

public interface BluetoothCommand {
    void execute(BluetoothGatt gatt);
}
