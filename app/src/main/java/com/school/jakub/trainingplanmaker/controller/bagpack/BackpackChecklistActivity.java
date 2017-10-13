package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

public class BackpackChecklistActivity extends AppCompatActivity {

    String backpackName;
    final private BackpackService service = new BackpackService();
    ListAdapter listAdapter;
    ListView itemList;
    List<String> colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backpack_checklist_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.backpack_checklist_toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        backpackName = i.getStringExtra("backpack_name");

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Twoja checklista");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listAdapter = new BackpackChecklistAdapter(this, service, backpackName);
        itemList = (ListView) findViewById(R.id.backpack_checklist_listView);
        itemList.setAdapter(listAdapter);

        colors = fillColors();

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if("Red".equals(colors.get(i))) {
                    adapterView.getChildAt(i).setBackgroundColor(Color.GREEN);
                    colors.set(i,"Blue");
                    if(checkIfAllTaken()){
                        Snackbar snackbar = Snackbar
                                .make(view, "Masz już wszystko", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
                else {
                    adapterView.getChildAt(i).setBackgroundColor(Color.RED);
                    colors.set(i, "Red");
                }
            }
        });
    }

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
