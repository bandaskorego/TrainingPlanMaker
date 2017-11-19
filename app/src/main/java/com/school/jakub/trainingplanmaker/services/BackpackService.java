package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.Backpack;
import com.school.jakub.trainingplanmaker.model.Item;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * Created by Jakub on 10-Oct-17.
 */

public class BackpackService {

    private Realm myRealm;
    public BackpackService(){
        myRealm = Realm.getDefaultInstance();
    }

    public String createBagpack(final String name){
        myRealm.executeTransaction( new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {

                Backpack backpack = myRealm.createObject(Backpack.class, UUID.randomUUID().toString());
                backpack.setName(name);
            }
        });
        return name;
    }

    public void deleteBackpack(Backpack bp){
        final Backpack backpack = bp;
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                backpack.deleteFromRealm();
            }
        });
    }

    public boolean checkIfNameIsValid(String name){
        ArrayList<Backpack> list = new ArrayList();
        RealmQuery<Backpack> query = myRealm.where(Backpack.class)
        .equalTo("name",name);
        RealmResults<Backpack> result = query.findAll();
        return result.isEmpty();
    }

    public void setBackpackName(String oldName, String newName){
        final String oldname = oldName;
        final String newname = newName;
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ArrayList<Backpack> list = new ArrayList();
                RealmQuery<Backpack> query = myRealm.where(Backpack.class)
                        .equalTo("name",oldname);
                Backpack result = query.findFirst();
                result.setName(newname);

                realm.copyToRealmOrUpdate(result);
            }
        });
    }

    public ArrayList<Backpack> getAllBackpacks(){

        ArrayList<Backpack> list = new ArrayList(myRealm.where(Backpack.class).findAll());
        return list;
    }

    public Backpack getBackpackByName(String backpackName){
        return myRealm.where(Backpack.class)
                .equalTo("name", backpackName)
                .findFirst();
    }

    public boolean checkIfBackpackContainsItem(String backpackName,String itemName){
        Backpack backpack = getBackpackByName(backpackName);
        if(backpack.getItems().size()==0)
            return false;
        else{
            for(Item item : backpack.getItems()){
                if(item.getName().equals(itemName))
                    return true;
            }
        }
        return false;
    }

    public void addItemToBackpack(final String backpackName,final String itemName){

        myRealm.executeTransaction( new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                Item item = myRealm.createObject(Item.class, UUID.randomUUID().toString());
                item.setName(itemName);

                Backpack backpack = getBackpackByName(backpackName);
                backpack.getItems().add(item);

                realm.copyToRealmOrUpdate(backpack);
            }
        });
    }

    public ArrayList<Item> getAllItemsFromBackpack(String backpackName) {
        Backpack backpack = getBackpackByName(backpackName);
        return new ArrayList<Item>(backpack.getItems());
    }

    public void deleteItemFromBackpack(final String backpackName, final String itemName){
        final Backpack backpack  = getBackpackByName(backpackName);

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               for(int i = 0 , size = backpack.getItems().size() ; i < size ; i++){
                    if (backpack.getItems().get(i).getName().equals(itemName)){
                        backpack.getItems().remove(i);
                        break;
                    }
                }

                myRealm.copyToRealmOrUpdate(backpack);
            }
        });
    }

    public void changeItemName (final String backpackName,final String oldName, final String newName) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Backpack backpack  = getBackpackByName(backpackName);
                RealmList<Item> items = backpack.getItems();

                for(Item item : items)
                    if(item.getName().equals(oldName))
                        item.setName(newName);
            }
        });
    }
}
