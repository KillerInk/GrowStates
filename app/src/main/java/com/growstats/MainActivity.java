package com.growstats;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.growstats.controller.BtController;
import com.growstats.controller.NavigationController;
import com.growstats.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<String> mPermissionResult;

    @Inject
    NavigationController navigationController;
    @Inject BtController btController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mPermissionResult = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            btController.startDiscover();
                        } else {
                            //Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                        }
                    }
                });

        binding.buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationController.show(NavigationController.NavView.home);
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationController.show(NavigationController.NavView.setting);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationController.show(NavigationController.NavView.home);
    }



}