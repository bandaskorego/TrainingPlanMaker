package com.school.jakub.trainingplanmaker.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.school.jakub.trainingplanmaker.R;

/**
 * Created by Jakub on 14-Oct-17.
 */

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
    }
}
