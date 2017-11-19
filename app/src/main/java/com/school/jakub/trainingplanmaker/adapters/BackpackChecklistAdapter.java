package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
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

    private Context context;
    CardView card;
    BackpackService bpService;


    public BackpackChecklistAdapter(@NonNull Context context, BackpackService backpackService,String backpackName) {
        super(context, R.layout.backpack_list_item_checklist, backpackService.getAllItemsFromBackpack(backpackName));
        bpService = backpackService;
        this.context = context;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.backpack_list_item_checklist, parent, false);

        final Item backpack = getItem(position);
        String itemName = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.backpack_list_item_to_take);
        textView.setText(itemName);
        card = (CardView) customView.findViewById(R.id.backpack_checklist_item_row_cardView);
        card.setCardBackgroundColor(Color.RED);

        return customView;
    }


}
