package com.livos.companionplants.search;


import android.content.Context;
import android.graphics.drawable.Drawable;

import com.livos.companionplants.base.BasePresenter;
import com.livos.companionplants.data.local.database.model.Picture;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.events.PlantSelectedEvent;

import java.util.List;

public interface SearchContract {
    interface View {
        void updatePlantsList(List<PlantDefinition> plantsDefinitions);
        void updateSearchedPlant(String plantName, Drawable image);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
        void takeView(SearchContract.View view);
        void dropView();
        void onListItemClicked(PlantDefinition plantDefinition, Context context);
        void onEvent(PlantSelectedEvent event);
    }

    interface Model {
        List<PlantDefinition> getAllPlantsDefinitions();
        PlantDefinition getDefaultPlantDefinition();
        Picture getPlantPicture(Long plantId);


    }
}
