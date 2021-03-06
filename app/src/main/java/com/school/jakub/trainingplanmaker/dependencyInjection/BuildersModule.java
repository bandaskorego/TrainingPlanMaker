package com.school.jakub.trainingplanmaker.dependencyInjection;


import com.school.jakub.trainingplanmaker.controller.atlas.AtlasActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackChecklistActivity;
import com.school.jakub.trainingplanmaker.controller.bagpack.BackpackEditActivity;
import com.school.jakub.trainingplanmaker.controller.diary.AddExerciseToDayEntry;
import com.school.jakub.trainingplanmaker.controller.diary.DiaryActivity;
import com.school.jakub.trainingplanmaker.controller.exerciseMonitor.ExerciseMonitor;
import com.school.jakub.trainingplanmaker.controller.main.MainActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ManageMeasurementsActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.MeasurementMonitorActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ProfileMeasurementActivity;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlanEditActivity;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlanSelect;
import com.school.jakub.trainingplanmaker.controller.trainingPlan.TrainingPlans;
import com.school.jakub.trainingplanmaker.model.Diary;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by Jakub on 19-Nov-17.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract BackpackActivity bindBackpackActivity();

    @ContributesAndroidInjector
    abstract BackpackEditActivity bindBackpackEditActivity();

    @ContributesAndroidInjector
    abstract BackpackChecklistActivity bindBackpackChecklistActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract ProfileMeasurementActivity bindProfileMeasurementActivity();

    @ContributesAndroidInjector
    abstract MeasurementMonitorActivity bindMeasurementMonitorActivity();

    @ContributesAndroidInjector
    abstract ManageMeasurementsActivity bindManageMeasurementsActivity();

    @ContributesAndroidInjector
    abstract ExerciseMonitor bindExerciseMonitor();

    @ContributesAndroidInjector
    abstract DiaryActivity bindDiaryActivity();

    @ContributesAndroidInjector
    abstract AddExerciseToDayEntry bindAddExerciseToDayEntry();

    @ContributesAndroidInjector
    abstract AtlasActivity bindAtlasActivity();

    @ContributesAndroidInjector
    abstract TrainingPlans bindTrainingPlans();

    @ContributesAndroidInjector
    abstract TrainingPlanSelect bindTrainingPlanSelect();

    @ContributesAndroidInjector
    abstract TrainingPlanEditActivity bindTrainingPlanEditActivity();

}