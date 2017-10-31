package com.school.jakub.trainingplanmaker.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 30-Oct-17.
 */

public class DayEntry extends RealmObject {

    @PrimaryKey
    private String id;
    private Date date;
    private RealmList<Entry> entrys = new RealmList<>();


    @Override
    public String toString() {
        return "DayEntry{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", entrys=" + entrys +
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

    public RealmList<Entry> getEntrys() {
        return entrys;
    }

    public void setEntrys(RealmList<Entry> entrys) {
        this.entrys = entrys;
    }
}
