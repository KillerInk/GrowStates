package com.growstats.hilt;

import com.growstats.controller.FytaController;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface ApiEntryPoint {
    FytaController fytaController();
}
