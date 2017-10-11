package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.model.Backpack;
import com.school.jakub.trainingplanmaker.services.BackpackService;

/**
 * Created by Jakub on 10-Oct-17.
 */

public class BackpackAdapter extends ArrayAdapter<Backpack> {

    private ImageView edit;
    private ImageView remove;

    BackpackService bpService;

    public BackpackAdapter(@NonNull Context context, BackpackService backpackService) {
        super(context, R.layout.backpack_list_item, backpackService.getAllBackpacks());
        bpService = backpackService;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.backpack_list_item, parent, false);

        String singleBagpack = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.backpack_list_item_textView);
        textView.setText(singleBagpack);

        edit = (ImageView) customView.findViewById(R.id.backpack_list_item_imageEdit);
        remove = (ImageView) customView.findViewById(R.id.backpack_list_item_imageRemove);
//
//        edit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                bagpacks.remove(position); //or some other task
//                notifyDataSetChanged();
//            }
//        });
//        edit.setOnClickListener(position, R.id.bagpack_list_item_imageEdit);

        return customView;
    }
}
