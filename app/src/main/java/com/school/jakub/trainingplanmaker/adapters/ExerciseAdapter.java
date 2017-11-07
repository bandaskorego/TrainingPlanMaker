package com.school.jakub.trainingplanmaker.adapters;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.atlas.AtlasActivity;
import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.List;

/**
 * Created by Jakub on 28-Oct-17.
 */



public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    private TrainingService service;
    private Context context;


    public ExerciseAdapter(@NonNull Context context, TrainingService service, int index) {
        super(context, R.layout.exercise_list_item, service.getExercisesFromCategory(service.getMuscleGroupName(index)));
        this.service = service;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.exercise_list_item, parent, false);

        final Exercise backpack = getItem(position);
        String singleBagpack = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.exercise_list_item_textView);
        textView.setText(singleBagpack);
        ImageView ytBtn = (ImageView) customView.findViewById(R.id.ytButton);

        ytBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arr = getItem(position).getName().split(" ");
                String link = "https://www.youtube.com/results?search_query=";
                for(int i =0 ; i<arr.length; i++)
                    link = link + arr[i] + "+";

                Intent intent = new Intent(
                        Intent.ACTION_VIEW ,
                        Uri.parse(link));
                intent.setComponent(new ComponentName("com.google.android.youtube","com.google.android.youtube.PlayerActivity"));

                Intent intent1 = new Intent(
                        Intent.ACTION_VIEW ,
                        Uri.parse(link));

                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(intent1);
                }

            }
        });

        return customView;
    }
}
