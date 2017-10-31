package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.DayEntry;
import com.school.jakub.trainingplanmaker.model.Diary;
import com.school.jakub.trainingplanmaker.model.Entry;
import com.school.jakub.trainingplanmaker.model.Exercise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Jakub on 30-Oct-17.
 */

public class DiaryService {

    private Realm myRealm;
    public DiaryService(){
        myRealm = Realm.getDefaultInstance();
    }

    public void createFirstDiary(){
        if(myRealm.where(Diary.class).findAll().isEmpty()) {
            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Diary diary = myRealm.createObject(Diary.class, UUID.randomUUID().toString());
                    diary.setDays(new RealmList<DayEntry>());
                    myRealm.copyToRealmOrUpdate(diary);
                }
            });
        }
    }

    public boolean checkIfTodaysDayEntryExist() {

        RealmResults<DayEntry> entrys = myRealm.where(DayEntry.class).findAll();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        for(DayEntry entry : entrys){
            cal2.setTime(entry.getDate());
            if((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                return true;
        }
        return false;
    }

    public boolean checkIfDayEntryExist(Date date){
        RealmResults<DayEntry> entrys = myRealm.where(DayEntry.class).findAll();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        for(DayEntry entry : entrys){
            cal2.setTime(entry.getDate());
            if((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                return true;
        }
        return false;
    }

    public DayEntry getDayEntryByDate(Date date){
        RealmResults<DayEntry> entrys = myRealm.where(DayEntry.class).findAll();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        for(DayEntry entry : entrys){
            cal2.setTime(entry.getDate());
            if((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                return entry;
        }
        return null;
    }

    public DayEntry createNewDayEntry(final Date date){

        if(checkIfDayEntryExist(date)){
            System.out.println(" Podany DayEntry ISTNIEJE");
            System.out.println(" Podany DayEntry ISTNIEJE");
            System.out.println(" Podany DayEntry ISTNIEJE");
            System.out.println(" Podany DayEntry ISTNIEJE");
        }else{
            myRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Diary diary = myRealm.where(Diary.class).findFirst();

                    DayEntry dayEntry = myRealm.createObject(DayEntry.class, UUID.randomUUID().toString());
                    dayEntry.setDate(date);
                    myRealm.copyToRealmOrUpdate(dayEntry);

                    diary.getDays().add(dayEntry);
                    myRealm.copyToRealmOrUpdate(diary);
                }
            });
            return getDayEntryByDate(date);
        }




        return null;
    }

    public List<Entry> getAllEntrysFromDayEntryById(String dayEntryId) {

        DayEntry dayEntry = myRealm.where(DayEntry.class)
                .equalTo("id",dayEntryId)
                .findFirst();

        return new ArrayList<>(dayEntry.getEntrys());
    }

    public void addEntrysToDayEntry(final String exerciseName,final int series,final int repetition,final int weight,final boolean ifFinished, Date date) {
        final DayEntry dayEntry = getDayEntryByDate(date);
        final Exercise exercise = myRealm.where(Exercise.class)
                .equalTo("name", exerciseName)
                .findFirst();

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(int i = 0; i < series ; i++)
                {
                    Entry entry = myRealm.createObject(Entry.class, UUID.randomUUID().toString());
                    entry.setExercise(exercise);
                    entry.setRepetition(repetition);
                    entry.setWeight(weight);
                    entry.setFinished(ifFinished);
                    dayEntry.getEntrys().add(entry);
                }
                myRealm.copyToRealmOrUpdate(dayEntry);
            }
        });
    }
}
