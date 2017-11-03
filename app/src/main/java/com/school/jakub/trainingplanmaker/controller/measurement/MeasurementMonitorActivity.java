package com.school.jakub.trainingplanmaker.controller.measurement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.Measurement;
import com.school.jakub.trainingplanmaker.services.MeasurementService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeasurementMonitorActivity extends NavDrawer implements CompoundButton.OnCheckedChangeListener {

    LineChart lineChart;
    MeasurementService service;
    CheckBox bicepsLeft;
    CheckBox bicepsRight;
    CheckBox waist;
    CheckBox weight;
    CheckBox thighLeft;
    CheckBox thighRight;
    CheckBox chest;
    TextView entrys;
    Spinner months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpToolbar();


        initialise();
        generateChart();

    }

    private void generateChart() {

        List<Measurement> list = service.getMeasurementInRange(months.getSelectedItemPosition()+1);

        List<Entry> weightList = new ArrayList<Entry>();
        List<Entry> waistList = new ArrayList<Entry>();
        List<Entry> chestList = new ArrayList<Entry>();
        List<Entry> bicepsLeftList = new ArrayList<Entry>();
        List<Entry> bicepsRightList = new ArrayList<Entry>();
        List<Entry> thighLeftList = new ArrayList<Entry>();
        List<Entry> thighRightList = new ArrayList<Entry>();


        String text = "";
        for (Measurement m : list) {
            weightList.add(new Entry(m.getDate().getTime(), (float) m.getWeight()));
            waistList.add(new Entry(m.getDate().getTime(), (float) m.getWaist()));
            chestList.add(new Entry(m.getDate().getTime(), (float) m.getChest()));
            bicepsLeftList.add(new Entry(m.getDate().getTime(), (float) m.getBicepsLeft()));
            bicepsRightList.add(new Entry(m.getDate().getTime(), (float) m.getBicepsRight()));
            thighLeftList.add(new Entry(m.getDate().getTime(), (float) m.getThighLeft()));
            thighRightList.add(new Entry(m.getDate().getTime(), (float) m.getThighRight()));

            Calendar cal = Calendar.getInstance();
            cal.setTime(m.getDate());

            text += "Data pomiaru: " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR) + "\n";
            text += "Waga: " + m.getWeight() + " kg, ";
            text += "Talia: " + m.getWaist() + " cm, ";
            text += "Udo(l): " + m.getThighLeft()+ " cm, ";
            text += "Udo(p): " + m.getThighRight() + " cm, \n";
            text += "Pierś: " + m.getChest() + " cm, ";
            text += "Biceps(l): " + m.getBicepsLeft() + " cm, ";
            text += "Biceps(p): " + m.getBicepsRight() + " cm, \n\n";

        }
        entrys.setText(text);

        ArrayList<ILineDataSet> lines = new ArrayList<ILineDataSet>();
        String[] xAxis = new String[weightList.size()];

        if (weight.isChecked()) {
            LineDataSet lDataSet1 = new LineDataSet(weightList, "Waga");
            lDataSet1.setDrawFilled(true);
            lDataSet1.setFillColor(Color.BLUE);
            lDataSet1.setColor(Color.BLUE);
            lines.add(lDataSet1);
        }
        if (waist.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(waistList, "Talia");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.RED);
            lDataSet2.setColor(Color.RED);
            lines.add(lDataSet2);
        }
        if (chest.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(chestList, "Pierś");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.CYAN);
            lDataSet2.setColor(Color.CYAN);
            lines.add(lDataSet2);
        }
        if (chest.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(bicepsLeftList, "Pierś");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.CYAN);
            lDataSet2.setColor(Color.CYAN);
            lines.add(lDataSet2);
        }
        if (bicepsLeft.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(bicepsLeftList, "Biceps(l)");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.GREEN);
            lDataSet2.setColor(Color.GREEN);
            lines.add(lDataSet2);
        }
        if (bicepsRight.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(bicepsRightList, "Biceps(p)");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.YELLOW);
            lDataSet2.setColor(Color.YELLOW);
            lines.add(lDataSet2);
        }
        if (thighLeft.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(thighLeftList, "Udo(l)");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.LTGRAY);
            lDataSet2.setColor(Color.LTGRAY);
            lines.add(lDataSet2);
        }
        if (thighRight.isChecked()) {
            LineDataSet lDataSet2 = new LineDataSet(thighRightList, "Udo(p)");
            lDataSet2.setDrawFilled(true);
            lDataSet2.setFillColor(Color.MAGENTA);
            lDataSet2.setColor(Color.MAGENTA);
            lines.add(lDataSet2);
        }

        lineChart.setData(new LineData(lines));
        lineChart.animateY(1000);
        Description d = new Description();
        d.setText("");
        lineChart.setDescription(d);


//        LineDataSet dataSet = new LineDataSet(entries, "Label");
//        dataSet.setColor(Color.RED);
//        dataSet.setValueTextColor(Color.BLUE);
//
//        LineData lineData = new LineData(dataSet);
//        lineChart.setData(lineData);
//        lineChart.invalidate(); // refresh
        XAxis xAxiss = lineChart.getXAxis();
        xAxiss.setValueFormatter(new DateValueFormatter());
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.getLegend().setWordWrapEnabled(true);

    }

    private void setUpToolbar() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.measurement_monitor_activity, null, false);
        mDrawerLayout.addView(contentView, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.measurement_monitor_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Monitor ciała");


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initialise() {
        lineChart = (LineChart) findViewById(R.id.measurement_monitor_activity_linechart);
        service = new MeasurementService();


        bicepsLeft = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_biceps_left);
        bicepsRight = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_biceps_right);
        waist = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_waist);
        weight = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_weight);
        thighLeft = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_thigh_left);
        thighRight = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_thigh_right);
        chest = (CheckBox) findViewById(R.id.measurement_monitor_activity_checkbox_chest);
        months = (Spinner) findViewById(R.id.measurement_monitor_activity_spinner);
        entrys = (TextView) findViewById(R.id.measurement_monitor_activity_entrys);

        bicepsLeft.setChecked(true);
        bicepsRight.setChecked(true);
        waist.setChecked(true);
        weight.setChecked(true);
        thighLeft.setChecked(true);
        thighRight.setChecked(true);
        chest.setChecked(true);

        ArrayAdapter<Integer> adapterweightSpinner = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateListWithNumbers(1,12));
        adapterweightSpinner.setDropDownViewResource(R.layout.my_spinner);
        months.setAdapter(adapterweightSpinner);
        months.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                generateChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bicepsLeft.setOnCheckedChangeListener(this);
        bicepsRight.setOnCheckedChangeListener(this);
        waist.setOnCheckedChangeListener(this);
        weight.setOnCheckedChangeListener(this);
        thighLeft.setOnCheckedChangeListener(this);
        thighRight.setOnCheckedChangeListener(this);
        chest.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialise();
        generateChart();
    }

    private List<Integer> generateListWithNumbers(int from, int to) {
        List<Integer> list = new ArrayList<>();
        for(int i = from ; i <=to ; i++){
            list.add(i);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_measurement2options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.manage_measurement){
            Intent intent = new Intent(MeasurementMonitorActivity.this, ManageMeasurementsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.manage_measurement_add_measurement){
            Intent intent = new Intent(MeasurementMonitorActivity.this, ProfileMeasurementActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        generateChart();
    }

    public class DateValueFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Date date = new Date(new Float(value).longValue());
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            return c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH)+1); //+ "." + c.get(Calendar.YEAR);
        }
    }

}
