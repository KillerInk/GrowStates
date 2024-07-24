package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.util.Log;

import java.util.UUID;

@SuppressLint("MissingPermission")
public class WriteCharacteristicCmd extends AbstractCommand{
    private final String TAG = WriteCharacteristicCmd.class.getName();
    BluetoothGattCharacteristic characteristic;

    public WriteCharacteristicCmd(UUID service, UUID characteristic, byte[] value) {
        super(service,characteristic,value);
    }

    public WriteCharacteristicCmd(BluetoothGattCharacteristic characteristic, byte[] value)
    {
        super(null,null,value);
        this.characteristic = characteristic;
    }

    @Override
    public void execute(BluetoothGatt gatt) {
        if (characteristic == null) {
            BluetoothGattService service = gatt.getService(serviceUuid);
            if (service == null) {
                Log.e(TAG, "Service is null for " + serviceUuid.toString());
                return;
            }
            characteristic = service.getCharacteristic(characteristicUuid);
            if (characteristic == null) {
                Log.e(TAG, "characteristic is null for " + characteristicUuid.toString());
                return;
            }
        }
        characteristic.setValue(value);
        boolean success = gatt.writeCharacteristic(characteristic);
        Log.d(ReadCharacteristicsCmd.class.getName(), "write characterstic for " + characteristic.getUuid().toString() + " success:" + success);
    }
}
