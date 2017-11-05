package com.school.jakub.trainingplanmaker.controller.utils;

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
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.controller.exerciseMonitor.ExerciseMonitor;
import com.school.jakub.trainingplanmaker.controller.measurement.MeasurementMonitorActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ProfileMeasurementActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;
import com.school.jakub.trainingplanmaker.controller.atlas.AtlasActivity;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlans;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;

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
    public void onBackPressed() {
        System.out.println(" BACK");
        System.out.println(" BACK");
        System.out.println(" BACK");
        System.out.println(" BACK");
        System.out.println(" BACK");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawer(GravityCompat.START);
       // Intent intent;

        switch (item.getItemId()) {

            case R.id.drawer_menu_account: {
                Intent intent = new Intent(this, ProfileMeasurementActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            }
            case R.id.drawer_menu_diary: {
                Intent intent = new Intent(this, DiaryActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            }
            case R.id.drawer_menu_training_plans: {
                Intent intent = new Intent(this, TrainingPlans.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.drawer_menu_atlas: {
                Intent intent = new Intent(this, AtlasActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            }
            case R.id.drawer_menu_calculators: {
                System.out.println("drawer_menu_calculators");
                break;
            }
            case R.id.drawer_menu_monitor: {
                Intent intent = new Intent(this, ExerciseMonitor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            }
            case R.id.drawer_menu_weight: {
                Intent intent = new Intent(this, MeasurementMonitorActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            }
            case R.id.drawer_menu_backpack: {
                Intent intent = new Intent(this, BackpackActivity.class);
                startActivity(intent);
                break;
            }

        }
        return true;
    }
}
