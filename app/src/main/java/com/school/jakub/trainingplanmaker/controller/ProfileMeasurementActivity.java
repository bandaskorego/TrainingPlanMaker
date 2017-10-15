package com.school.jakub.trainingplanmaker.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;

import java.util.Calendar;
import java.util.Locale;

public class ProfileMeasurementActivity extends AppCompatActivity {

    private TextView datepicker;
//    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_measurement_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_measurement_activity_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        datepicker = (TextView) findViewById(R.id.profile_measurement_activity_datepicker);
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profil  eMeasurementActivity.this, CalendarActivity.class);
                startActivity(i);
            }
        });


//        datepicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Locale locale = getResources().getConfiguration().locale;
//                Locale.setDefault(locale);
//
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(ProfileMeasurementActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        onDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });

//        onDateSetListener = new DatePickerDialog.OnDateSetListener(){
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                String date = day + " / " + month + " / " + year;
//                datepicker.setText(date);
//            }
//        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
