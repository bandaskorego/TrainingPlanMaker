package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackEditActivity;
import com.school.jakub.trainingplanmaker.model.Backpack;
import com.school.jakub.trainingplanmaker.model.Item;
import com.school.jakub.trainingplanmaker.services.BackpackService;

/**
 * Created by Jakub on 13-Oct-17.
 */

public class BackpackChecklistAdapter extends ArrayAdapter<Item>{

    private ImageView edit;

    private Context context;
    BackpackService bpService;


    public BackpackChecklistAdapter(@NonNull Context context, BackpackService backpackService,String backpackName) {
        super(context, R.layout.backpack_list_item, backpackService.getAllItemsFromBackpack(backpackName));
        bpService = backpackService;
        this.context = context;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.backpack_list_item, parent, false);

        final Item backpack = getItem(position);
        String itemName = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.backpack_list_item_textView);
        textView.setText(itemName);
        customView.setBackgroundColor(Color.RED);

        edit = (ImageView) customView.findViewById(R.id.backpack_list_item_imageEdit);
        edit.setVisibility(View.INVISIBLE);

        return customView;
    }


}
