package com.livos.companionplants.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.livos.companionplants.R;
import com.livos.companionplants.application.PlantsApplication;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.search.adapters.PlantsSearchAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.livos.companionplants.util.KeyboardUtil.hideSoftKeyboard;

public class SearchFragment extends Fragment implements SearchContract.View {
    private static final String TAG = SearchFragment.class.getSimpleName();


    @Inject
    SearchContract.Presenter presenter;

    @BindView(R.id.actv_search)
    AutoCompleteTextView actvSearch;

    @BindView(R.id.civ_plant)
    CircleImageView civPlant;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container,false);

        ButterKnife.bind(this, view);

        // Item clicked in the AutocompleteTextView list
        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlantDefinition plantDef = (PlantDefinition)adapterView.getAdapter().getItem(i);

                hideSoftKeyboard(getContext());

                presenter.onListItemClicked(plantDef, getContext());
            }
        });


        return view;
    }

    @Override
    public void updatePlantsList(List<PlantDefinition> plantsDefinitions) {

        PlantsSearchAdapter adapter = new PlantsSearchAdapter(getContext(), plantsDefinitions);
        actvSearch.setAdapter(adapter);

    }

    @Override
    public void updateSearchedPlant(String plantName, Drawable image) {

        actvSearch.setText(plantName);
        civPlant.setImageDrawable(image);
        actvSearch.setSelection(plantName.length()); // Put the cursor at the end of the name of the new searched plant
        actvSearch.dismissDropDown();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SearchComponent plantsComponent = ((PlantsApplication)getActivity().getApplication()).getSearchComponent();
        plantsComponent.inject(this);


    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.takeView(this);
        presenter.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.dropView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public interface OnPlantSelectedListener {
        void onPlantSelected(Long plantId);
    }
}
