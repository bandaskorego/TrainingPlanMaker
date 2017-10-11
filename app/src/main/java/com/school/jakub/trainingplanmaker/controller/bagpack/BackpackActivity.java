package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.BackpackAdapter;
import com.school.jakub.trainingplanmaker.controller.NavDrawer;
import com.school.jakub.trainingplanmaker.services.BackpackService;

public class BackpackActivity extends NavDrawer {

    protected Toolbar toolbar;
    private BackpackService backpackService;
    private FloatingActionButton fabAdd;
    ListAdapter listAdapter;
    ListView shitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.backpack_activity_layout, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.backpack_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Twoje plecaki");


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        backpackService = new BackpackService();
        listAdapter = new BackpackAdapter(this,backpackService);

        shitList = (ListView) findViewById(R.id.backpack_list_view);
        shitList.setAdapter(listAdapter);

        fabAdd = (FloatingActionButton) contentView.findViewById(R.id.backpack_activity_fabOK);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(BackpackActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.backpack_activity_new_popup, null);

                mBuilder.setView(mView);
                final AlertDialog dialog  = mBuilder.create();

                Button buttonOK = (Button) mView.findViewById(R.id.backpack_activity_popup_OK);
                Button buttonCancel = (Button) mView.findViewById(R.id.backpack_activity_popup_Cancel);
                final EditText etName = (EditText) mView.findViewById(R.id.backpack_activity_popup_textInputLayout_editText);

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(backpackService.checkIfNameIsValid(etName.getText().toString())) {
                            backpackService.createBagpack(etName.getText().toString());
                            updateListView();
                            dialog.cancel();
                        }else{
                            Snackbar snackbar = Snackbar
                                    .make(view, "Nazwa jest zajęta", Snackbar.LENGTH_LONG);
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

//                mBuilder.setView(mView);
//                AlertDialog dialog  = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void updateListView() {
        listAdapter = new BackpackAdapter(this,backpackService);
        shitList.setAdapter(listAdapter);
    }

}
