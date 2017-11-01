package com.school.jakub.trainingplanmaker.controller.diary;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.DiaryAdapter;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.DayEntry;
import com.school.jakub.trainingplanmaker.model.Diary;
import com.school.jakub.trainingplanmaker.model.Entry;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.DiaryService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiaryActivity extends NavDrawer {

    Toolbar toolbar;
    TextView dateTextView;
    ImageView leftImage;
    ImageView rightImage;
    ListView listView;
    Button addExerciseBtn;
    Button addPlanBtn;
    DiaryService service;
    DayEntry dayEntry;
    Context context;
    ArrayAdapter<Entry> adapter;

    Calendar myCalendar = Calendar.getInstance();
    //    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
//    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = context;

        initialise();
        setUpDatepickerLogic();
    }

    private void setUpDatepickerLogic() {
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Intent intent = new Intent(DiaryActivity.this, DiaryActivity.class);
                intent.putExtra("day", dayOfMonth);
                intent.putExtra("month", monthOfYear);
                intent.putExtra("year", year);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        };

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar.setTime(dayEntry.getDate());
                new DatePickerDialog(DiaryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void initialise() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.diary_activity, null, false);
        mDrawerLayout.addView(contentView, 0);
        toolbar = (Toolbar) findViewById(R.id.diary_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dziennik treningowy");
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        dateTextView = (TextView) findViewById(R.id.diary_activity_textdate);
        leftImage = (ImageView) findViewById(R.id.diary_activity_arrowback);
        rightImage = (ImageView) findViewById(R.id.diary_activity_arrownext);
        listView = (ListView) findViewById(R.id.diary_activity_listview);
        addExerciseBtn = (Button) findViewById(R.id.diary_activity_add_exercise);
        addPlanBtn = (Button) findViewById(R.id.diary_activity_add_plan);
        service = new DiaryService();

        readDayEntry();
        setDateTextView();
        addLisners();
        refreshListView();

        System.out.println("LISTUJE WPISY");
        for (Entry e : dayEntry.getEntrys()) {
            System.out.println(e.toString());
        }


    }

    public void setSelection(int n) {
        listView.setSelection(n);
    }

    public void refreshListView() {
        adapter = new DiaryAdapter(DiaryActivity.this, service, dayEntry.getId());
        listView.setAdapter(adapter);
    }

    private void addLisners() {

        addPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(DiaryActivity.this);
                b.setTitle("Wybierz plan");
                final List<TrainingPlan> plans = service.getAllTrainingPlans();
                String[] types = new String[plans.size()];
                for (int i = 0; i < plans.size(); i++) {
                    types[i] = plans.get(i).getName();
                }

                b.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        service.addAllSeriesToDiary(plans.get(i), dayEntry);
                        refreshListView();
                    }
                });
                if (plans.size() != 0) {
                    b.show();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Musisz najpierw stworzyć plan", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, AddExerciseToDayEntry.class);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dayEntry.getDate());
                intent.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("month", cal.get(Calendar.MONTH));
                intent.putExtra("year", cal.get(Calendar.YEAR));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                finish();
            }
        });

        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, DiaryActivity.class);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dayEntry.getDate());
                cal.add(Calendar.DAY_OF_MONTH, -1);
                intent.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("month", cal.get(Calendar.MONTH));
                intent.putExtra("year", cal.get(Calendar.YEAR));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        rightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, DiaryActivity.class);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dayEntry.getDate());
                cal.add(Calendar.DAY_OF_MONTH, +1);
                intent.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("month", cal.get(Calendar.MONTH));
                intent.putExtra("year", cal.get(Calendar.YEAR));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void readDayEntry() {
        if (getIntent().hasExtra("day")) {

            int day = getIntent().getIntExtra("day", 0);
            int month = getIntent().getIntExtra("month", 0);
            int year = getIntent().getIntExtra("year", 0);
            year -= 1900;

            Date tmpDate = new Date(year, month, day, 0, 0, 0);
            if (service.checkIfDayEntryExist(tmpDate)) {
                dayEntry = service.getDayEntryByDate(tmpDate);
            } else {
                service.createNewDayEntry(tmpDate);
                dayEntry = service.getDayEntryByDate(tmpDate);
            }
//
//            Calendar cal = Calendar.getInstance();
//            //cal.set(2017,9,32);
//            cal.setTime(tmpDate);
//
//            //USUNAC
//            dayEntry = service.getDayEntryByDate(new Date());

        } else {
            if (service.checkIfTodaysDayEntryExist()) {
                dayEntry = service.getDayEntryByDate(new Date());
            } else {
                service.createNewDayEntry(new Date());
                dayEntry = service.getDayEntryByDate(new Date());
            }
        }
    }

    private void setDateTextView() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dayEntry.getDate());
        dateTextView.setText(cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR));
    }


}
