package com.livos.companionplants.search.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.database.model.PlantDefinition;

import java.util.List;

public class PlantsSearchAdapter extends ArrayAdapter<PlantDefinition> {

    private List<PlantDefinition> plantsDefinitions;

    public PlantsSearchAdapter(@NonNull Context context, @NonNull List<PlantDefinition> plantsDefinitions) {
        super(context, 0, plantsDefinitions);
        this.plantsDefinitions = plantsDefinitions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlantDefinition plantDefinition = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_search, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);

        if(plantDefinition != null) {
            tvName.setText(plantDefinition.getDefinition());
        }

        return convertView;
    }

}
