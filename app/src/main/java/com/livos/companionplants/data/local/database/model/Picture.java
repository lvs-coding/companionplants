package com.livos.companionplants.data.local.database.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "picture")
public class Picture {

    @NotNull
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Property(nameInDb = "plant_id")
    private Long plantId;

    @NotNull
    @Property(nameInDb = "picture")
    private String picture;

    @Generated(hash = 300012391)
    public Picture(@NotNull Long id, @NotNull Long plantId,
            @NotNull String picture) {
        this.id = id;
        this.plantId = plantId;
        this.picture = picture;
    }

    @Generated(hash = 1602548376)
    public Picture() {
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

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}