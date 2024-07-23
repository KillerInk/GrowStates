package com.growstats.controller.commands;

import java.util.UUID;

public abstract class AbstractCommand<T> implements BluetoothCommand {

    public UUID serviceUuid;
    public UUID characteristicUuid;
    public byte[] value;

    public AbstractCommand(UUID service, UUID characteristic, byte[] value) {
        this.serviceUuid = service;
        this.characteristicUuid = characteristic;
        this.value = value;
    }
}
