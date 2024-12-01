package com.growstats.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.growstats.R;
import com.growstats.api.fyta.objects.plants.Plant;
import com.growstats.databinding.RecyclerviewItemTentBinding;
import com.growstats.databinding.RecylerviewItemPlantBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeCustomAdapter  extends RecyclerView.Adapter<HomeCustomAdapter.ViewHolder>
{
    private  List<PlantItem> plants = new ArrayList<>();
    private  List<TentItem> tents = new ArrayList<>();
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
            notifyItemInserted(plants.size()+tents.size());
        }
    }

    void addTent(TentItem tentItem)
    {
        boolean found = false;
        for(TentItem t : tents)
        {
            if(t.getUrl().equals(tentItem.getUrl()))
            {
                t.copyFrom(tentItem);
                found =true;
            }
        }
        if(!found) {
            tents.add(tentItem);
            notifyItemInserted(tents.size()+plants.size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        if(viewType == 0)
        {
            RecyclerviewItemTentBinding b = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.recyclerview_item_tent,viewGroup,false);
            b.tentlayout.setOnClickListener(onClickListener);
            return new ViewHolderTent(b);
        }
        else {
            RecylerviewItemPlantBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                    R.layout.recylerview_item_plant, viewGroup, false);
            binding.plantItem.setOnClickListener(onClickListener);
            return new ViewHolderPlant(binding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(tents.size() > 0 && position <= tents.size()-1)
            return 0;
        else
            return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(tents.size() > 0 && position <= tents.size()-1)
        {
            ((ViewHolderTent)holder).getTentBinding().setTent(tents.get(position));
        }
        else {
            ((ViewHolderPlant)holder).getPlantBinding().setPlant(plants.get(position-tents.size()));
            ((ViewHolderPlant)holder).getPlantBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
            return plants.size()+tents.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getPlantId(int pos)
    {
        if(pos - tents.size() >= 0)
            return plants.get(pos - tents.size()).id;
        else
            return -1;
    }

    public String getPlantName(int pos)
    {
        return plants.get(pos - tents.size()).name;
    }

    public String getPlantSensorMac(int pos)
    {
        return plants.get(pos).sensor_mac;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class ViewHolderPlant extends ViewHolder {
        private final RecylerviewItemPlantBinding plantBinding;

        public ViewHolderPlant(RecylerviewItemPlantBinding view) {
            super(view.getRoot());
            plantBinding = view;
        }

        public RecylerviewItemPlantBinding getPlantBinding() {
            return plantBinding;
        }
    }

    public static class ViewHolderTent extends ViewHolder {
        private final RecyclerviewItemTentBinding tentBinding;

        public ViewHolderTent(RecyclerviewItemTentBinding view) {
            super(view.getRoot());
            tentBinding = view;
        }

        public RecyclerviewItemTentBinding getTentBinding() {
            return tentBinding;
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
