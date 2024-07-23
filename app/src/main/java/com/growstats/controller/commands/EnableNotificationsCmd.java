package com.growstats.controller.commands;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;

import com.growstats.controller.BtClient;
import com.growstats.controller.BtController;

@SuppressLint("MissingPermission")
public class EnableNotificationsCmd implements BluetoothCommand
{
    private final static String TAG = EnableNotificationsCmd.class.getName();
    private final boolean enable;

    public EnableNotificationsCmd(boolean enable)
    {
        this.enable = enable;
    }

    @Override
    public void execute(BluetoothGatt gatt) {
        Log.i(TAG, "EnableNotification "+enable);
        BluetoothGattCharacteristic ch = gatt.getService(BtClient.serviceUUID).getCharacteristic(BtClient.liveModeCharacteristicUUID);
        boolean ok = gatt.setCharacteristicNotification(ch,enable);
        Log.i(TAG, "Enable notification success:" + ok);
        BluetoothGattDescriptor des = ch.getDescriptor(BtClient.notifyDescriptorUUID);
        if (enable)
            ok = des.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        else
            ok = des.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        Log.i(TAG, "setValue to Descriptor success " + ok);
        ok = gatt.writeDescriptor(des);
        Log.i(TAG, "writeDescriptor success " + ok);
    }
}
