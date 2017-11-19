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

public class BackpackItemEditAdapter extends ArrayAdapter<Item> {
    private ImageView remove;
    private Context context;
    String backpackName;
    BackpackService bpService;


    public BackpackItemEditAdapter(@NonNull Context context, BackpackService backpackService, String backpackName) {
        super(context, R.layout.backpack_list_item_edit, backpackService.getAllItemsFromBackpack(backpackName));
        bpService = backpackService;
        this.context = context;
        this.backpackName = backpackName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.backpack_list_item_edit, parent, false);


        final Item item = getItem(position);
        String itemName = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.backpack_list_item_edit_item);
        textView.setText(itemName);

        remove = (ImageView) customView.findViewById(R.id.backpack_list_item_remove);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("Czy na pewno chcesz usunąć przedmiot z plecaka?")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                bpService.deleteItemFromBackpack(backpackName, item.getName());
                                remove(item);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Nie", null)
                        .show();
            }
        });

        return customView;
    }

}
