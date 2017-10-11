package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
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

        ListAdapter listAdapter =
                new BackpackAdapter(this,backpackService);

        ListView shitList = (ListView) findViewById(R.id.backpack_list_view);
        shitList.setAdapter(listAdapter);

        fabAdd = (FloatingActionButton) contentView.findViewById(R.id.backpack_activity_fabOK);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BackpackActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.backpack_activity_new_popup, null);

                Button buttonOK = (Button) findViewById(R.id.backpack_activity_popup_OK);
                Button buttonCancel = (Button) findViewById(R.id.backpack_activity_popup_Cancel);

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText etName = (EditText) findViewById(R.id.backpack_activity_popup_textInputLayout_editText);
                        backpackService.createBagpack(etName.toString());

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog  = mBuilder.create();
                dialog.show();
            }
        });
    }
}
