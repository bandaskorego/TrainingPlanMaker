package com.school.jakub.trainingplanmaker.controller.measurement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.utils.CalendarActivity;
import com.school.jakub.trainingplanmaker.model.Measurement;
import com.school.jakub.trainingplanmaker.services.MeasurementService;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ProfileMeasurementActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView datepicker;
    private Context context;
    private Measurement measurement;
    private TextView bicepsLeft, bicepsRight, chest, waist, thighLeft, thighRight, weight;
    private Toolbar toolbar;
    private ImageView bicepsLeftPlusBtn, bicepsRightPlusBtn, chestPlusBtn, waistPlusBtn, thighLeftPlusBtn, thighRightPlusBtn, weightPlusBtn;
    private ImageView bicepsLeftMinusBtn, bicepsRightMinusBtn, chestMinusBtn, waistMinusBtn, thighLeftMinusBtn, thighRightMinusBtn, weightMinusBtn;
    private Button btnSave;
    @Inject
    MeasurementService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = context;
        AndroidInjection.inject(ProfileMeasurementActivity.this);
        setContentView(R.layout.profile_measurement_activity);
        initialiseItems();
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().hasExtra("year")){
            updateMeasurement();
        }else{
            if (service.getLastEntry() != null) {
                copyValues(measurement, service.getLastEntry());
            }
        }
        refreshMeasurements();

    }

    private void copyValues(Measurement measurement, Measurement lastEntry) {
        measurement.setBicepsLeft(lastEntry.getBicepsLeft());
        measurement.setBicepsRight(lastEntry.getBicepsRight());
        measurement.setChest(lastEntry.getChest());
        measurement.setWaist(lastEntry.getWaist());
        measurement.setThighLeft(lastEntry.getThighLeft());
        measurement.setThighRight(lastEntry.getThighRight());
        measurement.setWeight(lastEntry.getWeight());
    }

    private void initialiseItems() {
        measurement = new Measurement();
        btnSave = (Button) findViewById(R.id.profile_measurement_activity_save_btn);
        bicepsLeft = (TextView) findViewById(R.id.profile_measurement_activity_biceps_left);
        bicepsRight = (TextView) findViewById(R.id.profile_measurement_activity_biceps_right);
        chest = (TextView) findViewById(R.id.profile_measurement_activity_chest);
        waist = (TextView) findViewById(R.id.profile_measurement_activity_waist);
        thighLeft = (TextView) findViewById(R.id.profile_measurement_activity_thigh_left);
        thighRight = (TextView) findViewById(R.id.profile_measurement_activity_thigh_right);
        weight = (TextView) findViewById(R.id.profile_measurement_activity_weight);
        toolbar = (Toolbar) findViewById(R.id.profile_measurement_activity_toolbar);
        datepicker = (TextView) findViewById(R.id.profile_measurement_activity_datepicker);

        bicepsLeftPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_biceps_left);
        bicepsRightPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_biceps_right);
        chestPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_chest);
        waistPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_waist);
        thighLeftPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_thigh_left);
        thighRightPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_thigh_right);
        weightPlusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_plus_weight);

        bicepsLeftMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_biceps_left);
        bicepsRightMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_biceps_right);
        chestMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_chest);
        waistMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_waist);
        thighLeftMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_thigh_left);
        thighRightMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_thigh_right);
        weightMinusBtn = (ImageView) findViewById(R.id.profile_measurement_activity_minus_weight);

        bicepsLeftPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        bicepsRightPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        chestPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        waistPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        thighLeftPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        thighRightPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        weightPlusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        bicepsLeftMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        bicepsRightMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        chestMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        waistMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        thighLeftMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        thighRightMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));
        weightMinusBtn.setOnTouchListener(new RepeatListener(400, 100, this));

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileMeasurementActivity.this, CalendarActivity.class);
                i.putExtra("bicepsLeft", measurement.getBicepsLeft());
                i.putExtra("bicepsRight", measurement.getBicepsRight());
                i.putExtra("chest", measurement.getChest());
                i.putExtra("waist", measurement.getWaist());
                i.putExtra("thighLeft", measurement.getThighLeft());
                i.putExtra("thighRight", measurement.getThighRight());
                i.putExtra("weight", measurement.getWeight());
                startActivity(i);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.createEntry(measurement);
                Snackbar.make(view, "Dodano wpis", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void refreshMeasurements() {
        bicepsLeft.setText(Integer.toString(measurement.getBicepsLeft()));
        bicepsRight.setText(Integer.toString(measurement.getBicepsRight()));
        chest.setText(Integer.toString(measurement.getChest()));
        waist.setText(Integer.toString(measurement.getWaist()));
        thighLeft.setText(Integer.toString(measurement.getThighLeft()));
        thighRight.setText(Integer.toString(measurement.getThighRight()));
        weight.setText(Integer.toString(measurement.getWeight()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(measurement.getDate());
        datepicker.setText(cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR));
    }

    private void updateMeasurement() {
        measurement.setBicepsLeft(getIntent().getIntExtra("bicepsLeft", 0));
        measurement.setBicepsRight(getIntent().getIntExtra("bicepsRight", 0));
        measurement.setChest(getIntent().getIntExtra("chest", 0));
        measurement.setWaist(getIntent().getIntExtra("waist", 0));
        measurement.setThighLeft(getIntent().getIntExtra("thighLeft", 0));
        measurement.setThighRight(getIntent().getIntExtra("thighRight", 0));
        measurement.setWeight(getIntent().getIntExtra("weight", 0));
        measurement.setDate(new Date((getIntent().getIntExtra("year",0)-1900), getIntent().getIntExtra("month",0),getIntent().getIntExtra("day",0),0,0,0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        if(item.getItemId() == R.id.manage_measurement){
            Intent intent = new Intent(ProfileMeasurementActivity.this, ManageMeasurementsActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_measurement, menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_measurement_activity_plus_biceps_left:
                measurement.setBicepsLeft(measurement.getBicepsLeft() + 1);
                break;

            case R.id.profile_measurement_activity_plus_biceps_right:
                measurement.setBicepsRight(measurement.getBicepsRight() + 1);
                break;

            case R.id.profile_measurement_activity_plus_chest:
                measurement.setChest(measurement.getChest() + 1);
                break;

            case R.id.profile_measurement_activity_plus_waist:
                measurement.setWaist(measurement.getWaist() + 1);
                break;

            case R.id.profile_measurement_activity_plus_thigh_left:
                measurement.setThighLeft(measurement.getThighLeft() + 1);
                break;

            case R.id.profile_measurement_activity_plus_thigh_right:
                measurement.setThighRight(measurement.getThighRight() + 1);
                break;

            case R.id.profile_measurement_activity_plus_weight:
                measurement.setWeight(measurement.getWeight() + 1);
                break;

            case R.id.profile_measurement_activity_minus_biceps_left:
                measurement.setBicepsLeft(measurement.getBicepsLeft() - 1);
                break;

            case R.id.profile_measurement_activity_minus_biceps_right:
                measurement.setBicepsRight(measurement.getBicepsRight() - 1);
                break;

            case R.id.profile_measurement_activity_minus_chest:
                measurement.setChest(measurement.getChest() - 1);
                break;

            case R.id.profile_measurement_activity_minus_waist:
                measurement.setWaist(measurement.getWaist() - 1);
                break;

            case R.id.profile_measurement_activity_minus_thigh_left:
                measurement.setThighLeft(measurement.getThighLeft() - 1);
                break;

            case R.id.profile_measurement_activity_minus_thigh_right:
                measurement.setThighRight(measurement.getThighRight() - 1);
                break;

            case R.id.profile_measurement_activity_minus_weight:
                measurement.setWeight(measurement.getWeight()- 1);
                break;
        }
        
        refreshMeasurements();
    }

}
