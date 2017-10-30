package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlanEditActivity;
import com.school.jakub.trainingplanmaker.model.Series;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 30-Oct-17.
 */

public class seriesEditAdapter extends ArrayAdapter<Series> {

    private TrainingService service;
    private Context context;
    Spinner spinner_3;
    ImageView imageDelete;
    ImageView imageDown;
    ImageView imageUp;
    String planName;

    public seriesEditAdapter(@NonNull Context context, TrainingService service, String planName) {
        super(context, R.layout.training_plan_edit_activity_listview_row, service.getAllSeriesFromPlan(planName));
        this.service = service;
        this.context = context;
        this.planName = planName;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.training_plan_edit_activity_listview_row, parent, false);

        final Series series = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.training_plan_activity_edit_exercisename);
        textView.setText(series.getExercise().getName());

        spinner_3 = (Spinner) customView.findViewById(R.id.training_plan_activity_edit_spinner);
        ArrayAdapter<Integer> adapterSpiner3 = new ArrayAdapter<Integer>(context,R.layout.my_spinner, generateNumbersTo(40));
        adapterSpiner3.setDropDownViewResource(R.layout.my_spinner);
        spinner_3.setAdapter(adapterSpiner3);
        System.out.println(series.getNumberOfRepetitions());
        System.out.println("Ilosc repetition");
        spinner_3.setSelection(series.getNumberOfRepetitions()-1);

        imageDelete = (ImageView) customView.findViewById(R.id.training_plan_activity_edit_delete);
        imageUp = (ImageView) customView.findViewById(R.id.training_plan_activity_edit_uparrow);
        imageDown = (ImageView) customView.findViewById(R.id.training_plan_activity_edit_downarrow);

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.removeSeriesFromPlanByPlanName(planName,position);
                ((TrainingPlanEditActivity) context).refreshListView();
            }
        });

        imageUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.swapSeriesPositionInPlan(planName, position, position-1);
                ((TrainingPlanEditActivity) context).refreshListView();
            }
        });

        imageDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.swapSeriesPositionInPlan(planName, position+1, position);
                ((TrainingPlanEditActivity) context).refreshListView();
            }
        });



//        spinner_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//            }
//        });
        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(series.getId());
                service.setNumberOfRepetitionInSeries(series.getId(),i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return customView;
    }

    private List<Integer> generateNumbersTo(int to) {
        List<Integer> numbers = new ArrayList<>();
        for(int i=1; i<=to;i++){
            numbers.add(i);
        }
        return numbers;
    }
}
