package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 07-Oct-17.
 */

public class Item extends RealmObject {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
