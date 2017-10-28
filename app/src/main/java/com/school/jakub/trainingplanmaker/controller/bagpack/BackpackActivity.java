package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
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
import com.school.jakub.trainingplanmaker.adapters.BackpackAdapter;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.Backpack;
import com.school.jakub.trainingplanmaker.services.BackpackService;

public class BackpackActivity extends NavDrawer {

    protected Toolbar toolbar;
    final private BackpackService service = new BackpackService();
    private FloatingActionButton fabAdd;
    ListAdapter listAdapter;
    ListView backpackList;

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

        listAdapter = new BackpackAdapter(this, service);

        backpackList = (ListView) findViewById(R.id.backpack_list_view);
        backpackList.setAdapter(listAdapter);

        backpackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Backpack bp = (Backpack)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(BackpackActivity.this, BackpackChecklistActivity.class);
                intent.putExtra("backpackName",bp.getName());
                startActivity(intent);
            }
        });

        backpackList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Backpack bp = (Backpack)adapterView.getItemAtPosition(i);
                new AlertDialog.Builder(adapterView.getContext())
                        .setMessage("Czy na pewno chcesz usunąć plecak?")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                service.deleteBackpack(bp);
                                updateListView();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Nie", null)
                        .show();
                return true;
            }
        });



        fabAdd = (FloatingActionButton) contentView.findViewById(R.id.backpack_activity_fabOK);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(BackpackActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.backpack_activity_new_popup, null);

                mBuilder.setView(mView);
                final AlertDialog dialog  = mBuilder.create();

                Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
                Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
                final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(service.checkIfNameIsValid(etName.getText().toString())) {
                            service.createBagpack(etName.getText().toString());
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
        listAdapter = new BackpackAdapter(this, service);
        backpackList.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }
}
