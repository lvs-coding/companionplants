package com.livos.companionplants.plants;


import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.PlantAssociation;

import java.util.List;

public class PlantsModel implements PlantsContract.Model {
    private DatabaseHelper databaseHelper;

    public PlantsModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    @Override
    public List<PlantAssociation> getAllPlants() {
        return databaseHelper.getAllPlants();
    }

    @Override
    public List<PlantAssociation> getAssociatedPlants(Long plantId) {
        return databaseHelper.getAssociatedPlants(plantId);
    }

    @Override
    public Long getDefaultPlantId() {
        return 1L; // Id 1 => Ail
    }
}
