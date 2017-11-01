package com.livos.companionplants.search;

import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.events.PlantSelectedEventImpl;
import com.livos.companionplants.util.SearchScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    @Provides
    @SearchScope
    SearchContract.Model provideSearchModel(DatabaseHelper databaseHelper) {
        return new SearchModel(databaseHelper);
    }

    @Provides
    @SearchScope
    PlantSelectedEvent providePlantSelectedEvent() {
        return new PlantSelectedEventImpl();
    }

    @Provides
    @SearchScope
    SearchContract.Presenter provideSearchPresenter(SearchContract.Model searchModel, PlantSelectedEvent plantSelectedEvent) {
        return new SearchPresenter(searchModel, plantSelectedEvent);
    }
}
