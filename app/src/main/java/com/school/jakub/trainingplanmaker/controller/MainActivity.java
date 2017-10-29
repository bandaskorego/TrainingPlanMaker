package com.school.jakub.trainingplanmaker.controller;

import android.content.Context;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class MainActivity extends NavDrawer {

    public static final String TAG = MainActivity.class.getName();
    private Realm myRealm;
    private TrainingService trainingService;
    protected Toolbar toolbar;


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

        initialise();


    }

    private void initialise(){
        trainingService = new TrainingService();
//        List<TrainingPlan> list = new ArrayList<>(trainingService.getAllDefaultPlans());
//        for(int i=0;i<list.size();i++)
//            for(int j =0; j<list.get(i).getSeries().size(); j++)
//                System.out.println(list.get(i).getSeries().get(j).toString());



        myRealm = Realm.getDefaultInstance();

        if (trainingService.getAllMuscleGroup().isEmpty()){
            trainingService.createBaseConfiguration();
            System.out.println("Cwiczenia dodane");
        }else{
//            trainingService.printAllExercises();
//            trainingService.printAllMuscleGroups();
        }
    }

}
