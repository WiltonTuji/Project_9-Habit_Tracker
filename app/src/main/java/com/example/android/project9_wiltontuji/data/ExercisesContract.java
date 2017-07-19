package com.example.android.project9_wiltontuji.data;

import android.provider.BaseColumns;

/**
 * Created by Adailto on 26/05/2017.
 */

public class ExercisesContract {

    public static abstract class ExercisesEntry implements BaseColumns{

        public static final String TABLE_NAME = "exercises";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EXERCISE = "exercise";
        public static final String COLUMN_QUANTITY = "quantity";


    }

}
