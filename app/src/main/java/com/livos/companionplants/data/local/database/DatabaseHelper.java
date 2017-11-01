package com.livos.companionplants.data.local.database;


import com.livos.companionplants.data.local.database.model.Picture;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import java.util.List;

public interface DatabaseHelper {
    PlantDefinition getPlantDefinition(Long id);
    List<PlantDefinition> getAllPlantsDefinitions();

    List<PlantAssociation> getAssociatedPlants(Long plantId);
    List<PlantAssociation> getAllPlants();

    Picture getPlantImage(Long plantId);
}
