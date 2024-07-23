package com.growstats.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.growstats.R;
import com.growstats.controller.BtController;
import com.growstats.controller.NavigationController;
import com.growstats.databinding.FragmentHomeBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentHomeBinding homeBinding;
    @Inject
    public HomeCustomAdapter customAdapter;
    @Inject
    NavigationController navigationController;
    @Inject
    BtController btController;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel.btController = btController;
        homeBinding.recylerviewPlants.setAdapter(customAdapter);
        homeBinding.recylerviewPlants.setLayoutManager(new LinearLayoutManager(getContext()));
        customAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = homeBinding.recylerviewPlants.getChildLayoutPosition(view);
                int id = customAdapter.getPlantId(pos);
                navigationController.showStats(id);
            }
        });
        return homeBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }
}