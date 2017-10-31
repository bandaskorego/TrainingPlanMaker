package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 30-Oct-17.
 */

public class Entry extends RealmObject{

    @PrimaryKey
    private String id;
    private Exercise exercise;
    private int repetition;
    private int weight;
    private boolean isFinished;




    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", exercise=" + exercise +
                ", repetition=" + repetition +
                ", weight=" + weight +
                ", isFinished=" + isFinished +
                '}';
    }

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

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
