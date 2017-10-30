package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlanEditActivity;
import com.school.jakub.trainingplanmaker.model.Series;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.List;

/**
 * Created by Jakub on 29-Oct-17.
 */


public class TrainingPlansAdapter extends ArrayAdapter<TrainingPlan> {

    private Context context;
    TrainingService service;
    List<TrainingPlan> listOfPlans;


    public TrainingPlansAdapter(@NonNull Context context, List<TrainingPlan> plans) {
        super(context, R.layout.training_activity_list_adapter_row,  plans );
        this.context = context;
        listOfPlans = plans;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final Context con = getContext();
        View customView = inflater.inflate(R.layout.training_activity_list_adapter_row, parent, false);

        final TrainingPlan plan = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.training_plans_activity_list_adapter_row_title);
        title.setText(plan.getName());
        String text="";
        if(plan.getSeries().size()> 9){
            for(int i =0 ; i<7 ; i++){
                String s = listOfPlans.get(position).getSeries().get(i).getExercise().getName();
                text += (1+i)+". " + s.substring(0, Math.min(s.length(), 40)) + "\n";
            }
            text += "  ...   \n";
            text += plan.getSeries().size() + ".";
            text += listOfPlans.get(position).getSeries().get(plan.getSeries().size()-1).getExercise().getName();
            System.out.println(" Udało się wpisać " + listOfPlans.get(position).getSeries().get(plan.getSeries().size()-1).getExercise().getName());
        }else{
            for(Series s : plan.getSeries()){
                text += text + "\n" + s.getExercise().getName();
            }
        }
        TextView content = (TextView) customView.findViewById(R.id.training_plans_activity_list_adapter_row_content);
        content.setText(text);

        ImageView edit = (ImageView) customView.findViewById(R.id.training_plans_activity_list_adapter_row_edit_icon);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrainingPlanEditActivity.class);
                intent.putExtra("name", plan.getName());
                context.startActivity(intent);
            }
        });

        return customView;
    }
}
