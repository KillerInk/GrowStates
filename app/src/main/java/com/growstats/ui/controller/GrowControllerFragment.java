package com.growstats.ui.controller;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.growstats.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GrowControllerFragment extends Fragment {

    private GrowControllerViewModel mViewModel;
    private com.growstats.databinding.FragmentGrowControllerBinding binding;

    public static GrowControllerFragment newInstance() {
        return new GrowControllerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GrowControllerViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grow_controller, container, false);
        binding.setViewmodel(mViewModel);
        binding.checkBoxFanAutomatic.setOnCheckedChangeListener(mViewModel.autolistner);
        binding.checkBoxFanNightmode.setOnCheckedChangeListener(mViewModel.nightlistner);
        binding.checkBoxLightAutomatic.setOnCheckedChangeListener(mViewModel.lightlistner);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }
}