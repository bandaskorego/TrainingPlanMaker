package com.school.jakub.trainingplanmaker.controller.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.MeasurementAdapter;
import com.school.jakub.trainingplanmaker.services.MeasurementService;

public class ManageMeasurementsActivity extends AppCompatActivity {

    Button addBtn ;
    MeasurementService service;
    ListView listView;
    ArrayAdapter adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_measurements_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Zarządzanie pomiarami");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        service = new MeasurementService();
        listView = (ListView) findViewById(R.id.manage_measurement_listView);

        addBtn = (Button) findViewById(R.id.manage_measurement_addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageMeasurementsActivity.this, ProfileMeasurementActivity.class);
                startActivity(intent);
                finish();
            }
        });


        adaper = new MeasurementAdapter(this, service);
        listView.setAdapter(adaper);

    }

    public void setSelection(int pos){
        listView.setSelection(pos);
    }

    public void finishActivity(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
