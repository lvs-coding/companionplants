package com.livos.companionplants.search;

import com.livos.companionplants.application.PlantsApplicationComponent;
import com.livos.companionplants.util.SearchScope;

import dagger.Component;

@SearchScope
@Component(dependencies = PlantsApplicationComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchFragment target);
}
