package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 28-Oct-17.
 */

public class TrainingPlan extends RealmObject{

    @PrimaryKey
    String id;
    String name;
    RealmList<Series> series = new RealmList<>();
    boolean defaultPlan;



    @Override
    public String toString() {
        return "TrainingPlan{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", series=" + series +
                '}';
    }

    public boolean isDefaultPlan() {
        return defaultPlan;
    }

    public void setDefaultPlan(boolean defaultPlan) {
        this.defaultPlan = defaultPlan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Series> getSeries() {
        return series;
    }

    public void setSeries(RealmList<Series> series) {
        this.series = series;
    }
}
