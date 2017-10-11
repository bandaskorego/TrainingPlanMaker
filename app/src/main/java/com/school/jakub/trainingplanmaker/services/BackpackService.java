package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.Backpack;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Jakub on 10-Oct-17.
 */

public class BackpackService {

    private Realm myRealm;
    public BackpackService(){
        myRealm = Realm.getDefaultInstance();
    }

    public String createBagpack(String name){

        final String backpackName = name;

        myRealm.executeTransaction( new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {

                Backpack backpack = myRealm.createObject(Backpack.class, backpackName);
            }
        });

        return name;
    }

    public ArrayList<Backpack> getAllBackpacks(){

        ArrayList<Backpack> list = new ArrayList(myRealm.where(Backpack.class).findAll());
        return list;
    }




}
