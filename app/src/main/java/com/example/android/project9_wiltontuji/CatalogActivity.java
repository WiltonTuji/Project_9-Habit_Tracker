package com.example.android.project9_wiltontuji;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.project9_wiltontuji.data.ExercisesContract;
import com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry;
import com.example.android.project9_wiltontuji.data.ExercisesDbHelper;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Button launchEditExerciseActivity = (Button) findViewById(R.id.add_exercise_button);
        launchEditExerciseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo(){

        ExercisesDbHelper mDbHelper = new ExercisesDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {ExercisesEntry._ID, ExercisesEntry.COLUMN_EXERCISE, ExercisesEntry.COLUMN_QUANTITY };

        Cursor cursor = db.query(ExercisesEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.catalog_text_view);

        try{

            displayView.setText("The exercises table contains " + cursor.getCount() + " exercises.\n\n");
            displayView.append(ExercisesEntry._ID + " - " + ExercisesEntry.COLUMN_EXERCISE + " - " + ExercisesEntry.COLUMN_QUANTITY + "\n");

            int idColumnIndex = cursor.getColumnIndex(ExercisesEntry._ID);
            int exerciseColumnIndex = cursor.getColumnIndex(ExercisesEntry.COLUMN_EXERCISE);
            int quantityColumnIndex = cursor.getColumnIndex(ExercisesEntry.COLUMN_QUANTITY);

            while (cursor.moveToNext()){

                int currentID = cursor.getInt(idColumnIndex);
                String currentExercise = cursor.getString(exerciseColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);

                displayView.append("\n" + currentID + " - " + currentExercise + " - " + currentQuantity);
            }

        } finally {
            cursor.close();
        }
    }

}
