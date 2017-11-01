package com.livos.companionplants.plants;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.events.PlantSelectedEvent;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<PlantAssociation> plantAssociations;
    Context context;
    PlantSelectedEvent plantSelectedEvent;

    public RecyclerViewAdapter(Context context, List<PlantAssociation> plantsAssociations, PlantSelectedEvent plantSelectedEvent) {
        this.plantAssociations = plantsAssociations;
        this.context = context;
        this.plantSelectedEvent = plantSelectedEvent;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_item, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view, plantSelectedEvent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        int resourceId = context.getResources().getIdentifier(plantAssociations.get(position).getPicture2(),"drawable", context.getPackageName());
        holder.getIvPlant().setImageResource(resourceId);
        holder.getTvPlantName().setText(plantAssociations.get(position).getPlantDefinition2());
        Long flagId = plantAssociations.get(position).getFlagId();

        holder.getTvPlantName().setBackgroundColor(getFlagColor(flagId));
        holder.getIvPlant().setTag(plantAssociations.get(position).getPlantId2());
        holder.setPlantAssociation(plantAssociations.get(position));
    }

    public int getFlagColor(Long flagId) {
        switch (flagId.intValue()) {
            case 1 :
                return context.getResources().getColor(R.color.colorGood);
            case 2:
                return context.getResources().getColor(R.color.colorNeutral);
            case 3:
                return context.getResources().getColor(R.color.colorDebate);
            case 4:
                return context.getResources().getColor(R.color.colorBad);
            case 5:
                return context.getResources().getColor(R.color.colorNeutral);
            default:
                return Color.BLACK;
        }
    }

    @Override
    public int getItemCount() {
        return plantAssociations.size();
    }
}
