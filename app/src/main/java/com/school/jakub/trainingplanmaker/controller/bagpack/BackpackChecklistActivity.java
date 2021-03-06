package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.BackpackChecklistAdapter;
import com.school.jakub.trainingplanmaker.adapters.BackpackItemEditAdapter;
import com.school.jakub.trainingplanmaker.services.BackpackService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class BackpackChecklistActivity extends AppCompatActivity {

    String backpackName;
    @Inject
    BackpackService service;
    ListAdapter listAdapter;
    ListView itemList;
    List<String> colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(BackpackChecklistActivity.this);
        setContentView(R.layout.backpack_checklist_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.backpack_checklist_toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        backpackName = i.getStringExtra("backpackName");

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Przedmioty do zabrania");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listAdapter = new BackpackChecklistAdapter(this, service.getAllItemsFromBackpack(backpackName), backpackName);
        itemList = (ListView) findViewById(R.id.backpack_checklist_listView);
        itemList.setAdapter(listAdapter);

        colors = fillColors();

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if("Red".equals(colors.get(i))) {

                    CardView card = (CardView) adapterView.getChildAt(i).findViewById(R.id.backpack_checklist_item_row_cardView);
                    card.setCardBackgroundColor(Color.GREEN);
                    colors.set(i,"Blue");
                    if(checkIfAllTaken()){
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    BackpackChecklistActivity.this.finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        Snackbar snackbar = Snackbar
                                .make(view, "Masz już wszystko, możesz ruszać na trening", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        thread.start();

                    }
                }
                else {
                    CardView card = (CardView) adapterView.getChildAt(i).findViewById(R.id.backpack_checklist_item_row_cardView);
                    card.setCardBackgroundColor(Color.RED);
                    colors.set(i, "Red");
                }
            }
        });
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(3500);
                BackpackChecklistActivity.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private boolean checkIfAllTaken(){
        for(String s : colors){
            if(s.equals("Red"))
                return false;
        }
        return true;
    }

    private List fillColors() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < service.getAllItemsFromBackpack(backpackName).size(); i++)
            list.add("Red");
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
