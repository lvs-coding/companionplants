package com.livos.companionplants.plants;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.livos.companionplants.R;
import com.livos.companionplants.application.PlantsApplication;
import com.livos.companionplants.data.local.database.model.PlantAssociation;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.events.PlantSelectedEvent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlantsFragment extends Fragment implements PlantsContract.View {
    private static final String TAG = PlantsFragment.class.getSimpleName();

    private GridLayoutManager gridLayoutManager;
    private Long plantSelectedId;


    @Inject
    PlantsContract.Presenter presenter;

    @BindView(R.id.rv_plants)
    RecyclerView grvPlants;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants,container,false);
        ButterKnife.bind(this, view);

        // Increase recyclerview performance
        grvPlants.setHasFixedSize(true);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        PlantsComponent plantsComponent = ((PlantsApplication)getActivity().getApplication()).getPlantsComponent();
        plantsComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void updateData(List<PlantAssociation> plantsAssociations, PlantSelectedEvent plantSelectedEvent) {
        gridLayoutManager = new GridLayoutManager(getContext(), 4);

         plantSelectedId = plantSelectedEvent.getPlantId();


        grvPlants.setHasFixedSize(true);
        grvPlants.setLayoutManager(gridLayoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),plantsAssociations, plantSelectedEvent);
        grvPlants.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            presenter.onSaveInstanceState(outState);
        }
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {

            presenter.takeView(this);
            presenter.onRestoreInstanceState(savedInstanceState);


        }
    }

}
