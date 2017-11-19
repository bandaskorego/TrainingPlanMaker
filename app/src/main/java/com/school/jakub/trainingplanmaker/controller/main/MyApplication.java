package com.school.jakub.trainingplanmaker.controller.main;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Jakub on 07-Oct-17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("myDatabase.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);


    }
}
