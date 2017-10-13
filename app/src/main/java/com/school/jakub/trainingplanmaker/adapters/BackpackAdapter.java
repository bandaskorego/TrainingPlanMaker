package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackEditActivity;
import com.school.jakub.trainingplanmaker.model.Backpack;
import com.school.jakub.trainingplanmaker.services.BackpackService;

/**
 * Created by Jakub on 10-Oct-17.
 */

public class BackpackAdapter extends ArrayAdapter<Backpack> {

    private ImageView edit;
    private ImageView remove;
    private Context context;

    BackpackService bpService;

    public BackpackAdapter(@NonNull Context context, BackpackService backpackService) {
        super(context, R.layout.backpack_list_item, backpackService.getAllBackpacks());
        bpService = backpackService;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.backpack_list_item, parent, false);


        final Backpack backpack = getItem(position);
        String singleBagpack = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.backpack_list_item_textView);
        textView.setText(singleBagpack);

        edit = (ImageView) customView.findViewById(R.id.backpack_list_item_imageEdit);
//        remove = (ImageView) customView.findViewById(R.id.backpack_list_item_imageRemove);
//
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, BackpackEditActivity.class);
                intent.putExtra("backpack_name",backpack.getName());
                context.startActivity(intent);
            }
        });
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new AlertDialog.Builder(getContext())
//                        .setMessage("Czy na pewno chcesz usunąć plecak?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                bpService.deleteBackpack(backpack);
//                                dialog.cancel();
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//
//            }
//        });

        return customView;
    }
}
