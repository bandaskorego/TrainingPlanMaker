package com.school.jakub.trainingplanmaker.dependencyInjection;

/**
 * Created by Jakub on 19-Nov-17.
 */
import android.content.Context;


import com.school.jakub.trainingplanmaker.App;
import com.school.jakub.trainingplanmaker.services.BackpackService;
import com.school.jakub.trainingplanmaker.services.DiaryService;
import com.school.jakub.trainingplanmaker.services.MeasurementService;
import com.school.jakub.trainingplanmaker.services.TrainingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }


    @Provides
    BackpackService provideBackpackService() {
        return new BackpackService();
    }

    @Provides
    MeasurementService provideMeasurementService() {
        return new MeasurementService();
    }
    @Provides
    TrainingService provideTrainingService() {
        return new TrainingService();
    }

    @Provides
    DiaryService provideDiaryService() {
        return new DiaryService();
    }

}