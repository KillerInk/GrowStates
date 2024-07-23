package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

public class ReadCharacteristicsCmd {
    @SuppressLint("MissingPermission")
    class ReadCharacteristicCmd implements BluetoothCommand {
        BluetoothGattCharacteristic characteristic;

        public ReadCharacteristicCmd(BluetoothGattCharacteristic c) {
            this.characteristic = c;
        }

        @Override
        public void execute(BluetoothGatt gatt) {
            boolean success = gatt.readCharacteristic(characteristic);
            Log.d(ReadCharacteristicCmd.class.getName(), "read characterstic for " + characteristic.getUuid().toString() + " success:" + success);
        }
    }
}
