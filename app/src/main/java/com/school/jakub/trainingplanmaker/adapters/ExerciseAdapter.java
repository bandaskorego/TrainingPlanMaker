package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.services.TrainingService;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.exercise_list_item, parent, false);

        final Exercise backpack = getItem(position);
        String singleBagpack = getItem(position).getName();
        TextView textView = (TextView) customView.findViewById(R.id.exercise_list_item_textView);
        textView.setText(singleBagpack);

//        edit = (ImageView) customView.findViewById(R.id.backpack_list_item_imageEdit);
//        remove = (ImageView) customView.findViewById(R.id.backpack_list_item_imageRemove);
//
//        edit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, BackpackEditActivity.class);
//                intent.putExtra("backpack_name",backpack.getName());
//                context.startActivity(intent);
//            }
//        });


        return customView;
    }
}
