package com.school.jakub.trainingplanmaker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;

/**
 * Created by Jakub on 09-Oct-17.
 */

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout mDrawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawer(GravityCompat.START);
       // Intent intent;

        switch (item.getItemId()) {

            case R.id.drawer_menu_account: {
                System.out.println("drawer_menu_account");
                break;
            }
            case R.id.drawer_menu_diary: {
                System.out.println("drawer_menu_diary");
                break;
            }
            case R.id.drawer_menu_training_plans: {
                System.out.println("drawer_menu_training_plans");
                break;
            }
            case R.id.drawer_menu_atlas: {
                System.out.println("drawer_menu_atlas");
                break;
            }
            case R.id.drawer_menu_calculators: {
                System.out.println("drawer_menu_calculators");
                break;
            }
            case R.id.drawer_menu_monitor: {
                System.out.println("drawer_menu_monitor");
                break;
            }
            case R.id.drawer_menu_weight: {
                System.out.println("drawer_menu_weight");
                break;
            }
            case R.id.drawer_menu_backpack: {
                System.out.println("drawer_menu_backpack");
                Intent intent = new Intent(this, BackpackActivity.class);
                startActivity(intent);
                break;
            }

        }
        return true;
    }
}
