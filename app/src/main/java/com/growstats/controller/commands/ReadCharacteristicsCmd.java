package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

@SuppressLint("MissingPermission")
public class ReadCharacteristicsCmd implements BluetoothCommand{

        BluetoothGattCharacteristic characteristic;

        public ReadCharacteristicsCmd(BluetoothGattCharacteristic c) {
            this.characteristic = c;
        }

        @Override
        public void execute(BluetoothGatt gatt) {
            boolean success = gatt.readCharacteristic(characteristic);
            Log.d(ReadCharacteristicsCmd.class.getName(), "read characterstic for " + characteristic.getUuid().toString() + " success:" + success);
        }

}
