package com.growstats;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.growstats.databinding.ActivityMainBinding;
import com.growstats.ui.HomeFragment;
import com.growstats.ui.SettingsFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SettingsFragment settingsFragment;
    private HomeFragment homeFragment;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        settingsFragment = SettingsFragment.newInstance();
        homeFragment = HomeFragment.newInstance();
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,homeFragment).commit();
        activeFragment = homeFragment;
        binding.buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeFragment != null)
                    getSupportFragmentManager().beginTransaction().remove(activeFragment).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,homeFragment).commit();
                activeFragment = homeFragment;
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeFragment != null)
                    getSupportFragmentManager().beginTransaction().remove(activeFragment).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,settingsFragment).commit();
                activeFragment = settingsFragment;
            }
        });
    }

}