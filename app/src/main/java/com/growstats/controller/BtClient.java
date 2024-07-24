package com.growstats.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.growstats.controller.commands.BluetoothCommand;
import com.growstats.controller.commands.ChangeMtuCmd;
import com.growstats.controller.commands.DiscoverServices;
import com.growstats.controller.commands.ReadCharacteristicsCmd;
import com.growstats.controller.commands.WriteCharacteristicCmd;
import com.growstats.controller.commands.WriteDescriptorCmd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;

import kotlin.UByte;


public class BtClient {

    public enum BtClientState
    {
        disconnected,
        disconneting,
        connecting,
        connected,
        livemode,
    }

    public interface Events
    {
        void onStateChanged(BtClientState state,String mac);
        void onBeamData(BeamData data);
    }

    public void addCommand(BluetoothCommand command) {
        Log.i(TAG, "Add Command");
        synchronized (mCommandQueue) {

            mCommandQueue.add(command);
            mCommandExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Run Command");
                    mCommandLock.acquireUninterruptibly();
                    command.execute(gatt);
                }
            });
        }
    }

    protected void removeCommand() {
        Log.i(TAG, "Deque Command");
        mCommandQueue.pop();
        mCommandLock.release();
    }

    private final String TAG = BtClient.class.getName();
    String name;
    String mac;
    //f364babe-00b0-4240-ba50-05ca45bf0000
    public static final java.util.UUID serviceUUID = UUID.fromString("f364babe-00b0-4240-ba50-05ca45bf0000");
    //f364bebf-00b0-4240-ba50-05ca45bf0000
    public static final java.util.UUID liveModeCharacteristicUUID = UUID.fromString("f364bebf-00b0-4240-ba50-05ca45bf0000");
    public static final java.util.UUID controlCharacteristicUUID = UUID.fromString("f3640001-00b0-4240-ba50-05ca45bf0000");
    //00002902-0000-1000-8000-00805f9b34fb
    public static final java.util.UUID notifyDescriptorUUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");


    public static final UUID livedataEnableService = UUID.fromString("ABCDFFF0-1212-EFDE-1523-785FEF130000");
    public static final UUID livedataEnableChar = UUID.fromString("0000FFF1-0000-1000-8000-00805F9B34FB");
    public static final byte[] enableLiveData = new byte[]{2};
    public static final byte[] stopLiveData = new byte[]{3};

    private BluetoothAdapter btAdapter;
    private BluetoothDevice device;
    private BluetoothGatt gatt;
    private Context context;
    private final Handler handler = new Handler(Looper.getMainLooper());

    LinkedList<BluetoothCommand> mCommandQueue = new LinkedList<BluetoothCommand>();
    //Command Operation executor - will only run one at a time
    Executor mCommandExecutor = Executors.newSingleThreadExecutor();
    //Semaphore lock to coordinate command executions, to ensure only one is
    //currently started and waiting on a response.
    Semaphore mCommandLock = new Semaphore(1, true);

    private boolean stream = false;
    private BtClientState state;
    private Events eventsListner;

    public void setEventsListner(Events events)
    {
        this.eventsListner = events;
    }

    private void setState(BtClientState state)
    {
        this.state = state;
        if (eventsListner != null)
            eventsListner.onStateChanged(state, mac);
    }


    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {

        @Override
        public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyUpdate(gatt, txPhy, rxPhy, status);
        }

        @Override
        public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
            super.onPhyRead(gatt, txPhy, rxPhy, status);
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            String deviceAddress = gatt.getDevice().getAddress();
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    stream = false;
                    Log.i(TAG, "Conntected to " + deviceAddress);
                    BtClient.this.gatt = gatt;
                    addCommand(new ChangeMtuCmd());
                    addCommand(new DiscoverServices());
                    setState(BtClientState.connected);

                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    Log.i(TAG, "Disconnected from " + deviceAddress);
                    gatt.close();
                    BtClient.this.gatt = null;
                    stream = false;
                    setState(BtClientState.disconnected);
                }
            } else {
                Log.e(TAG, "Failed to connect to " + deviceAddress + " status;" +  status + " newState:" + newState);
                gatt.close();
                BtClient.this.gatt = null;
                stream = false;
                setState(BtClientState.disconnected);
            }
        }

        private String getPropertyString(int perm) {
            String ret = "";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_BROADCAST) != 0)
                ret += " PROPERTY_BROADCAST";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_READ) != 0)
                ret += " PROPERTY_READ";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_WRITE) != 0)
                ret += " PROPERTY_WRITE";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0)
                ret += " PROPERTY_NOTIFY";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_EXTENDED_PROPS) != 0)
                ret += " PROPERTY_EXTENDED_PROPS";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE) != 0)
                ret += " PROPERTY_SIGNED_WRITE";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0)
                ret += " PROPERTY_WRITE_NO_RESPONSE";
            if ((perm & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0)
                ret += " PROPERTY_INDICATE";
            if (ret.isEmpty())
                return "UNKNOWN " + perm;
            else
                return ret;
        }

        @SuppressLint("MissingPermission")
        private void printServices(List<BluetoothGattService> services) {
            if (services.isEmpty()) {
                Log.i(TAG, "No services");
                return;
            }
            String ret = "";
            for (BluetoothGattService s : services) {
                if (s.getUuid().equals(serviceUUID))
                    ret += "LiveModeService " + s.getUuid().toString() + "\n";
                else
                    ret += "Service " + s.getUuid().toString() + "\n";
                List<BluetoothGattCharacteristic> serviceCharacteristics = s.getCharacteristics();
                for (BluetoothGattCharacteristic characteristic : serviceCharacteristics) {
                    if (characteristic.getUuid().equals(liveModeCharacteristicUUID)) {
                        ret += "    LiveModeCharacteristic " + characteristic.getUuid().toString() + " " + getPropertyString(characteristic.getProperties()) + "\n";
                        //handler.post(()-> gatt.readDescriptor(ss.getDescriptors().get(1)));
                        //handler.post(()-> gatt.readCharacteristic(ss));


                    } else
                        ret += "    Characteristic " + characteristic.getUuid().toString() + " " + getPropertyString(characteristic.getProperties()) + "\n";
                    List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();
                    for (BluetoothGattDescriptor descriptor : descriptors)
                    {
                        ret += "        Descriptor " +descriptor.getUuid().toString() +"\n";
                    }
                }

                Log.i(TAG, ret);
            }
        }


        @SuppressLint("MissingPermission")
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            Log.i(TAG, "onServicesDiscovered");
            List<BluetoothGattService> services = gatt.getServices();
            //btAdapter.cancelDiscovery();
            printServices(services);
            removeCommand();
            BluetoothGattCharacteristic ch = gatt.getService(BtClient.serviceUUID).getCharacteristic(BtClient.liveModeCharacteristicUUID);

            //singel request to live mode characteristic
            //addCommand(new ReadCharacteristicsCmd(ch));

            //subscibe to live mode notify
            gatt.setCharacteristicNotification(ch,true);
            addCommand(new WriteDescriptorCmd(ch.getDescriptor(notifyDescriptorUUID),BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE));
            addCommand(new WriteCharacteristicCmd(livedataEnableService,livedataEnableChar,enableLiveData));
            state = BtClientState.livemode;
        }


        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            Log.i(TAG, "onCharacteristicRead");
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (characteristic.getUuid().equals(liveModeCharacteristicUUID)) {
                    byte[] data = characteristic.getValue();
                    processData(data);
                }
            }
            if (!stream)
                removeCommand();
        }

        private void processData(byte[] bytes)
        {
            Log.i(TAG, Arrays.toString(bytes) + " Size:"+bytes.length);
            byteArrayToHexString(bytes);
            BeamData b = new BeamData(bytes,mac);
            if (eventsListner != null)
                handler.post(()->eventsListner.onBeamData(b));
        }

        //dont USE IT!
        @Override
        public void onCharacteristicRead(@NonNull BluetoothGatt gatt, @NonNull BluetoothGattCharacteristic characteristic, @NonNull byte[] value, int status) {
            super.onCharacteristicRead(gatt, characteristic, value, status);
            Log.i(TAG, "onCharacteristicRead");
           /* if (status == BluetoothGatt.GATT_SUCCESS) {
                if (characteristic.getUuid().equals(liveModeCharacteristicUUID)) {
                    byte[] data = value;
                    Log.i(TAG, Arrays.toString(data));
                    byteArrayToHexString(data);
                }
            }*/
            removeCommand();
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
            Log.i(TAG, "onDescriptorRead");

        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            Log.i(TAG, "onCharacteristicChanged");
            if (characteristic.getUuid().equals(liveModeCharacteristicUUID)) {
                byte[] data = characteristic.getValue();
                processData(data);
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            Log.i(TAG, "onCharacteristicWrite");
            if (!stream && characteristic.getUuid().equals(livedataEnableChar)) {
                stream = true;
                setState(BtClientState.livemode);
            }
            removeCommand();
        }

        @Override
        public void onCharacteristicChanged(@NonNull BluetoothGatt gatt, @NonNull BluetoothGattCharacteristic characteristic, @NonNull byte[] value) {
            super.onCharacteristicChanged(gatt, characteristic, value);
            Log.i(TAG, "onCharacteristicChanged");
            if (characteristic.getUuid().equals(liveModeCharacteristicUUID)) {
                byte[] data = characteristic.getValue();
                processData(data);
            }
        }

        @Override
        public void onDescriptorRead(@NonNull BluetoothGatt gatt, @NonNull BluetoothGattDescriptor descriptor, int status, @NonNull byte[] value) {
            super.onDescriptorRead(gatt, descriptor, status, value);
            Log.i(TAG, "onDescriptorRead");
            if (status == BluetoothGatt.GATT_SUCCESS) {
                byte[] data = value;
                byteArrayToHexString(data);
            }
            removeCommand();
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.i(TAG, "onDescriptorWrite success");
            }
            else
                Log.i(TAG, "onDescriptorWrite failed");
            removeCommand();
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
            Log.i(TAG, "onReliableWriteCompleted");
            removeCommand();
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
            Log.i(TAG, "onReadRemoteRssi");
            removeCommand();
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            removeCommand();
            Log.i(TAG, "onMtuChanged");
        }

        @Override
        public void onServiceChanged(@NonNull BluetoothGatt gatt) {
            super.onServiceChanged(gatt);
            removeCommand();
            Log.i(TAG, "onServiceChanged");
        }
    };

    public BtClient(BluetoothAdapter btAdapter, String name, String mac, Context context) {
        this.btAdapter = btAdapter;
        this.name = name;
        this.mac = mac;
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public void connect() {
        setState(BtClientState.connecting);
        device = btAdapter.getRemoteDevice(mac);
        device.connectGatt(context, false, gattCallback);
    }

    @SuppressLint("MissingPermission")
    public void disconnect()
    {
        setState(BtClientState.disconneting);
        if (gatt != null)
            gatt.disconnect();
        else
            setState(BtClientState.disconnected);
    }

    @SuppressLint("MissingPermission")
    public byte[] getByteArrayValue() {
        BluetoothGattCharacteristic bgc = gatt.getService(serviceUUID).getCharacteristic(liveModeCharacteristicUUID);
        gatt.readCharacteristic(bgc);
        byteArrayToHexString(bgc.getValue());
        return bgc.getValue();
    }

    private void byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        Log.i(TAG, sb.toString());
    }

    public BtClientState getState() {
        return state;
    }
}
