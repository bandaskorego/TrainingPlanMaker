package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Jakub on 28-Oct-17.
 */


 public class Series extends RealmObject{

    @PrimaryKey
    String id;
    Exercise exercise;
    int numberOfRepetitions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }


    @Override
    public String toString() {
        return "Series{" +
                "id='" + id + '\'' +
                ", exercise=" + exercise +
                ", numberOfRepetitions=" + numberOfRepetitions +
                '}';
    }

}
