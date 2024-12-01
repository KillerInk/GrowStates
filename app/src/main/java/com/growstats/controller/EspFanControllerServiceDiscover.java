package com.growstats.controller;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;

public class EspFanControllerServiceDiscover {

    public  interface ServiceEvent
    {
        void onServiceDiscovered(String url);
    }

    NsdManager.DiscoveryListener discoveryListener;
    NsdManager nsdManager;
    WifiManager.MulticastLock multicastLock;
    String SERVICE_TYPE = "_http._tcp."; //"_services._dns-sd._udp"; //"_http._tcp.";
    String serviceName ="Esp32FanController";
    NsdServiceInfo mService;
    NsdManager.ResolveListener resolveListener;
    public ServiceEvent serviceEvent;

    private final String TAG = EspFanControllerServiceDiscover.class.getName();

    public EspFanControllerServiceDiscover(Context context)
    {
        nsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
        multicastLock = ((WifiManager)context.getSystemService(Context.WIFI_SERVICE)).createMulticastLock(TAG);
    }

    public void initializeResolveListener() {
        resolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Called when the resolve fails. Use the error code to debug.
                Log.e(TAG, "Resolve failed: " + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "Resolve Succeeded. " + serviceInfo);

                if (serviceInfo.getServiceName().equals(serviceName)) {
                    mService = serviceInfo;
                    int port = mService.getPort();
                    InetAddress host = mService.getHost();
                    if(serviceEvent != null)
                        serviceEvent.onServiceDiscovered("http:/"+host+":"+port);
                }

            }
        };
    }


    public void initializeDiscoveryListener() {

        initializeResolveListener();
        // Instantiate a new DiscoveryListener
        discoveryListener = new NsdManager.DiscoveryListener() {

            // Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                // A service was found! Do something with it.
                Log.d(TAG, "Service discovery success" + service);

                if (!service.getServiceType().equals(SERVICE_TYPE)) {
                    // Service type is the string containing the protocol and
                    // transport layer for this service.
                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
                } else if (service.getServiceName().equals(serviceName)) {
                    // The name of the service tells the user what they'd be
                    // connecting to. It could be "Bob's Chat App".
                    Log.d(TAG, "Same machine: " + serviceName);
                    nsdManager.resolveService(service, resolveListener);

                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e(TAG, "service lost: " + service);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                //nsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                nsdManager.stopServiceDiscovery(this);
            }
        };
        multicastLock.setReferenceCounted(true);
        multicastLock.acquire();
        nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
    }


}
