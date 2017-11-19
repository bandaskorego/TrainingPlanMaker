package com.school.jakub.trainingplanmaker.controller.diary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.DiaryService;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddExerciseToDayEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView title;
    Spinner bodyPartSpinner;
    Spinner exerciseSpinner;
    Spinner seriesSpinner;
    Spinner repetitionSpinner;
    Spinner weightSpinner;
    CheckBox ifFinished;
    Button confirm;
    Context context;
    @Inject
    TrainingService trainingService;
    @Inject
    DiaryService diaryService;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_to_day_entry_activity);
        this.context=context;
        AndroidInjection.inject(AddExerciseToDayEntry.this);
        createHandlers();
        setUpTextViews();
        setUpSpinners();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date(year-1900,month,day,0,0,0);
                diaryService.addEntrysToDayEntry(
                        exerciseSpinner.getSelectedItem().toString(),
                        Integer.parseInt(seriesSpinner.getSelectedItem().toString()),
                        Integer.parseInt(repetitionSpinner.getSelectedItem().toString()),
                        Integer.parseInt(weightSpinner.getSelectedItem().toString()),
                        ifFinished.isChecked(),
                        date);
                Intent intent = new Intent(AddExerciseToDayEntry.this, DiaryActivity.class);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUpSpinners() {

        ArrayAdapter<String> adapterMuscleSpiner = new ArrayAdapter<String>(this,R.layout.my_spinner, trainingService.getAllMuscleGroupAsStringList());
        adapterMuscleSpiner.setDropDownViewResource(R.layout.my_spinner);
        bodyPartSpinner.setAdapter(adapterMuscleSpiner);
        bodyPartSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapterExerciseSpiner = new ArrayAdapter<String>(this,R.layout.my_spinner, trainingService.getExercisesFromCategoryAsStringList("Barki"));
        adapterExerciseSpiner.setDropDownViewResource(R.layout.my_spinner);
        exerciseSpinner.setAdapter(adapterExerciseSpiner);

        ArrayAdapter<Integer> adapterSeriesSpiner = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateListWithNumbers(1,10));
        adapterSeriesSpiner.setDropDownViewResource(R.layout.my_spinner);
        seriesSpinner.setAdapter(adapterSeriesSpiner);

        ArrayAdapter<Integer> adapterRepetitionSpiner = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateListWithNumbers(1,40));
        adapterRepetitionSpiner.setDropDownViewResource(R.layout.my_spinner);
        repetitionSpinner.setAdapter(adapterRepetitionSpiner);

        ArrayAdapter<Integer> adapterWeightSpiner = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateListWithNumbers(0,200));
        adapterWeightSpiner.setDropDownViewResource(R.layout.my_spinner);
        weightSpinner.setAdapter(adapterWeightSpiner);
    }

    public List<Integer> generateListWithNumbers(int from, int to){
        List<Integer> list = new ArrayList<>();
        for(int i = from ; i <=to ; i++){
            list.add(i);
        }
        return list;
    }

    private void setUpTextViews() {
        day = getIntent().getIntExtra("day",0);
        month = getIntent().getIntExtra("month",0);
        year = getIntent().getIntExtra("year",0);
        title.setText("Dodawanie ćwiczeń dla dnia: \n" + day + "-" + (month+1) + "-" + year);

    }

    private void createHandlers() {
        title = (TextView) findViewById(R.id.add_exercise_to_day_entry_title);
         bodyPartSpinner = (Spinner) findViewById(R.id.add_exercise_to_day_entry_spinner_body_part);
         exerciseSpinner = (Spinner) findViewById(R.id.add_exercise_to_day_entry_spinner_exercise);
        seriesSpinner = (Spinner) findViewById(R.id.add_exercise_to_day_entry_spinner_series);
         repetitionSpinner = (Spinner) findViewById(R.id.add_exercise_to_day_entry_spinner_repetition);
         weightSpinner = (Spinner) findViewById(R.id.add_exercise_to_day_entry_spinner_weight);
         ifFinished = (CheckBox) findViewById(R.id.add_exercise_to_day_entry_ckeckbox);
         confirm = (Button) findViewById(R.id.add_exercise_to_day_entry_confirm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Plan treningowy");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        updateExerciseSpinner(parent.getItemAtPosition(i).toString());
    }

    private void updateExerciseSpinner(String s) {
        ArrayAdapter<String> adapterExerciseSpiner = new ArrayAdapter<String>(this,R.layout.my_spinner, trainingService.getExercisesFromCategoryAsStringList(s));
        adapterExerciseSpiner.setDropDownViewResource(R.layout.my_spinner);
        exerciseSpinner.setAdapter(adapterExerciseSpiner);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
