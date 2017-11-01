package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "plants_associations")
public class PlantAssociation {
    @NotNull
    @Id(autoincrement = true)
    private Long _id;

    @Property(nameInDb = "plant_id_1")
    private Long plantId1;

    @Property(nameInDb = "plant_definition_1")
    private String plantDefinition1;

    @Property(nameInDb = "picture_1")
    private String picture1;

    @Property(nameInDb = "plant_id_2")
    private Long plantId2;

    @Property(nameInDb = "plant_definition_2")
    private String plantDefinition2;

    @Property(nameInDb = "picture_2")
    private String picture2;

    @Property(nameInDb = "flag_id")
    private Long flagId;

    @Generated(hash = 621083676)
    public PlantAssociation(@NotNull Long _id, Long plantId1,
            String plantDefinition1, String picture1, Long plantId2,
            String plantDefinition2, String picture2, Long flagId) {
        this._id = _id;
        this.plantId1 = plantId1;
        this.plantDefinition1 = plantDefinition1;
        this.picture1 = picture1;
        this.plantId2 = plantId2;
        this.plantDefinition2 = plantDefinition2;
        this.picture2 = picture2;
        this.flagId = flagId;
    }

    @Generated(hash = 1742457018)
    public PlantAssociation() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getPlantId1() {
        return this.plantId1;
    }

    public void setPlantId1(Long plantId1) {
        this.plantId1 = plantId1;
    }

    public String getPlantDefinition1() {
        return this.plantDefinition1;
    }

    public void setPlantDefinition1(String plantDefinition1) {
        this.plantDefinition1 = plantDefinition1;
    }

    public String getPicture1() {
        return this.picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public Long getPlantId2() {
        return this.plantId2;
    }

    public void setPlantId2(Long plantId2) {
        this.plantId2 = plantId2;
    }

    public String getPlantDefinition2() {
        return this.plantDefinition2;
    }

    public void setPlantDefinition2(String plantDefinition2) {
        this.plantDefinition2 = plantDefinition2;
    }

    public String getPicture2() {
        return this.picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public Long getFlagId() {
        return this.flagId;
    }

    public void setFlagId(Long flagId) {
        this.flagId = flagId;
    }

  
}
