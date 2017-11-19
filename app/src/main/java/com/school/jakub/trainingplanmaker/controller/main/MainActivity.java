package com.school.jakub.trainingplanmaker.controller.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackChecklistActivity;
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ManageMeasurementsActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.MeasurementMonitorActivity;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlans;
import com.school.jakub.trainingplanmaker.model.Measurement;
import com.school.jakub.trainingplanmaker.services.MeasurementService;
import com.school.jakub.trainingplanmaker.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.services.DiaryService;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.realm.Realm;


public class MainActivity extends NavDrawer {

    private Realm myRealm;

    @Inject
    TrainingService trainingService;
    @Inject
    DiaryService diaryService;
    @Inject
    MeasurementService measurementService;

    protected Toolbar toolbar;
    Button goToDiary;
    Button goToTraining;
    Button goToBackpack;
    Button goToMeasurement;
    TextView measurementText;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(MainActivity.this);
        this.context = context;
        initialize();
    }

    private void initialize(){

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.atlas_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

//        trainingService = new TrainingService();
//        diaryService = new DiaryService();
//        measurementService = new MeasurementService();

        myRealm = Realm.getDefaultInstance();

        if (trainingService.getAllMuscleGroup().isEmpty()){
            trainingService.createBaseConfiguration();
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
                Intent intent = new Intent(MainActivity.this, ManageMeasurementsActivity.class);
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

        if(!trainingService.checkIfAccountExist()){
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            final View mView = inflater.inflate(R.layout.backpack_activity_new_popup, null);

            mBuilder.setView(mView);
            final AlertDialog dialog  = mBuilder.create();

            Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
            Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
            final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);
            etName.setHint("Twoje imie");

            buttonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!"".equals(etName.getText().toString().trim())) {
                        trainingService.createAccount(etName.getText().toString().trim());
                        dialog.cancel();
                    }else{
                        Snackbar snackbar = Snackbar
                                .make(view, "Niepoprawne imie", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
            });

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            dialog.show();
        }

        measurementText = (TextView) findViewById(R.id.main_activity_measurements);
        if(measurementService.getAllMeasurement()==null){
            measurementText.setText("Nie dodałeś żadnego pomiaru");
        }else{
            Measurement m = measurementService.getLastEntry();
            Calendar cal = Calendar.getInstance();
            cal.setTime(m.getDate());
            String mText = "Pomiar z "+ cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH)+1)+ "-" + cal.get(Calendar.YEAR)+"\n";
            mText += "Biceps lewy: " + m.getBicepsLeft() + " cm\n";
            mText += "Biceps prawy: " + m.getBicepsRight() + " cm\n";
            mText += "Pierś: " + m.getChest() + " cm\n";
            mText += "Talia: " + m.getWaist() + " cm\n";
            mText += "Udo lewe: " + m.getThighLeft() + " cm\n";
            mText += "Udo prawe: " + m.getThighRight() + " cm\n";
            mText += "Waga: " + m.getWeight() + " kg";
            measurementText.setText(mText);
        }

    }

}
