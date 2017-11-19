package com.school.jakub.trainingplanmaker.controller.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.utils.NavDrawer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BmiCalculatorActivity extends NavDrawer {

    Toolbar toolbar;
    Spinner weightSpinner;
    Spinner heightSpinner;
    ArrayAdapter<Integer> adapterSpinerWeight;
    ArrayAdapter<Integer> adapterSpinerHeight;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.bmi_calculator_activity, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.atlas_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kalkulator BMI");

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        weightSpinner = (Spinner) findViewById(R.id.bim_calculator_weight_spinner);
        heightSpinner = (Spinner) findViewById(R.id.bim_calculator_height_spinner);

        adapterSpinerWeight = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateNumbers(40,250));
        adapterSpinerWeight.setDropDownViewResource(R.layout.my_spinner);
        weightSpinner.setAdapter(adapterSpinerWeight);

        adapterSpinerHeight = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateNumbers(100,250));
        adapterSpinerHeight.setDropDownViewResource(R.layout.my_spinner);
        heightSpinner.setAdapter(adapterSpinerHeight);

        calculate = (Button) findViewById(R.id.bim_calculator_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView bmiText = (TextView) findViewById(R.id.bim_calculator_bmi_result);

                Double weight = Double.parseDouble(weightSpinner.getSelectedItem().toString());
                Double height = Double.parseDouble(heightSpinner.getSelectedItem().toString());
                height /= 100;

                Double bmi = height * height;
                bmi = weight / bmi;

                String msg;
                if (bmi < 18.5)
                    msg = "Niedowaga";
                else if (bmi < 24.5)
                    msg = "Waga w normie";
                else if (bmi < 29.5)
                    msg = "Nadwaga";
                else if (bmi < 39.5)
                    msg = "Otyłość";
                else
                    msg = "Poważna otyłość";

                bmiText.setText("Twoje bmi wynosi: "+ String.format( "%.2f", bmi ) + "\n\n" + msg);
            }
        });

    }

    private List<Integer> generateNumbers(int from, int to) {
        List<Integer> ints = new ArrayList<>();
        for(int i = from; i<=to; i++)
            ints.add(i);
        return ints;
    }


}
