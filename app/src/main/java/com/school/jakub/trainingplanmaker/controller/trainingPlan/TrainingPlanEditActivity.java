package com.school.jakub.trainingplanmaker.controller.trainingPlan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.BackpackAdapter;
import com.school.jakub.trainingplanmaker.adapters.seriesEditAdapter;
import com.school.jakub.trainingplanmaker.model.Series;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TrainingPlanEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText trainingName;
    Button addBtn;
    TextInputLayout layoutTrainingName;
    @Inject
    TrainingService service;
    TrainingPlan plan;
    ListAdapter adapter1;
    ListView listView;
    ArrayAdapter<String> adapter2;
    Spinner spinner_1;
    Spinner spinner_2;
    Spinner spinner_3;
    Spinner spinner_4;
    List<String> listSpiner1;
    List<String> listSpiner2;
    ArrayAdapter<String> adapterSpiner1;
    ArrayAdapter<String> adapterSpiner2;
    ArrayAdapter<Integer> adapterSpiner3;
    ArrayAdapter<Integer> adapterSpiner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_plan_edit_activity);
        AndroidInjection.inject(TrainingPlanEditActivity.this);

        initialise();
    }

    private void initialise() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.training_plans_activity_edit_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Plan treningowy");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addBtn = (Button) findViewById(R.id.training_plans_activity_edit_add_btn);

        trainingName = (EditText) findViewById(R.id.training_plans_activity_edit_training_name);
        layoutTrainingName = (TextInputLayout) findViewById(R.id.training_plans_activity_edit_text_wrapper);
        trainingName.addTextChangedListener(new MyTextWatcher(trainingName));
        String oldName;

        plan = service.getPlanByName(getIntent().getStringExtra("name"));
        oldName = getIntent().getStringExtra("name");
        trainingName.setText(oldName);
        validateName();

        listView = (ListView) findViewById(R.id.training_plans_activity_edit_series_listView);

        adapter1 = new seriesEditAdapter(this, service, plan.getName());
        listView.setAdapter(adapter1);

        spinner_1 = (Spinner) findViewById(R.id.training_plans_activity_edit_spinner_bodyPart);
        spinner_1.setOnItemSelectedListener(this);
        listSpiner1 = new ArrayList<String>(service.getAllMuscleGroupAsStringList());

        spinner_2 = (Spinner) findViewById(R.id.training_plans_activity_edit_spinner_Exercise);
        listSpiner2 = new ArrayList<String>(service.getExercisesFromCategoryAsStringList("Barki"));

        spinner_3 = (Spinner) findViewById(R.id.training_plans_activity_edit_spinner_series_number);
        spinner_4 = (Spinner) findViewById(R.id.training_plans_activity_edit_spinner_repetition_number);


        adapterSpiner1 = new ArrayAdapter<String>(this,R.layout.my_spinner, listSpiner1);
        adapterSpiner1.setDropDownViewResource(R.layout.my_spinner);
        spinner_1.setAdapter(adapterSpiner1);

        adapterSpiner2 = new ArrayAdapter<String>(this, R.layout.my_spinner, listSpiner2);
        adapterSpiner2.setDropDownViewResource(R.layout.my_spinner);
        spinner_2.setAdapter(adapterSpiner2);


        adapterSpiner3 = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateNumbersTo(10));
        adapterSpiner3.setDropDownViewResource(R.layout.my_spinner);
        spinner_3.setAdapter(adapterSpiner3);

        adapterSpiner4 = new ArrayAdapter<Integer>(this,R.layout.my_spinner, generateNumbersTo(40));
        adapterSpiner4.setDropDownViewResource(R.layout.my_spinner);
        spinner_4.setAdapter(adapterSpiner4);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!spinner_2.getSelectedItem().toString().equals("")) {
                    service.addSeriesToPlan(
                            plan.getId(),
                            spinner_2.getSelectedItem().toString(),
                            Integer.parseInt(spinner_3.getSelectedItem().toString()),
                            Integer.parseInt(spinner_4.getSelectedItem().toString())+1
                    );
                    Snackbar snackbar = Snackbar
                            .make(view, "Dodano", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    refreshListView();
                }else{
                    Snackbar snackbar = Snackbar
                            .make(view, "Wypełnij wszystkie pola", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                service.removeSeriesFromPlanByPlanId(plan.getId(), i);
                refreshListView();
                return false;
            }
        });

    }

    private List<Integer> generateNumbersTo(int to) {
        List<Integer> numbers = new ArrayList<>();
        for(int i=1; i<=to;i++){
            numbers.add(i);
        }
        return numbers;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateExerciseSpinner(parent.getItemAtPosition(position).toString());
    }

    private void updateExerciseSpinner(String name){
        listSpiner2 = new ArrayList<String>(service.getExercisesFromCategoryAsStringList(name));
        adapter2 = new ArrayAdapter<String>(this, R.layout.my_spinner, listSpiner2);
        adapter2.setDropDownViewResource(R.layout.my_spinner);
        spinner_2.setAdapter(adapter2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public void refreshListView(){
        adapter1 = new seriesEditAdapter(this, service, plan.getName());
        listView.setAdapter(adapter1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateName() {
        if (trainingName.getText().toString().trim().isEmpty()) {
            layoutTrainingName.setError("Nazwa treningu nie może być pusta");
            requestFocus(trainingName);
            return false;
        } else if (service.checkIfTrainingExist(trainingName.getText().toString())) {
            if (getIntent().hasExtra("name")) {
                if (!trainingName.getText().toString().equals(getIntent().getStringExtra("name"))) {
                    layoutTrainingName.setError("Posiadasz już plecak o tej nazwie");
                    requestFocus(trainingName);
                    return false;
                }
            } else {
                layoutTrainingName.setError("Posiadasz już plecak o tej nazwie");
                requestFocus(trainingName);
                return false;
            }
            return true;
        }
//            (!trainingName.getText().toString().equals(getIntent().getStringExtra("backpack_name"))){
//            layoutTrainingName.setError("Posiadasz już plecak o tej nazwie");
//            requestFocus(trainingName);
//            return false;
        else
            layoutTrainingName.setErrorEnabled(false);
        //service.setBackpackName(backpackName, etName.getText().toString());
        //backpackName = etName.getText().toString();
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            if (view.getId() == R.id.training_plans_activity_edit_training_name) {
                validateName();
            }
        }

    }
}
