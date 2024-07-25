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
    private static List<PlantItem> plants = new ArrayList<>();
    private View.OnClickListener onClickListener;

    @Inject
    public HomeCustomAdapter()
    {
    }


    public void setPlants(List<PlantItem> p)
    {
        if(plants.size() == 0) {
            plants.clear();
            plants.addAll(p);
            notifyItemRangeChanged(0, plants.size());
        }
        for (PlantItem pl : p)
        {
            boolean found = false;
            for (PlantItem addedP : plants)
            {
                if(addedP.sensor_mac.equals(pl.sensor_mac))
                {
                    addedP.copyFrom(pl);
                    found = true;
                }
            }
            if(!found) {
                plants.add(pl);
                notifyItemInserted(plants.size());
            }
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
}
