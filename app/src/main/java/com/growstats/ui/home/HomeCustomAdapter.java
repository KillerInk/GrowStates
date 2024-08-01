package com.growstats.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.growstats.R;
import com.growstats.api.fyta.objects.plants.Plant;
import com.growstats.databinding.RecylerviewItemPlantBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeCustomAdapter  extends RecyclerView.Adapter<HomeCustomAdapter.ViewHolder>
{
    private  List<PlantItem> plants = new ArrayList<>();
    private View.OnClickListener onClickListener;

    @Inject
    public HomeCustomAdapter()
    {
    }


    void addPlant(PlantItem plantItem)
    {
        boolean found = false;
        for (PlantItem addedP : plants)
        {
            if(addedP.sensor_mac.equals(plantItem.sensor_mac))
            {
                addedP.copyFrom(plantItem);
                found = true;
            }

        }
        if(!found) {
            plants.add(plantItem);
            notifyItemInserted(plants.size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        RecylerviewItemPlantBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.recylerview_item_plant, viewGroup, false);
        binding.plantItem.setOnClickListener(onClickListener);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getPlantBinding().setPlant(plants.get(position));
        holder.getPlantBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if(plants == null)
            return 0;
        else
            return plants.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getPlantId(int pos)
    {
        return plants.get(pos).id;
    }

    public String getPlantName(int pos)
    {
        return plants.get(pos).name;
    }

    public String getPlantSensorMac(int pos)
    {
        return plants.get(pos).sensor_mac;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RecylerviewItemPlantBinding plantBinding;

        public ViewHolder(RecylerviewItemPlantBinding view) {
            super(view.getRoot());
            plantBinding = view;
        }

        public RecylerviewItemPlantBinding getPlantBinding() {
            return plantBinding;
        }
    }

    public PlantItem getPlantItem(String mac)
    {
        for (PlantItem p : plants)
        {
            if (p.sensor_mac.equals(mac))
                return p;
        }
        return null;
    }
}
