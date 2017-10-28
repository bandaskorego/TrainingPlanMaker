package com.school.jakub.trainingplanmaker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
    private int bicepsLeft;
    private int bicepsRight;
    private int chest;
    private int waist;
    private int thighLeft;
    private int thighRight;

    public Measurement() {
        this.id = UUID.randomUUID().toString();
        date = new Date();
        this.weight = 50;
        this.bicepsLeft = 25;
        this.bicepsRight = 25;
        this.chest = 90;
        this.waist = 60;
        this.thighLeft = 40;
        this.thighRight = 40;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", weight=" + weight +
                ", bicepsLeft=" + bicepsLeft +
                ", bicepsRight=" + bicepsRight +
                ", chest=" + chest +
                ", waist=" + waist +
                ", thighLeft=" + thighLeft +
                ", thighRight=" + thighRight +
                '}';
    }

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

    public int getBicepsLeft() {
        return bicepsLeft;
    }

    public void setBicepsLeft(int bicepsLeft) {
        this.bicepsLeft = bicepsLeft;
    }

    public int getBicepsRight() {
        return bicepsRight;
    }

    public void setBicepsRight(int bicepsRight) {
        this.bicepsRight = bicepsRight;
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

    public int getThighLeft() {
        return thighLeft;
    }

    public void setThighLeft(int thighLeft) {
        this.thighLeft = thighLeft;
    }

    public int getThighRight() {
        return thighRight;
    }

    public void setThighRight(int thighRight) {
        this.thighRight = thighRight;
    }
}
