package com.school.jakub.trainingplanmaker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import com.school.jakub.trainingplanmaker.R;

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

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = day + " / " + (month+1) + " / " + year;

                Intent intent = new Intent(CalendarActivity.this, ProfileMeasurementActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });
    }
}
