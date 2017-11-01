package com.livos.companionplants.plants;


import android.os.Bundle;

import com.livos.companionplants.base.BasePresenter;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.events.PlantSelectedEvent;

import java.util.List;

public interface PlantsContract {
    interface View {
        void updateData(List<PlantAssociation> plantsAssociations, PlantSelectedEvent event);
    }

    interface Presenter extends BasePresenter<View> {
        void takeView(PlantsContract.View view);
        void dropView();
        void onEvent(PlantSelectedEvent event);
        void onSaveInstanceState(Bundle outState);
        void onRestoreInstanceState(Bundle savedInstanceState);
    }

    interface Model {
        List<PlantAssociation> getAllPlants();
        List<PlantAssociation> getAssociatedPlants(Long plantId);
        Long getDefaultPlantId();
    }
}
