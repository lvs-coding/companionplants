package com.livos.companionplants.plants;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.events.PlantSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvPlantName;
    private ImageView ivPlant;
    private PlantAssociation plantAssociation;
    private PlantSelectedEvent plantSelectedEvent;

    @Override
    public void onClick(View view) {
        // The img tag contains the id of the associated plant
        plantSelectedEvent.setPlantId(Long.valueOf(ivPlant.getTag().toString()));
        plantSelectedEvent.setPlantName(tvPlantName.getText().toString());
        plantSelectedEvent.setImage(ivPlant.getDrawable());

        EventBus.getDefault().post(plantSelectedEvent);

    }

    public ImageView getIvPlant() {
        return ivPlant;
    }

    public void setIvPlant(ImageView ivPlant) {
        this.ivPlant = ivPlant;
    }

    public PlantAssociation getPlantAssociation() {
        return plantAssociation;
    }

    public void setPlantAssociation(PlantAssociation plantAssociation) {
        this.plantAssociation = plantAssociation;
    }

    public TextView getTvPlantName() {
        return tvPlantName;
    }

    public void setTvPlantName(TextView tvPlantName) {
        this.tvPlantName = tvPlantName;
    }

    public RecyclerViewHolder(View itemView, PlantSelectedEvent plantSelectedEvent) {
        super(itemView);
        itemView.setOnClickListener(this);
        tvPlantName = itemView.findViewById(R.id.tv_plant_name);
        ivPlant = itemView.findViewById(R.id.iv_plant);
        this.plantSelectedEvent = plantSelectedEvent;
    }
}
