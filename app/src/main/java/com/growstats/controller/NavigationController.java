package com.growstats.controller;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.growstats.R;
import com.growstats.ui.home.HomeFragment;
import com.growstats.ui.chart.PlantChartFragment;
import com.growstats.ui.SettingsFragment;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class NavigationController {

    public enum NavView
    {
        home,
        setting,
    }

    private FragmentManager fragmentManager;
    private SettingsFragment settingsFragment;
    private HomeFragment homeFragment;
    private static Fragment activeFragment;
    private PlantChartFragment plantChartFragment;

    @Inject
    public NavigationController(@ActivityContext Context context)
    {
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        settingsFragment = new SettingsFragment();
        homeFragment = new HomeFragment();
        plantChartFragment = new PlantChartFragment();
    }

    public void show(NavView nav)
    {
        switch (nav) {
            case home:
                if (activeFragment != homeFragment) {
                    if(activeFragment != null)
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,homeFragment).commit();
                    else
                        fragmentManager.beginTransaction().add(R.id.fragmentContainer, homeFragment).commit();
                    activeFragment = homeFragment;
                }
                break;
            case setting:
                if (activeFragment != settingsFragment) {
                    if(activeFragment != null)
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,settingsFragment).commit();
                    else
                        fragmentManager.beginTransaction().add(R.id.fragmentContainer, settingsFragment).commit();
                    activeFragment = settingsFragment;
                }
                break;
        }
    }

    public void showStats(int id,String name)
    {
        if (activeFragment != plantChartFragment) {
            plantChartFragment.setId(id);
            plantChartFragment.setName(name);
            if (activeFragment != null)
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,plantChartFragment).commit();
            else
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, plantChartFragment).commit();

            activeFragment = plantChartFragment;
        }
    }


}
