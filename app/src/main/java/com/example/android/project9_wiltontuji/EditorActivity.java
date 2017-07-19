package com.example.android.project9_wiltontuji;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.project9_wiltontuji.data.ExercisesContract;
import com.example.android.project9_wiltontuji.data.ExercisesDbHelper;
import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry;
import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry.COLUMN_EXERCISE;

public class EditorActivity extends AppCompatActivity {

    private EditText mExercisesEditText;
    private EditText mQuantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mExercisesEditText = (EditText) findViewById(R.id.exercises_edit_text);
        mQuantityEditText = (EditText) findViewById(R.id.quantity_edit_text);
        Button mSaveExerciseButton = (Button) findViewById(R.id.save_exercise_button);

        mSaveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditorActivity.this, CatalogActivity.class);
                insertExercise();
                startActivity(intent);
            }
        });
    }

    private void insertExercise(){
        String mExercise = mExercisesEditText.getText().toString().trim();
        int mQuantity = Integer.parseInt(mQuantityEditText.getText().toString().trim());

        ExercisesDbHelper mDbHelper = new ExercisesDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExercisesEntry.COLUMN_EXERCISE, mExercise);
        values.put(ExercisesEntry.COLUMN_QUANTITY, mQuantity);
        long newRowId = db.insert(ExercisesEntry.TABLE_NAME, null, values);

        if(newRowId != -1){
            Toast.makeText(this, "Error while adding the exercise.",Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Exercises add to ID: " + newRowId, Toast.LENGTH_LONG);
        }
    }

}
