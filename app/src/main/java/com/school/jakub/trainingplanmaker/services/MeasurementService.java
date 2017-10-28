package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.Measurement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Jakub on 27-Oct-17.
 */

public class MeasurementService {

    private Realm myRealm;
    public MeasurementService() {
        myRealm = Realm.getDefaultInstance();
    }

    public boolean checkIfEntryExists(Date date){

        RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();

        for(Measurement m : measurements){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setTime(m.getDate());
            cal2.setTime(date);

            if((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                return true;
        }

        return false;
    }

    public void removeAllEntrys(){
        final RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                measurements.deleteAllFromRealm();
            }
        });
    }

    public void printAll(){
        RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();
        for (Measurement m : measurements){
            System.out.println(m.toString());
        }
    }

    public Measurement getMeasurementByDate(Date date){
        RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();

        for(Measurement m : measurements){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setTime(m.getDate());
            cal2.setTime(date);

            if((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                return m;
        }

        return null;
    }

    public void createEntry(final Measurement measurement){

        if(checkIfEntryExists(measurement.getDate())){
            System.out.println("Entry Exist");
            final Measurement m = getMeasurementByDate(measurement.getDate());
            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    System.out.println(" W teorii usuwam " + m.toString());
                    m.deleteFromRealm();
                    System.out.println(" Oraz dodaje " + measurement.toString());
                    myRealm.copyToRealmOrUpdate(measurement);
                }
            });

        }else{
            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    System.out.println("Nie istnieje, więc dodaje " + measurement.toString());
                    myRealm.copyToRealmOrUpdate(measurement);
                }
            });
        }
    }

    public Measurement getLastEntry(){

        RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();
        if(measurements.isEmpty())
            return null;
        RealmResults<Measurement> list = myRealm.where(Measurement.class).findAllSorted("date", Sort.DESCENDING);
        return list.first();
    }

    public List<Measurement> getAllMeasurement(){
        RealmResults<Measurement> measurements = myRealm.where(Measurement.class).findAll();
        if(measurements.isEmpty())
            return null;
        List<Measurement> list = new ArrayList<>(measurements);
        return list;
    }

}
