package com.livos.companionplants.search;

import android.content.Context;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;

import com.livos.companionplants.data.local.database.model.Picture;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.events.PlantSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getSimpleName();

    private SearchContract.View view;
    private SearchContract.Model model;
    private PlantSelectedEvent plantSelectedEvent;



    public SearchPresenter(SearchContract.Model model, PlantSelectedEvent plantSelectedEvent) {

        EventBus.getDefault().register(this);
        this.model = model;
        this.plantSelectedEvent = plantSelectedEvent;
    }

    // Fired when a plant is clicked in the grid
    @Override
    @Subscribe
    public void onEvent(PlantSelectedEvent event){

//        int resourceId = context.getResources().getIdentifier(plantAssociations.get(position).getPicture2(),"drawable", context.getPackageName());
//        holder.getIvPlant().setImageResource(resourceId);

        view.updateSearchedPlant(event.getPlantName(),event.getImage());

    }

    @Override
    public void loadData() {
        List<PlantDefinition> plantsDefinitions = model.getAllPlantsDefinitions();
        view.updatePlantsList(plantsDefinitions);
    }

    @Override
    public void takeView(SearchContract.View view) {

        this.view = view;
//        PlantDefinition defaultPlant = model.getDefaultPlantDefinition();
//        onListItemClicked(defaultPlant);

    }

    @Override
    public void dropView() {
        this.view = null;
    }

    // Fired when a plant is clicked in the autocompletetextview
    @Override
    public void onListItemClicked(PlantDefinition plantDefinition, Context context) {
        plantSelectedEvent.setPlantId(plantDefinition.getPlantId());
        plantSelectedEvent.setPlantName(plantDefinition.getDefinition());

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(model.getPlantPicture(plantDefinition.getPlantId()).getPicture(), "drawable", context.getPackageName());
        plantSelectedEvent.setImage(resources.getDrawable(resourceId));

        EventBus.getDefault().post(plantSelectedEvent);
    }


}
