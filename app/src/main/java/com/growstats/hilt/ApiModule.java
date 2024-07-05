package com.growstats.hilt;

import android.content.Context;
import android.content.SharedPreferences;

import com.growstats.controller.FytaController;
import com.growstats.controller.Settings;
import com.growstats.ui.HomeCustomAdapter;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.components.SingletonComponent;
import kotlin.jvm.JvmSuppressWildcards;

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
