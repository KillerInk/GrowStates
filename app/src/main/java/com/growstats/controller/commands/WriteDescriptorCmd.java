package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.util.Log;

import java.util.UUID;

public class WriteDescriptorCmd extends AbstractCommand {
    private final String TAG = WriteDescriptorCmd.class.getName();

    private UUID descriptorUuid;
    private BluetoothGattDescriptor descriptor;

    public WriteDescriptorCmd(UUID service, UUID characteristic, byte[] value, UUID descriptorUuid) {
        super(service, characteristic, value);
        this.descriptorUuid = descriptorUuid;
    }

    public WriteDescriptorCmd(BluetoothGattDescriptor descriptor,  byte[] value)
    {
        super(descriptor.getCharacteristic().getService().getUuid(), descriptor.getCharacteristic().getUuid(), value);
        this.descriptor = descriptor;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void execute(BluetoothGatt gatt) {

        if (descriptor == null) {
            BluetoothGattService service = gatt.getService(serviceUuid);
            if (service == null) {
                Log.e(TAG, "Service is null for " + serviceUuid.toString());
                return;
            }
            BluetoothGattCharacteristic characteristic1 = service.getCharacteristic(characteristicUuid);
            if (characteristic1 == null) {
                Log.e(TAG, "characteristic is null for " + characteristicUuid.toString());
                return;
            }
            BluetoothGattDescriptor descriptor = characteristic1.getDescriptor(descriptorUuid);
            if (descriptor == null) {
                Log.e(TAG, "descriptor is null for " + descriptorUuid.toString());
                return;
            }
        }
        descriptor.setValue(value);
        boolean success = gatt.writeDescriptor(descriptor);
        Log.i(WriteDescriptorCmd.class.getName(),"write descriptor success:" + success);
    }
}
