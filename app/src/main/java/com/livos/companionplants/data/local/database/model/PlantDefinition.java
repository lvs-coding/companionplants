package com.livos.companionplants.data.local.database.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "plant_definition")
public class PlantDefinition {

    @NotNull
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Property(nameInDb = "plant_id")
    private Long plantId;

    @NotNull
    @Property(nameInDb = "definition")
    private String definition;

    @Generated(hash = 159473555)
    public PlantDefinition(@NotNull Long id, @NotNull Long plantId,
            @NotNull String definition) {
        this.id = id;
        this.plantId = plantId;
        this.definition = definition;
    }

    @Generated(hash = 309989786)
    public PlantDefinition() {
    }

    @Override
    public String toString() {
        return this.definition;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlantId() {
        return this.plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}