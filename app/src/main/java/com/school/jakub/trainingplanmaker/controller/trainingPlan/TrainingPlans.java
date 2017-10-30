package com.school.jakub.trainingplanmaker.controller.trainingPlan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.TrainingPlansAdapter;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackEditActivity;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.Item;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;
import com.school.jakub.trainingplanmaker.services.TrainingService;

public class TrainingPlans extends NavDrawer {

    TrainingService service;
//    ListAdapter defaultPlanAdapter;
//    ListView defaultPlansList;

    ListAdapter userPlanAdapter;
    ListView userPlansList;
    Button newPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.training_plans_activity, null, false);
        mDrawerLayout.addView(contentView, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.training_plans_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Plany treningowe");

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        initialise();
//
//        defaultPlanAdapter = new TrainingPlansAdapter(this, service.getAllDefaultPlans());
//        defaultPlansList = (ListView) findViewById(R.id.training_plans_activity_default_plans_list);
//        defaultPlansList.setAdapter(defaultPlanAdapter);



//        setContentView(R.layout.training_plans_activity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.training_plans_activity_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initialise() {
        service = new TrainingService();
//        defaultPlanAdapter = new TrainingPlansAdapter(this, service.getAllDefaultPlans());
//        defaultPlansList = (ListView) findViewById(R.id.training_plans_activity_default_plans_list);
//        defaultPlansList.setAdapter(defaultPlanAdapter);

        userPlanAdapter = new TrainingPlansAdapter(this, service.getAllDefaultPlans());
        userPlansList = (ListView) findViewById(R.id.training_plans_activity_user_plans_list);
        userPlansList.setAdapter(userPlanAdapter);

        userPlansList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrainingPlan plan = (TrainingPlan)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(adapterView.getContext(), TrainingPlanSelect.class);
                intent.putExtra("name",plan.getName());
                startActivity(intent);
            }
        });


        newPlan = (Button) findViewById(R.id.training_plans_activity_add_new_plan_btn);
        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(TrainingPlans.this);
                final View mView = getLayoutInflater().inflate(R.layout.backpack_edit_activity_new_popup, null);

                mBuilder.setView(mView);
                final AlertDialog dialog  = mBuilder.create();

                Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
                Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
                final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);
                etName.setHint("Nazwa planu");
                buttonOK.setText("Dodaj");

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(etName.getText().toString().trim().equals("")){
                            Snackbar snackbar = Snackbar
                                    .make(view, "Wprowadz nazwe", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else if(!service.checkIfTrainingExist(etName.getText().toString()) ) {
                            service.createNewPlan(etName.getText().toString());
                            dialog.cancel();
                            Intent i = new Intent(TrainingPlans.this, TrainingPlanEditActivity.class);
                            i.putExtra("name", etName.getText().toString().trim());
                            startActivity(i);
                        }else{
                            Snackbar snackbar = Snackbar
                                    .make(view, "Nazwa jest zajęta", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

}
