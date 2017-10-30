package com.school.jakub.trainingplanmaker.services;

import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.model.MuscleGroup;
import com.school.jakub.trainingplanmaker.model.Series;
import com.school.jakub.trainingplanmaker.model.TrainingPlan;

import java.security.spec.ECField;
import java.sql.ResultSet;
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
                c1.setName("wyciskanie sztangi w leżeniu na ławce poziomej");

                Exercise c2;
                c2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c2.setMuscleGroup(chest);
                c2.setName("wyciskanie sztangielek w leżeniu na ławce poziomej");

                Exercise c3;
                c3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c3.setMuscleGroup(chest);
                c3.setName("wyciskanie sztangi w leżeniu na ławce skośnej-głową w górę");

                Exercise c4;
                c4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c4.setMuscleGroup(chest);
                c4.setName("wyciskanie sztangielek w leżeniu na ławce skośnej-głową w górę");

                Exercise c5;
                c5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c5.setMuscleGroup(chest);
                c5.setName("wyciskanie sztangi w leżeniu na ławce skośnej-głową w dół");

                Exercise c6;
                c6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c6.setMuscleGroup(chest);
                c6.setName("rozpiętki ze sztangielkami w leżeniu na ławce poziomej");

                Exercise c7;
                c7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c7.setMuscleGroup(chest);
                c7.setName("pompki na poręczach");

                Exercise c8;
                c8 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                c8.setMuscleGroup(chest);
                c8.setName("krzyżowanie linek wyciągu w staniu");

                /* Nogi */

                Exercise n1;
                n1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n1.setMuscleGroup(legs);
                n1.setName("PRZYSIADY ZE SZTANGĄ NA KARKU");

                Exercise n2;
                n2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n2.setMuscleGroup(legs);
                n2.setName("WYPYCHANIE CIĘŻARU NA SUWNICY");

                Exercise n3;
                n3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n3.setMuscleGroup(legs);
                n3.setName("PROSTOWANIE NÓG NA MASZYNIE");

                Exercise n4;
                n4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n4.setMuscleGroup(legs);
                n4.setName("MARTWY CIĄG NA PROSTYCH NOGACH");

                Exercise n5;
                n5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n5.setMuscleGroup(legs);
                n5.setName("WSPIĘCIA NA PALCE");

                Exercise n6;
                n6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                n6.setMuscleGroup(legs);
                n6.setName("WYKROKI");

                /* Brzuch */

                Exercise b1;
                b1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b1.setMuscleGroup(abs);
                b1.setName("SKŁONY W LEŻENIU PŁASKO");

                Exercise b2;
                b2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b2.setMuscleGroup(abs);
                b2.setName("SKŁONY W LEŻENIU GŁOWĄ W DÓŁ");

                Exercise b3;
                b3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b3.setMuscleGroup(abs);
                b3.setName("UNOSZENIE NÓG W LEŻENIU NA SKOŚNEJ ŁAWCE");

                Exercise b4;
                b4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b4.setMuscleGroup(abs);
                b4.setName("UNOSZENIE NÓG W ZWISIE NA DRĄŻKU");

                Exercise b5;
                b5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b5.setMuscleGroup(abs);
                b5.setName("SKŁONY TUŁOWIA Z LINKĄ WYCIĄGU SIEDZĄC");

                Exercise b6;
                b6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b6.setMuscleGroup(abs);
                b6.setName("SKRĘTY TUŁOWIA");

                Exercise b7;
                b7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b7.setMuscleGroup(abs);
                b7.setName("SPIĘCIA BRZUCHA NA WYCIĄGU");

                Exercise b8;
                b8 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                b8.setMuscleGroup(abs);
                b8.setName("SKŁONY BOCZNE NA ŁAWCE");

                /* Plecy */

                Exercise p1;
                p1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p1.setMuscleGroup(back);
                p1.setName("PODCIĄGANIE NA DRĄŻKU SZEROKIM UCHWYTEM (NACHWYT)");

                Exercise p2;
                p2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p2.setMuscleGroup(back);
                p2.setName("PODCIĄGANIE NA DRĄŻKU PODCHWYTEM");

                Exercise p3;
                p3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p3.setMuscleGroup(back);
                p3.setName("PODCIĄGANIE SZTANGI W OPADZIE(WIOSŁOWANIE)");

                Exercise p4;
                p4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p4.setMuscleGroup(back);
                p4.setName("PODCIĄGANIE SZTANGIELKI W OPADZIE(WIOSŁOWANIE)");

                Exercise p5;
                p5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p5.setMuscleGroup(back);
                p5.setName("PODCIĄGANIE KOŃCA SZTANGI W OPADZIE");

                Exercise p6;
                p6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p6.setMuscleGroup(back);
                p6.setName("PRZYCIĄGANIE LINKI WYCIĄGU DOLNEGO W SIADZIE PŁASKIM");

                Exercise p7;
                p7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p7.setMuscleGroup(back);
                p7.setName("MARTWY CIĄG");

                Exercise p8;
                p8 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                p8.setMuscleGroup(back);
                p8.setName("PODCIĄGANIE (WIOSŁOWANIE) W LEŻENIU NA ŁAWECZCE POZIOMEJ");

                /* Biceps */

                Exercise bic1;
                bic1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic1.setMuscleGroup(biceps);
                bic1.setName("UGINANIE RAMION ZE SZTANGĄ STOJAC PODCHWYTEM");

                Exercise bic2;
                bic2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic2.setMuscleGroup(biceps);
                bic2.setName("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC PODCHWYTEM");

                Exercise bic3;
                bic3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic3.setMuscleGroup(biceps);
                bic3.setName("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC (UCHWYT „MŁOTKOWY”)");

                Exercise bic4;
                bic4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic4.setMuscleGroup(biceps);
                bic4.setName("UGINANIE RAMION ZE SZTANGĄ NA „MODLITEWNIKU”");

                Exercise bic5;
                bic5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic5.setMuscleGroup(biceps);
                bic5.setName("UGINANIE RAMIENIA ZE SZTANGIELKĄ NA „MODLITEWNIKU”");

                Exercise bic6;
                bic6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic6.setMuscleGroup(biceps);
                bic6.setName("UGINANIE RAMION ZE SZTANGIELKAMI W SIADZIE NA ŁAWCE SKOŚNEJ(Z SUPINACJĄ NADGARSTKA)");

                Exercise bic7;
                bic7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bic7.setMuscleGroup(biceps);
                bic7.setName("UGINANIE RAMION PODCHWYTEM STOJĄC-Z RĄCZKĄ WYCIĄGU");

                /* Triceps */

                Exercise t1;
                t1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t1.setMuscleGroup(triceps);
                t1.setName("PROSTOWANIE RAMION NA WYCIĄGU STOJĄC");

                Exercise t2;
                t2 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t2.setMuscleGroup(triceps);
                t2.setName("WYCISKANIE „FRANCUSKIE” SZTANGI W SIADZIE");

                Exercise t3;
                t3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t3.setMuscleGroup(triceps);
                t3.setName("WYCISKANIE “FRANCUSKIE” JEDNORĄCZ SZTANGIELKI W SIADZIE");

                Exercise t4;
                t4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t4.setMuscleGroup(triceps);
                t4.setName("WYCISKANIE „FRANCUSKIE” SZTANGI W LEŻENIU");

                Exercise t5;
                t5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t5.setMuscleGroup(triceps);
                t5.setName("WYCISKANIE „FRANCUSKIE”SZTANGIELKI W LEŻENIU");

                Exercise t6;
                t6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t6.setMuscleGroup(triceps);
                t6.setName("PROSTOWNIE RAMIENIA ZE SZTANGIELKĄ W OPADZIE TUŁOWIA");

                Exercise t7;
                t7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t7.setMuscleGroup(triceps);
                t7.setName("PROSTOWANIE RAMION NA WYCIĄGU W PŁASZCZYŹNE POZIOMEJ STOJĄC");

                Exercise t8;
                t8 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t8.setMuscleGroup(triceps);
                t8.setName("POMPKI NA PORĘCZACH");

                Exercise t9;
                t9 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                t9.setMuscleGroup(triceps);
                t9.setName("WYCISKANIE W LEŻENIU NA ŁAWCE POZIOMEJ WĄSKIM UCHWYTEM");

                /* Barki */
                Exercise bar1;
                bar1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar1.setMuscleGroup(shoulders);
                bar1.setName("WYCISKANIE SZTANGI SPRZED GŁOWY");

                Exercise bar2;
                bar1 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar1.setMuscleGroup(shoulders);
                bar1.setName("WYCISKANIE SZTANGI ZZA GŁOWY");

                Exercise bar3;
                bar3 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar3.setMuscleGroup(shoulders);
                bar3.setName("WYCISKANIE SZTANGIELEK");

                Exercise bar4;
                bar4 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar4.setMuscleGroup(shoulders);
                bar4.setName("ARNOLDKI");

                Exercise bar5;
                bar5 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar5.setMuscleGroup(shoulders);
                bar5.setName("UNOSZENIE SZTANGIELEK BOKIEM W GÓRĘ");

                Exercise bar6;
                bar6 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar6.setMuscleGroup(shoulders);
                bar6.setName("UNOSZENIE SZTANGIELEK W OPADZIE TUŁOWIA");

                Exercise bar7;
                bar7 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar7.setMuscleGroup(shoulders);
                bar7.setName("PODCIĄGANIE SZTANGI WZDŁUŻ TUŁOWIA");

                Exercise bar8;
                bar8 = myRealm.createObject(Exercise.class, UUID.randomUUID().toString());
                bar8.setMuscleGroup(shoulders);
                bar8.setName("UNOSZENIE RAMION W PRZÓD ZE SZTANGIELKAMI");

                /*Treningi*/
                TrainingPlan fbw;
                fbw = myRealm.createObject(TrainingPlan.class, UUID.randomUUID().toString());
                fbw.setName("Trening FBW");
                fbw.setDefaultPlan(true);

                Series series1 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series1.setExercise(n1);
                series1.setNumberOfRepetitions(12);

                Series series2 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series2.setExercise(n1);
                series2.setNumberOfRepetitions(10);

                Series series3 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series3.setExercise(n1);
                series3.setNumberOfRepetitions(8);

                Series series4 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series4.setExercise(p7);
                series4.setNumberOfRepetitions(12);

                Series series5 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series5.setExercise(p7);
                series5.setNumberOfRepetitions(10);

                Series series6 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series6.setExercise(p7);
                series6.setNumberOfRepetitions(8);

                Series series7 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series7.setExercise(c1);
                series7.setNumberOfRepetitions(12);

                Series series8 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series8.setExercise(c1);
                series8.setNumberOfRepetitions(10);

                Series series9 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series9.setExercise(c1);
                series9.setNumberOfRepetitions(8);

                Series series10 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series10.setExercise(bar4);
                series10.setNumberOfRepetitions(12);

                Series series11 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series11.setExercise(bar4);
                series11.setNumberOfRepetitions(10);

                Series series12 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series12.setExercise(bar4);
                series12.setNumberOfRepetitions(8);

                Series series13 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series13.setExercise(bic2);
                series13.setNumberOfRepetitions(12);

                Series series14 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series14.setExercise(bic2);
                series14.setNumberOfRepetitions(10);

                Series series15 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series15.setExercise(bic2);
                series15.setNumberOfRepetitions(8);

                Series series16 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series16.setExercise(t2);
                series16.setNumberOfRepetitions(12);

                Series series17 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series17.setExercise(t2);
                series17.setNumberOfRepetitions(10);

                Series series18 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series18.setExercise(t2);
                series18.setNumberOfRepetitions(8);

                Series series19 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series19.setExercise(b3);
                series19.setNumberOfRepetitions(12);

                Series series20 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series20.setExercise(b3);
                series20.setNumberOfRepetitions(10);

                Series series21 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series21.setExercise(b3);
                series21.setNumberOfRepetitions(8);

                fbw.getSeries().add(series1);
                fbw.getSeries().add(series2);
                fbw.getSeries().add(series3);
                fbw.getSeries().add(series4);
                fbw.getSeries().add(series5);
                fbw.getSeries().add(series6);
                fbw.getSeries().add(series7);
                fbw.getSeries().add(series8);
                fbw.getSeries().add(series9);
                fbw.getSeries().add(series10);
                fbw.getSeries().add(series11);
                fbw.getSeries().add(series12);
                fbw.getSeries().add(series13);
                fbw.getSeries().add(series14);
                fbw.getSeries().add(series15);
                fbw.getSeries().add(series16);
                fbw.getSeries().add(series17);
                fbw.getSeries().add(series18);
                fbw.getSeries().add(series19);
                fbw.getSeries().add(series20);
                fbw.getSeries().add(series21);

                TrainingPlan split1;
                split1 = myRealm.createObject(TrainingPlan.class, UUID.randomUUID().toString());
                split1.setName("Trening split - Klatka + Barki");
                split1.setDefaultPlan(true);

                Series series22 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series22.setExercise(c3);
                series22.setNumberOfRepetitions(10);

                Series series23 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series23.setExercise(c3);
                series23.setNumberOfRepetitions(10);

                Series series24 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series24.setExercise(c3);
                series24.setNumberOfRepetitions(10);

                Series series25 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series25.setExercise(c4);
                series25.setNumberOfRepetitions(10);

                Series series26 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series26.setExercise(c4);
                series26.setNumberOfRepetitions(10);

                Series series27 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series27.setExercise(c4);
                series27.setNumberOfRepetitions(10);

                Series series28 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series28.setExercise(c6);
                series28.setNumberOfRepetitions(12);

                Series series29 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series29.setExercise(c6);
                series29.setNumberOfRepetitions(12);

                Series series30 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series30.setExercise(c6);
                series30.setNumberOfRepetitions(12);

                Series series31 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series31.setExercise(c8);
                series31.setNumberOfRepetitions(15);

                Series series32 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series32.setExercise(c8);
                series32.setNumberOfRepetitions(15);

                Series series33 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series33.setExercise(c8);
                series33.setNumberOfRepetitions(15);

                Series series34 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series34.setExercise(bar1);
                series34.setNumberOfRepetitions(12);

                Series series35 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series35.setExercise(bar1);
                series35.setNumberOfRepetitions(12);

                Series series36 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series36.setExercise(bar1);
                series36.setNumberOfRepetitions(12);

                Series series37 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series37.setExercise(bar3);
                series37.setNumberOfRepetitions(15);

                Series series38 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series38.setExercise(bar3);
                series38.setNumberOfRepetitions(15);

                Series series39 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series39.setExercise(bar3);
                series39.setNumberOfRepetitions(15);

                Series series40 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series40.setExercise(bar5);
                series40.setNumberOfRepetitions(15);

                Series series41 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series41.setExercise(bar5);
                series41.setNumberOfRepetitions(15);

                Series series42 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series42.setExercise(bar5);
                series42.setNumberOfRepetitions(15);

                split1.getSeries().add(series22);
                split1.getSeries().add(series23);
                split1.getSeries().add(series24);
                split1.getSeries().add(series25);
                split1.getSeries().add(series26);
                split1.getSeries().add(series27);
                split1.getSeries().add(series28);
                split1.getSeries().add(series29);
                split1.getSeries().add(series30);
                split1.getSeries().add(series31);
                split1.getSeries().add(series32);
                split1.getSeries().add(series33);
                split1.getSeries().add(series34);
                split1.getSeries().add(series35);
                split1.getSeries().add(series36);
                split1.getSeries().add(series37);
                split1.getSeries().add(series38);
                split1.getSeries().add(series39);
                split1.getSeries().add(series40);
                split1.getSeries().add(series41);
                split1.getSeries().add(series42);


                TrainingPlan split2;
                split2 = myRealm.createObject(TrainingPlan.class, UUID.randomUUID().toString());
                split2.setName("Trening split - Nogi + Triceps");
                split2.setDefaultPlan(true);

                Series series43 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series43.setExercise(n1);
                series43.setNumberOfRepetitions(12);

                Series series44 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series44.setExercise(n1);
                series44.setNumberOfRepetitions(12);

                Series series45 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series45.setExercise(n1);
                series45.setNumberOfRepetitions(12);

                Series series46 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series46.setExercise(n2);
                series46.setNumberOfRepetitions(15);

                Series series47 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series47.setExercise(n2);
                series47.setNumberOfRepetitions(15);

                Series series48 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series48.setExercise(n2);
                series48.setNumberOfRepetitions(15);

                Series series49 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series49.setExercise(n5);
                series49.setNumberOfRepetitions(25);

                Series series50 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series50.setExercise(n5);
                series50.setNumberOfRepetitions(25);

                Series series51 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series51.setExercise(n5);
                series51.setNumberOfRepetitions(25);

                Series series52 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series52.setExercise(n6);
                series52.setNumberOfRepetitions(12);

                Series series53 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series53.setExercise(n6);
                series53.setNumberOfRepetitions(12);

                Series series54 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series54.setExercise(n6);
                series54.setNumberOfRepetitions(12);

                Series series55 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series55.setExercise(t4);
                series55.setNumberOfRepetitions(12);

                Series series56 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series56.setExercise(t4);
                series56.setNumberOfRepetitions(12);

                Series series57 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series57.setExercise(t4);
                series57.setNumberOfRepetitions(12);

                Series series58 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series58.setExercise(t8);
                series58.setNumberOfRepetitions(12);

                Series series59 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series59.setExercise(t8);
                series59.setNumberOfRepetitions(12);

                Series series60 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series60.setExercise(t8);
                series60.setNumberOfRepetitions(12);

                Series series61 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series61.setExercise(t7);
                series61.setNumberOfRepetitions(15);

                Series series62 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series62.setExercise(t7);
                series62.setNumberOfRepetitions(15);

                Series series63 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series63.setExercise(t7);
                series63.setNumberOfRepetitions(15);

                split2.getSeries().add(series43);
                split2.getSeries().add(series44);
                split2.getSeries().add(series45);
                split2.getSeries().add(series46);
                split2.getSeries().add(series47);
                split2.getSeries().add(series48);
                split2.getSeries().add(series49);
                split2.getSeries().add(series50);
                split2.getSeries().add(series51);
                split2.getSeries().add(series52);
                split2.getSeries().add(series53);
                split2.getSeries().add(series54);
                split2.getSeries().add(series55);
                split2.getSeries().add(series56);
                split2.getSeries().add(series57);
                split2.getSeries().add(series58);
                split2.getSeries().add(series59);
                split2.getSeries().add(series60);
                split2.getSeries().add(series61);
                split2.getSeries().add(series62);
                split2.getSeries().add(series63);

                TrainingPlan split3;
                split3 = myRealm.createObject(TrainingPlan.class, UUID.randomUUID().toString());
                split3.setName("Trening split - Plecy + Biceps");
                split3.setDefaultPlan(true);

                Series series64 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series64.setExercise(p2);
                series64.setNumberOfRepetitions(10);

                Series series65 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series65.setExercise(p2);
                series65.setNumberOfRepetitions(10);

                Series series66 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series66.setExercise(p2);
                series66.setNumberOfRepetitions(10);

                Series series67 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series67.setExercise(p7);
                series67.setNumberOfRepetitions(12);

                Series series68 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series68.setExercise(p7);
                series68.setNumberOfRepetitions(12);

                Series series69 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series69.setExercise(p7);
                series69.setNumberOfRepetitions(12);

                Series series70 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series70.setExercise(p8);
                series70.setNumberOfRepetitions(12);

                Series series71 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series71.setExercise(p8);
                series71.setNumberOfRepetitions(12);

                Series series72 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series72.setExercise(p6);
                series72.setNumberOfRepetitions(12);

                Series series73 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series73.setExercise(p8);
                series73.setNumberOfRepetitions(15);

                Series series74 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series74.setExercise(p8);
                series74.setNumberOfRepetitions(15);

                Series series75 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series75.setExercise(p8);
                series75.setNumberOfRepetitions(15);

                Series series76 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series76.setExercise(bic1);
                series76.setNumberOfRepetitions(12);

                Series series77 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series77.setExercise(bic1);
                series77.setNumberOfRepetitions(12);

                Series series78 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series78.setExercise(bic1);
                series78.setNumberOfRepetitions(12);

                Series series79 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series79.setExercise(bic2);
                series79.setNumberOfRepetitions(12);

                Series series80 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series80.setExercise(bic2);
                series80.setNumberOfRepetitions(12);

                Series series81 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series81.setExercise(bic2);
                series81.setNumberOfRepetitions(12);

                Series series82 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series82.setExercise(bic3);
                series82.setNumberOfRepetitions(10);

                Series series83 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series83.setExercise(bic3);
                series83.setNumberOfRepetitions(10);

                Series series84 = myRealm.createObject(Series.class,UUID.randomUUID().toString());
                series84.setExercise(bic3);
                series84.setNumberOfRepetitions(10);

                split3.getSeries().add(series64);
                split3.getSeries().add(series65);
                split3.getSeries().add(series66);
                split3.getSeries().add(series67);
                split3.getSeries().add(series68);
                split3.getSeries().add(series69);
                split3.getSeries().add(series70);
                split3.getSeries().add(series71);
                split3.getSeries().add(series72);
                split3.getSeries().add(series73);
                split3.getSeries().add(series74);
                split3.getSeries().add(series75);
                split3.getSeries().add(series76);
                split3.getSeries().add(series77);
                split3.getSeries().add(series78);
                split3.getSeries().add(series79);
                split3.getSeries().add(series80);
                split3.getSeries().add(series81);
                split3.getSeries().add(series82);
                split3.getSeries().add(series83);
                split3.getSeries().add(series84);

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

    public List<TrainingPlan> getAllDefaultPlans(){
        RealmResults<TrainingPlan> plans = myRealm.where(TrainingPlan.class)
                .findAll();
        return new ArrayList<>(plans);
    }

    public boolean checkIfTrainingExist(String s) {
        RealmResults<TrainingPlan> plans = myRealm.where(TrainingPlan.class)
                .equalTo("name",s).findAll();

        return !plans.isEmpty();

    }

    public TrainingPlan getPlanByName(String name) {
        TrainingPlan plan = myRealm.where(TrainingPlan.class)
                .equalTo("name",name).findFirst();

        return plan;
    }

    public TrainingPlan getPlanByUUID(String uuid) {
        TrainingPlan plan = myRealm.where(TrainingPlan.class)
                .equalTo("id",uuid).findFirst();

        return plan;
    }

    public void setPlanName(final String oldName, final String newName){
        final TrainingPlan plan = myRealm.where(TrainingPlan.class)
                .equalTo("name",oldName).findFirst();

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                plan.setName(newName);
            }
        });
    }

    public void createNewPlan(final String name){
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TrainingPlan plan = myRealm.createObject(TrainingPlan.class, UUID.randomUUID().toString());
                plan.setName(name);
                myRealm.copyToRealmOrUpdate(plan);
            }
        });
    }

    public List<String> getAllMuscleGroupAsStringList() {
        List<String> names = new ArrayList<>();
        RealmResults<MuscleGroup> groups = myRealm.where(MuscleGroup.class)
                .findAll();
        for(MuscleGroup group : groups)
            names.add(group.getName());

        return names;
    }

    public List<String> getExercisesFromCategoryAsStringList(String name) {
        List<String> names = new ArrayList<>();
        RealmResults<Exercise> exercises = myRealm.where(Exercise.class)
                .equalTo("muscleGroup.name", name)
                .findAll();
        for(Exercise exercise : exercises)
            names.add(exercise.getName());

        return names;
    }
}
