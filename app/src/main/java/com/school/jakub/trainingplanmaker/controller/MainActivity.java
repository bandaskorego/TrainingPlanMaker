package com.school.jakub.trainingplanmaker.controller;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.school.jakub.trainingplanmaker.R;

import io.realm.Realm;


public class MainActivity extends NavDrawer {

    public static final String TAG = MainActivity.class.getName();
    private Realm myRealm;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        mDrawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        myRealm = Realm.getDefaultInstance();
    }
}
