package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 30-Oct-17.
 */

public class Diary extends RealmObject{

    @PrimaryKey
    private String id;
    private RealmList<DayEntry> days = new RealmList<>();

    @Override
    public String toString() {
        return "Diary{" +
                "id='" + id + '\'' +
                ", days=" + days +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<DayEntry> getDays() {
        return days;
    }

    public void setDays(RealmList<DayEntry> days) {
        this.days = days;
    }
}
