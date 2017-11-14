package com.school.jakub.trainingplanmaker.controller.trainingPlan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.TrainingService;

public class TrainingPlanSelect extends AppCompatActivity {

    TrainingPlan plan;
    TrainingService service;
    TextView title;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_plan_select_activity);

        initialize();

    }

    private void initialize() {

        service = new TrainingService();
        Toolbar toolbar = (Toolbar) findViewById(R.id.training_plan_activity_select_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Plan treningowy");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plan = service.getPlanByName(getIntent().getStringExtra("name"));
        title = (TextView) findViewById(R.id.training_plan_select_activity_title);
        content = (TextView) findViewById(R.id.training_plan_select_activity_content);
        title.setText(plan.getName());
        fillContent();

    }

    public void fillContent(){
        String text = "";
        for(int i = 0; i < plan.getSeries().size(); i++){
            text += i + ". " + plan.getSeries().get(i).getExercise().getName() + "\n" + "Powtórzeń: " + plan.getSeries().get(i).getNumberOfRepetitions() + "\n\n";

        }
        content.setText(text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
