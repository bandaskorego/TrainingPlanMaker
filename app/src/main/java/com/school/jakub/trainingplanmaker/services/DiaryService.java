package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.DayEntry;
import com.school.jakub.trainingplanmaker.model.Diary;
import com.school.jakub.trainingplanmaker.model.Entry;
import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.model.MuscleGroup;
import com.school.jakub.trainingplanmaker.model.Series;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;

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

    public void setNumberOfRepetition(final Entry item,final int repetition) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.setRepetition(repetition);
            }
        });
    }

    public void setWeightOfEntry(final Entry item, final int i) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.setWeight(i);
            }
        });
    }

    public void swapSeriesPositionInPlan(String dayEntryId,final int pos1,final int pos2) {
        final DayEntry dayEntry = myRealm.where(DayEntry.class)
                .equalTo("id",dayEntryId)
                .findFirst();

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(pos2>-1 && pos1 < dayEntry.getEntrys().size()){
                    dayEntry.getEntrys().move(pos1, pos2);
                }
            }
        });
    }

    public void setFinishedStatusOfEntry(final Entry item) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.setFinished(!item.isFinished());
            }
        });
    }

    public void removeEntry(final Entry item) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.deleteFromRealm();
            }
        });
    }

    public List<TrainingPlan> getAllTrainingPlans() {
        RealmResults<TrainingPlan> plans = myRealm.where(TrainingPlan.class).findAll();
        return new ArrayList<>(plans);
    }

    public void addAllSeriesToDiary(final TrainingPlan plan, final DayEntry dayEntry) {
        System.out.println(plan.getName());
        System.out.println(plan.getName());
        System.out.println(plan.getName());
        System.out.println(plan.getName());

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(Series s : plan.getSeries()){
                    Entry e = myRealm.createObject(Entry.class, UUID.randomUUID().toString());
                    e.setFinished(false);
                    e.setExercise(s.getExercise());
                    e.setRepetition(s.getNumberOfRepetitions());
                    e.setWeight(0);
                    dayEntry.getEntrys().add(e);
                }
                myRealm.copyToRealmOrUpdate(dayEntry);
            }
        });

    }

    public List<String> getAllMuscleGroup() {
        RealmResults<MuscleGroup> groups = myRealm.where(MuscleGroup.class).findAll();
        List<String> list = new ArrayList<>();
        for(MuscleGroup m : groups) {
            list.add(m.getName());
            System.out.println(m.getName());
        }
        return list;
    }

    public List<String> getAllDoneExerciseAsStringList(String s, int range) {
        List<String> exercises = new ArrayList<>();
        RealmResults<DayEntry> dayEntries = myRealm.where(DayEntry.class).findAll();
        if(!dayEntries.isEmpty()){
            Calendar cal = Calendar.getInstance();
            Date to = new Date();
            Date from = new Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH)-range,cal.get(Calendar.DAY_OF_MONTH));
            for(DayEntry day : dayEntries){
                if (day.getDate().after(from) && day.getDate().before(to)){
                    for(Entry e : day.getEntrys()){
                        if (!exercises.contains(e.getExercise().getName()) && e.getExercise().getMuscleGroup().getName().equals(s) && e.isFinished())
                            exercises.add(e.getExercise().getName());
                    }
                }
            }
        }else{
            return exercises;
        }
    return exercises;
    }

    public List<DayEntry> getAllDayEntryInRange(int range){
        RealmResults<DayEntry> dayEntries = myRealm.where(DayEntry.class).findAll().sort("date");
        List<DayEntry> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date to = new Date();
        Date from = new Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH)-range,cal.get(Calendar.DAY_OF_MONTH));
        for(DayEntry d : dayEntries)
            if (!d.getEntrys().isEmpty() && d.getDate().after(from) && d.getDate().before(to))
                list.add(d);
        return list;
    }

}
