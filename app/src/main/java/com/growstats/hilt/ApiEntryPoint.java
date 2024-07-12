package com.growstats.hilt;

import android.content.Context;

import com.growstats.controller.FytaController;
import com.growstats.controller.NavigationController;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface ApiEntryPoint {
    FytaController fytaController();
}
