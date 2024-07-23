package com.growstats.hilt;

import android.content.Context;

import com.growstats.controller.BtController;
import com.growstats.controller.FytaController;
import com.growstats.ui.home.HomeCustomAdapter;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApiModule {

    private static final String PREFERENCES_NAME = "Settings";

    @Provides
    @Singleton
    public static FytaController fytaController()
    {
        return new FytaController();
    }

    @Provides
    @Singleton
    public static HomeCustomAdapter homeCustomAdapter()
    {
        return new HomeCustomAdapter();
    }

    @Provides
    @Singleton
    public static File getAppCache(@ActivityContext Context context)
    {
        return context.getCacheDir();
    }

}
