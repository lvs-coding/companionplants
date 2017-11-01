package com.livos.companionplants.data.local.database;

import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.data.local.database.model.Picture;
import com.livos.companionplants.data.local.database.model.PictureDao;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.data.local.database.model.PlantAssociationDao;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.util.ApplicationScope;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;

@ApplicationScope
public class AppDatabaseHelper implements DatabaseHelper {
    private DaoSession daoSession;

    @Inject
    public AppDatabaseHelper(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public PlantDefinition getPlantDefinition(Long id) {
        return daoSession.getPlantDefinitionDao().load(id);
    }

    @Override
    public List<PlantDefinition> getAllPlantsDefinitions() {
       //List<PlantDefinition> plants = daoSession.getPlantDefinitionDao().loadAll();

       return daoSession.getPlantDefinitionDao().loadAll();
    }

    @Override
    public List<PlantAssociation> getAssociatedPlants(Long plantId) {
        QueryBuilder queryBuilder = daoSession.getPlantAssociationDao().queryBuilder();
        queryBuilder.LOG_SQL = true;

        return queryBuilder.where(PlantAssociationDao.Properties.PlantId1.eq(plantId))
                .orderAsc(PlantAssociationDao.Properties.FlagId)
                .list();
    }

    @Override
    public List<PlantAssociation> getAllPlants() {
        return daoSession.getPlantAssociationDao().queryBuilder()
                .orderAsc(PlantAssociationDao.Properties.FlagId)
                .list();
    }

    public Picture getPlantImage(Long plantId) {
        return daoSession.getPictureDao().queryBuilder()
                .where(PictureDao.Properties.PlantId.eq(plantId))
                .unique();
    }
}
