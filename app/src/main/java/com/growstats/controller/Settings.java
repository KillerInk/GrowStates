package com.growstats.controller;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;
import kotlin.jvm.JvmSuppressWildcards;

@JvmSuppressWildcards
public class Settings {

    private SharedPreferences preferences;
    private final String PREFERENCES_NAME = "Settings";

    public static final KEY KEY_APIKEY = new KEY("APIKEY", String.class);

    @Inject
    public Settings(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    public <T> void saveSetting(KEY name, T value) {
        if (name.c.equals(String.class))
            preferences.edit().putString(name.name, (String) value).apply();
        if (name.c.equals(Integer.class))
            preferences.edit().putInt(name.name, (int) value).apply();
        if (name.c.equals(Boolean.class))
            preferences.edit().putBoolean(name.name, (boolean) value).apply();
        if (name.c.equals(Float.class))
            preferences.edit().putFloat(name.name, (float) value).apply();
    }

    public <T>T getSetting(KEY name)
    {
        T ret = null;
        if (name.c.equals(String.class))
            ret = (T)preferences.getString(name.name,"");
        if (name.c.equals(Integer.class))
            ret = (T)(Integer)preferences.getInt(name.name,0);
        if (name.c.equals(Boolean.class))
            ret = (T)(Boolean)preferences.getBoolean(name.name,false);
        if (name.c.equals(Float.class))
            ret = (T)(Float)preferences.getFloat(name.name,0f);
        return ret;
    }

    public static class KEY
    {
        private final String name;
        private final Class c;
        public KEY(String name, Class c)
        {
            this.name = name;
            this.c = c;
        }
    }
}
