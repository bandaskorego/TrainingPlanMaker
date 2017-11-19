package com.school.jakub.trainingplanmaker.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.measurement.ProfileMeasurementActivity;

/**
 * Created by Jakub on 14-Oct-17.
 */

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "Calendar Activity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.profile_measurement_calendarView);

        final int bicepsLeft = getIntent().getIntExtra("bicepsLeft", 0);
        final int bicepsRight = getIntent().getIntExtra("bicepsRight", 0);
        final int chest = getIntent().getIntExtra("chest", 0);
        final int waist = getIntent().getIntExtra("waist", 0);
        final int thighLeft = getIntent().getIntExtra("thighLeft", 0);
        final int thighRight = getIntent().getIntExtra("thighRight", 0);
        final int weight = getIntent().getIntExtra("weight", 0);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //String date = day + " / " + (month+1) + " / " + year;

                Intent intent = new Intent(CalendarActivity.this, ProfileMeasurementActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("bicepsLeft", bicepsLeft);
                intent.putExtra("bicepsRight", bicepsRight);
                intent.putExtra("chest",chest);
                intent.putExtra("waist", waist);
                intent.putExtra("thighLeft", thighLeft);
                intent.putExtra("thighRight", thighRight);
                intent.putExtra("weight", weight);
                startActivity(intent);
                finish();
            }
        });
    }
}
