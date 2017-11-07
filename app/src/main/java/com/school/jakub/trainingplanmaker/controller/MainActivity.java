package com.school.jakub.trainingplanmaker.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.MeasurementMonitorActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ProfileMeasurementActivity;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlans;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.services.DiaryService;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import io.realm.Realm;


public class MainActivity extends NavDrawer {

    public static final String TAG = MainActivity.class.getName();
    private Realm myRealm;
    private TrainingService trainingService;
    private DiaryService diaryService;
    protected Toolbar toolbar;
    Button goToDiary;
    Button goToTraining;
    Button goToBackpack;
    Button goToMeasurement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.atlas_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        initialize();


    }

    private void initialize(){
        trainingService = new TrainingService();
        diaryService = new DiaryService();

        myRealm = Realm.getDefaultInstance();

        if (trainingService.getAllMuscleGroup().isEmpty()){
            trainingService.createBaseConfiguration();
            diaryService.createFirstDiary();
        }

        goToBackpack = (Button) findViewById(R.id.goToBackpackActivity);
        goToDiary = (Button) findViewById(R.id.goToDiaryActivity);
        goToMeasurement = (Button) findViewById(R.id.goToMeasurementActivity);
        goToTraining = (Button) findViewById(R.id.goToTrainingPlansActivity);

        goToBackpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BackpackActivity.class);
                startActivity(intent);
            }
        });

        goToDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        goToMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MeasurementMonitorActivity.class);
                startActivity(intent);
            }
        });

        goToTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingPlans.class);
                startActivity(intent);
            }
        });
    }

}
