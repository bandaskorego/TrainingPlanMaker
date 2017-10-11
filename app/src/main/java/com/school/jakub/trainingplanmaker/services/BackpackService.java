package com.school.jakub.trainingplanmaker.services;

import io.realm.Realm;

/**
 * Created by Jakub on 10-Oct-17.
 */

public class BackpackService {

    private Realm myRealm;

    BackpackService(){
        myRealm = Realm.getDefaultInstance();
    }

    public void createBagpack(String name){



    }
}
