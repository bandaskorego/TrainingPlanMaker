package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Jakub on 18-Nov-17.
 */

public class Account extends RealmObject {

    String name;
    Diary diary;
    RealmList<Backpack> backpacks;
    RealmList<TrainingPlan> trainingPlans;
    RealmList<Measurement> measurement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public RealmList<Backpack> getBackpacks() {
        return backpacks;
    }

    public void setBackpacks(RealmList<Backpack> backpacks) {
        this.backpacks = backpacks;
    }

    public RealmList<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(RealmList<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public RealmList<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(RealmList<Measurement> measurement) {
        this.measurement = measurement;
    }
}
