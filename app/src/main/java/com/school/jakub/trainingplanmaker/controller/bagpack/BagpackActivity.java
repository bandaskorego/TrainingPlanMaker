package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.Context;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.BackpackAdapter;
import com.school.jakub.trainingplanmaker.controller.NavDrawer;

public class BagpackActivity extends NavDrawer {

    protected Toolbar toolbar;

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

        String[] list = { "KROPLOWKA KROASDASDASDASDASDDASDFASFSAFSAF" , " test1 ","test " , " test1 ","test " , " test1 ", "KROPLOWKA KROASDASDASDASDASDDASDFASFSAFSAF" , " test1 ","test " , " test1 ","test " , " test1 "};


        ListAdapter listAdapter =
                new BackpackAdapter(this,list);

        ListView shitList = (ListView) findViewById(R.id.backpack_list_view);
        shitList.setAdapter(listAdapter);
    }
}
