package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
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
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.model.DayEntry;
import com.school.jakub.trainingplanmaker.model.Entry;
import com.school.jakub.trainingplanmaker.services.DiaryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 31-Oct-17.
 */

public class DiaryAdapter extends ArrayAdapter<Entry> {

    DiaryService service;
    String dayEntryId;
    TextView series;
    TextView status;
    TextView exercise;
    ImageView upArrow;
    ImageView downArrow;
    ImageView deleteBtn;
    ImageView statusBtn;
    Spinner repetitionSpinner;
    Spinner weightSpinner;
    Context context;

    public DiaryAdapter(@NonNull Context context, DiaryService service, String dayEntryId) {
        super(context,R.layout.diary_activity_listview_row , service.getAllEntrysFromDayEntryById(dayEntryId));
        this.service = service;
        this.dayEntryId = dayEntryId;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.diary_activity_listview_row, parent, false);

        createHandlers(customView);
        setUpTextViews(position);
        addOnClickListeners(position);
        setUpSpinners(position);

        return customView;
    }

    private void setUpSpinners(final int position) {
        ArrayAdapter<Integer> adapterRepetitionSpinner = new ArrayAdapter<Integer>(context,R.layout.my_spinner, generateListWithNumbers(1,40));
        adapterRepetitionSpinner.setDropDownViewResource(R.layout.my_spinner);
        repetitionSpinner.setAdapter(adapterRepetitionSpinner);
        repetitionSpinner.setSelection(getItem(position).getRepetition()-1);
        repetitionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                service.setNumberOfRepetition(getItem(position),i+1);
//                ((DiaryActivity) context).refreshListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<Integer> adapterweightSpinner = new ArrayAdapter<Integer>(context,R.layout.my_spinner, generateListWithNumbers(0,200));
        adapterweightSpinner.setDropDownViewResource(R.layout.my_spinner);
        weightSpinner.setAdapter(adapterweightSpinner);
        weightSpinner.setSelection(getItem(position).getWeight());
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                service.setWeightOfEntry(getItem(position), i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public List<Integer> generateListWithNumbers(int from, int to){
        List<Integer> list = new ArrayList<>();
        for(int i = from ; i <=to ; i++){
            list.add(i);
        }
        return list;
    }

    private void setUpTextViews(int position) {
        series.setText("Seria " + (position+1));
        exercise.setText(getItem(position).getExercise().getName().toString());
        status.setText(getItem(position).isFinished()?"Status: Wykonane":"Status: Zaplanowane");
        if(getItem(position).isFinished())
            statusBtn.setImageResource(R.drawable.ic_undo);
}

    private void addOnClickListeners(final int position) {

        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.swapSeriesPositionInPlan(dayEntryId, position, position -1);
                ((DiaryActivity) context).refreshListView();
                if(position!=0)
                    ((DiaryActivity) context).setSelection(position-1);
//                notifyDataSetChanged();
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.swapSeriesPositionInPlan(dayEntryId, position+1, position );
                ((DiaryActivity) context).refreshListView();
                ((DiaryActivity) context).setSelection(position+1);
//                notifyDataSetChanged();
            }
        });

        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.setFinishedStatusOfEntry(getItem(position));
                notifyDataSetChanged();
//                ((DiaryActivity) context).refreshListView();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entry entry = getItem(position);
                remove(getItem(position));
                service.removeEntry(entry);
                notifyDataSetChanged();
            }
        });

    }

    private void createHandlers(View customView) {
         series = (TextView) customView.findViewById(R.id.diary_activity_list_series);
         status = (TextView) customView.findViewById(R.id.diary_activity_list_status);
         exercise = (TextView) customView.findViewById(R.id.diary_activity_list_exercise);
         upArrow = (ImageView) customView.findViewById(R.id.diary_activity_list_up_btn);
         downArrow =(ImageView) customView.findViewById(R.id.diary_activity_list_down_btn);
         deleteBtn = (ImageView) customView.findViewById(R.id.diary_activity_list_delete_image);
         statusBtn = (ImageView) customView.findViewById(R.id.diary_activity_list_status_image);
         repetitionSpinner = (Spinner) customView.findViewById(R.id.diary_activity_list_spinner_repetiton);
         weightSpinner = (Spinner) customView.findViewById(R.id.diary_activity_list_spinner_weight);
    }
}
