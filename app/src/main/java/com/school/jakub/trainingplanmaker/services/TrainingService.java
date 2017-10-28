package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.model.MuscleGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jakub on 28-Oct-17.
 */

public class TrainingService {

    private Realm myRealm;

    public TrainingService() {
        myRealm = Realm.getDefaultInstance();
    }

    public List<MuscleGroup> getAllMuscleGroup() {
        RealmResults<MuscleGroup> list = myRealm.where(MuscleGroup.class).findAll();
        List<MuscleGroup> groups = new ArrayList<>(list);
        return groups;
    }

    public List<Exercise> getAllExercises() {
        RealmResults<Exercise> list = myRealm.where(Exercise.class).findAll();
        List<Exercise> exercises = new ArrayList<>(list);
        return exercises;
    }

    public void printAllMuscleGroups() {
        List<MuscleGroup> list = getAllMuscleGroup();
        for (MuscleGroup e : list)
            System.out.println(e.toString());
    }

    public void printAllExercises() {
        List<Exercise> list = getAllExercises();
        for (Exercise e : list)
            System.out.println(e.toString());
    }

    public void createBaseConfiguration() {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                MuscleGroup legs = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                legs.setName("Nogi");

                MuscleGroup abs = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                abs.setName("Brzuch");

                MuscleGroup chest = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                chest.setName("Klatka piersiowa");

                MuscleGroup back = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                back.setName("Plecy");

                MuscleGroup biceps = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                biceps.setName("Biceps");

                MuscleGroup triceps = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                triceps.setName("Triceps");

                MuscleGroup shoulders = myRealm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
                shoulders.setName("Barki");

                /*Klatka piersiowa*/
                Exercise c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE POZIOMEJ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("WYCISKANIE SZTANGIELEK W LEŻENIU NA ŁAWCE POZIOMEJ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ W GÓRĘ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("WYCISKANIE SZTANGIELEK W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ W GÓRĘ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ W DÓŁ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("ROZPIĘTKI ZE SZTANGIELKAMI W LEŻENIU NA ŁAWCE POZIOMEJ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("POMPKI NA PORĘCZACH");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(chest);
                c1.setName("KRZYŻOWANIE LINEK WYCIĄGU W STANIU");

                /* Nogi */

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("PRZYSIADY ZE SZTANGĄ NA KARKU");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("WYPYCHANIE CIĘŻARU NA SUWNICY");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("PROSTOWANIE NÓG NA MASZYNIE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("MARTWY CIĄG NA PROSTYCH NOGACH");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("WSPIĘCIA NA PALCE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(legs);
                c1.setName("WYKROKI");

                /* Brzuch */

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SKŁONY W LEŻENIU PŁASKO");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SKŁONY W LEŻENIU GŁOWĄ W DÓŁ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("UNOSZENIE NÓG W LEŻENIU NA SKOŚNEJ ŁAWCE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("UNOSZENIE NÓG W ZWISIE NA DRĄŻKU");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SKŁONY TUŁOWIA Z LINKĄ WYCIĄGU SIEDZĄC");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SKRĘTY TUŁOWIA");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SPIĘCIA BRZUCHA NA WYCIĄGU");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(abs);
                c1.setName("SKŁONY BOCZNE NA ŁAWCE");

                /* Plecy */

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE NA DRĄŻKU SZEROKIM UCHWYTEM (NACHWYT)");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE NA DRĄŻKU PODCHWYTEM");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE SZTANGI W OPADZIE(WIOSŁOWANIE)");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE SZTANGIELKI W OPADZIE(WIOSŁOWANIE)");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE KOŃCA SZTANGI W OPADZIE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PRZYCIĄGANIE LINKI WYCIĄGU DOLNEGO W SIADZIE PŁASKIM");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("MARTWY CIĄG");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(back);
                c1.setName("PODCIĄGANIE (WIOSŁOWANIE) W LEŻENIU NA ŁAWECZCE POZIOMEJ");

                /* Biceps */

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION ZE SZTANGĄ STOJAC PODCHWYTEM");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC PODCHWYTEM");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC (UCHWYT „MŁOTKOWY”)");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION ZE SZTANGĄ NA „MODLITEWNIKU”");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMIENIA ZE SZTANGIELKĄ NA „MODLITEWNIKU”");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION ZE SZTANGIELKAMI W SIADZIE NA ŁAWCE SKOŚNEJ(Z SUPINACJĄ NADGARSTKA)");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(biceps);
                c1.setName("UGINANIE RAMION PODCHWYTEM STOJĄC-Z RĄCZKĄ WYCIĄGU");

                /* Triceps */


                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("PROSTOWANIE RAMION NA WYCIĄGU STOJĄC");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("WYCISKANIE „FRANCUSKIE” SZTANGI W SIADZIE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("WYCISKANIE “FRANCUSKIE” JEDNORĄCZ SZTANGIELKI W SIADZIE");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("WYCISKANIE „FRANCUSKIE” SZTANGI W LEŻENIU");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("WYCISKANIE „FRANCUSKIE”SZTANGIELKI W LEŻENIU");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("PROSTOWNIE RAMIENIA ZE SZTANGIELKĄ W OPADZIE TUŁOWIA");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("PROSTOWANIE RAMION NA WYCIĄGU W PŁASZCZYŹNE POZIOMEJ STOJĄC");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("POMPKI NA PORĘCZACH");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(triceps);
                c1.setName("WYCISKANIE W LEŻENIU NA ŁAWCE POZIOMEJ WĄSKIM UCHWYTEM");

                /* Barki */


                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("WYCISKANIE SZTANGI SPRZED GŁOWY");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("WYCISKANIE SZTANGI ZZA GŁOWY");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("WYCISKANIE SZTANGIELEK");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("ARNOLDKI");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("UNOSZENIE SZTANGIELEK BOKIEM W GÓRĘ");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("UNOSZENIE SZTANGIELEK W OPADZIE TUŁOWIA");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("PODCIĄGANIE SZTANGI WZDŁUŻ TUŁOWIA");

                c1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c1.setMuscleGroup(shoulders);
                c1.setName("UNOSZENIE RAMION W PRZÓD ZE SZTANGIELKAMI");

            }
        });
    }

    public String getMuscleGroupName(int index){
        RealmResults<MuscleGroup> groups = myRealm.where(MuscleGroup.class).findAll();
        return groups.get(index-1).getName();
    }

    public List<Exercise> getExercisesFromCategory(int index) {
        RealmResults<Exercise> exercises;
        switch (index) {
            case 1:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Biceps")
                        .findAll();
                break;
            case 2:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Triceps")
                        .findAll();
                break;
            case 3:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Barki")
                        .findAll();
                break;
            case 4:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Klatka piersiowa")
                        .findAll();
                break;
            case 5:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Plecy")
                        .findAll();
                break;
            case 6:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Brzuch")
                        .findAll();
                break;
            case 7:
                exercises = myRealm.where(Exercise.class)
                        .equalTo("muscleGroup.name", "Nogi")
                        .findAll();
                break;
            default:
                exercises = myRealm.where(Exercise.class).findAll();
        }
        return new ArrayList<>(exercises);
    }

    public List<Exercise> getExercisesFromCategory(String muscleGroup) {
        RealmResults<Exercise> exercises = myRealm.where(Exercise.class)
                .equalTo("muscleGroup.name", muscleGroup)
                .findAll();

        return new ArrayList<>(exercises);
    }

    public boolean checkIfExerciseExists(String name){
        RealmResults<Exercise> groups = myRealm.where(Exercise.class)
                .equalTo("name", name)
                .findAll();

        return !groups.isEmpty();
    }

    public void addNewExercise(final String name, final String group){
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Exercise newExercise = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                newExercise.setName(name);
                newExercise.setMuscleGroup(myRealm.where(MuscleGroup.class).equalTo("name", group).findFirst());
            }
        });

    }

    public void removeExercise(final Exercise exercise) {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                exercise.deleteFromRealm();
            }
        });
    }
}
