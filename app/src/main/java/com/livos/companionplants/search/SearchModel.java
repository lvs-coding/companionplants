package com.livos.companionplants.search;


import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.Picture;
import com.livos.companionplants.data.local.database.model.PlantDefinition;

import java.util.List;

public class SearchModel implements SearchContract.Model {
    DatabaseHelper databaseHelper;

    public SearchModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<PlantDefinition> getAllPlantsDefinitions() {
        List<PlantDefinition> plants = databaseHelper.getAllPlantsDefinitions();

        return plants;
    }

    public PlantDefinition getDefaultPlantDefinition() {
        PlantDefinition defaultPlantDefinition = databaseHelper.getPlantDefinition(1L);
        return defaultPlantDefinition;
    }

    public Picture getPlantPicture(Long plantId) {
        Picture plantPicture = databaseHelper.getPlantImage(plantId);
        return plantPicture;
    }


}
