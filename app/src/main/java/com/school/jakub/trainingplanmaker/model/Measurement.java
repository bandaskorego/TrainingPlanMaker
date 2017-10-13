package com.school.jakub.trainingplanmaker.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 13-Oct-17.
 */

public class Measurement extends RealmObject {

    @PrimaryKey
    private String id;
    private Date date;
    private int weight;
    private int biceps;
    private int chest;
    private int waist;
    private int thigh;
    private int calf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBiceps() {
        return biceps;
    }

    public void setBiceps(int biceps) {
        this.biceps = biceps;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getThigh() {
        return thigh;
    }

    public void setThigh(int thigh) {
        this.thigh = thigh;
    }

    public int getCalf() {
        return calf;
    }

    public void setCalf(int calf) {
        this.calf = calf;
    }
}
