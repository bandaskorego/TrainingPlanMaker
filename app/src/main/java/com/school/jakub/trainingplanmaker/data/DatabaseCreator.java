package com.school.jakub.trainingplanmaker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.school.jakub.trainingplanmaker.data.DatabaseContract.*;

/**
 * Created by Jakub on 03-Oct-17.
 */

public class DatabaseCreator extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "trainingPlanMaker.db";
    private static final int DATABASE_VERSION = 1;
    private List<String> tablesConstructors;



    public DatabaseCreator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createAllTables(){
        prepareTablesConstructors();
        //tablesConstructors.forEach(table -> db.execSQL(table));
    }

    private void prepareTablesConstructors() {
        tablesConstructors = new ArrayList<>();
        tablesConstructors.add("CREATE TABLE " + TrainingChecklist.TABLE_NAME + " (" +
                TrainingChecklist._ID + " INTEGER PRIMARY KEY, " +
                TrainingChecklist.COLUMN_NAME + " TEXT, " +
                ")");

        tablesConstructors.add("CREATE TABLE " + ItemForTraining.TABLE_NAME + " (" +
                ItemForTraining._ID + " INTEGER PRIMARY KEY, " +
                ItemForTraining.COLUMN_NAME + " TEXT, " +
                ")");

        tablesConstructors.add("CREATE TABLE " + ChecklistDetails.TABLE_NAME + " (" +
                ChecklistDetails._ID + " INTEGER PRIMARY KEY, " +
                ChecklistDetails.COLUMN_ITEM_ID + " TEXT, " +
                ChecklistDetails.COLUMN_CHECKLIST_ID + " TEXT, " +
                ")");
    }


}
