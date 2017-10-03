package com.school.jakub.trainingplanmaker.data;

import android.provider.BaseColumns;

/**
 * Created by Jakub on 03-Oct-17.
 */

public final class DatabaseContract {

    public static final class TrainingChecklist implements BaseColumns {
        public static final String TABLE_NAME = "TrainingChecklist";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "Name";
    }

    public static final class ChecklistDetails implements BaseColumns {
        public static final String TABLE_NAME = "ChecklistDetails";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ITEM_ID = "ItemId";
        public static final String COLUMN_CHECKLIST_ID= "ChecklistId";
    }

    public static final class ItemForTraining implements BaseColumns {
        public static final String TABLE_NAME = "ItemForTraining";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "ItemForTraining";
    }
}
