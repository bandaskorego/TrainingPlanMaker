package com.school.jakub.trainingplanmaker.controller.exerciseMonitor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.DayEntry;
import com.school.jakub.trainingplanmaker.services.DiaryService;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExerciseMonitor extends NavDrawer implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    Spinner bodyPartSpinner;
    Spinner exerciseSpinner;
    Spinner rangeSpinner;
    LineChart chart;
    DiaryService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new DiaryService();
        setupToolbar();
        initializeSpinners();
        prepareChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_diary_btn) {
            Intent intent = new Intent(ExerciseMonitor.this, DiaryActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeSpinners() {


        bodyPartSpinner = (Spinner) findViewById(R.id.exercise_monitor_activity_body_part_spinner);
        exerciseSpinner = (Spinner) findViewById(R.id.exercise_monitor_activity_exercisespinner);
        rangeSpinner = (Spinner) findViewById(R.id.exercise_monitor_activity_range_spinner);

        ArrayAdapter<String> adapterGroupsSpinner = new ArrayAdapter<String>(ExerciseMonitor.this, R.layout.my_spinner, service.getAllMuscleGroup());
        adapterGroupsSpinner.setDropDownViewResource(R.layout.my_spinner);
        bodyPartSpinner.setAdapter(adapterGroupsSpinner);
        bodyPartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                refreshExerciseSpinner();
                prepareChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<Integer> adapterweightSpinner = new ArrayAdapter<Integer>(ExerciseMonitor.this, R.layout.my_spinner, generateListWithNumbers(1, 12));
        adapterweightSpinner.setDropDownViewResource(R.layout.my_spinner);
        rangeSpinner.setAdapter(adapterweightSpinner);
        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value="";
                if(exerciseSpinner.getSelectedItem()!=null)
                    value = exerciseSpinner.getSelectedItem().toString();
                refreshExerciseSpinner();
                List<String> list ;
                list =service.getAllDoneExerciseAsStringList(bodyPartSpinner.getSelectedItem().toString(),i);
                if(!list.isEmpty() && !value.equals("")){
                    for(int j =0 ; j<list.size();j++){
                        if (list.get(j).equals(value)){
                            exerciseSpinner.setSelection(j);
                        }
                    }
                }
                prepareChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        refreshExerciseSpinner();
    }

    private void refreshExerciseSpinner() {
        ArrayAdapter<String> adapterExerciseSpinner = new ArrayAdapter<String>(ExerciseMonitor.this, R.layout.my_spinner,
                service.getAllDoneExerciseAsStringList(bodyPartSpinner.getSelectedItem().toString(), Integer.parseInt(rangeSpinner.getSelectedItem().toString())));
        adapterExerciseSpinner.setDropDownViewResource(R.layout.my_spinner);
        exerciseSpinner.setAdapter(adapterExerciseSpinner);
        exerciseSpinner.setOnItemSelectedListener(this);
    }


    private void prepareChart() {

        chart = (LineChart) findViewById(R.id.exercise_monitor_activity_chart);

        List<Entry> weights = new ArrayList<>();
        String text = "";
        if (exerciseSpinner.getSelectedItem() != null)
            for (DayEntry d : service.getAllDayEntryInRange(rangeSpinner.getSelectedItemPosition() + 1)) {
                int max = 0;
                boolean hasEntry = false;
                for (com.school.jakub.trainingplanmaker.model.Entry e : d.getEntrys()) {
                    if (e.getExercise().getName().equals(exerciseSpinner.getSelectedItem().toString()) && e.isFinished() == true && e.getWeight() >= max) {
                        hasEntry = true;
                        max = e.getWeight();
                    }
                }
                if (hasEntry) {
                    weights.add(new Entry(d.getDate().getTime(), (float) max));
                    Calendar c = Calendar.getInstance();
                    c.setTime(d.getDate());

                    text = text + "Data: " + c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR) + "\n";
                    text = text + "Cwiczenie: " + exerciseSpinner.getSelectedItem().toString() + "\n";
                    text = text + "Ciężar: " + max + "kg\n\n";
                }

            }

        ArrayList<ILineDataSet> lines = new ArrayList<ILineDataSet>();
        String[] xAxis = new String[weights.size()];

        chart.clear();
        chart.setNoDataText("Brak wykonanych ćwiczeń, uzupełnij dziennik");

        TextView entrys = (TextView) findViewById(R.id.exercise_monitor_activity_entrys);
        entrys.setText("Brak danych, uzupełnij dziennik");
        if (!weights.isEmpty()) {
            lines = new ArrayList<ILineDataSet>();
            xAxis = new String[weights.size()];
            LineDataSet lDataSet1 = new LineDataSet(weights, "Maksymalny ciężar");
            lDataSet1.setDrawFilled(true);
            lDataSet1.setFillColor(Color.BLUE);
            lDataSet1.setColor(Color.BLUE);
            lines.add(lDataSet1);
            chart.setData(new LineData(lines));
            chart.animateY(1000);
            Description d = new Description();
            d.setText("");
            chart.setDescription(d);
            XAxis xAxiss = chart.getXAxis();
            xAxiss.setValueFormatter(new DateValueFormatter());
            chart.notifyDataSetChanged();
            chart.invalidate();
            chart.getLegend().setWordWrapEnabled(true);

            entrys.setText(text);
        }
    }

    private void setupToolbar() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.exercise_monitor_activity, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.exercise_monitor_toolbar1);
        toolbar.setTitle("Monitor ćwiczeń");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private List<Integer> generateListWithNumbers(int from, int to) {
        List<Integer> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(i);
        }
        return list;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        prepareChart();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class DateValueFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Date date = new Date(new Float(value).longValue());
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            return c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1);
        }
    }


}
