package com.school.jakub.trainingplanmaker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jakub on 28-Oct-17.
 */

public class MuscleGroup extends RealmObject {

    @PrimaryKey
    String id;
    String name;

    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
}
