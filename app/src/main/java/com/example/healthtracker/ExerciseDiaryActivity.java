package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthtracker.Database.AppDatabase;
import com.example.healthtracker.Database.Exercise;

public class ExerciseDiaryActivity extends AppCompatActivity {

    public AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary_activiy);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "exercise-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Exercise tap = new Exercise("Finger Tap", "52", "Tap as fast as you can!", "today");
        db.exerciseDao().add(tap);

        Exercise found = db.exerciseDao().findByTitle("Finger Tap");

        TextView list = findViewById(R.id.list1);
        list.setText(found.toString());

    }

    public void newExercise(View view) {
        // add to db
        EditText title = findViewById(R.id.title);
        EditText quantity = findViewById(R.id.quantity);
        EditText description = findViewById(R.id.description);
        EditText time = findViewById(R.id.time);


        Exercise exercise = new Exercise();
        exercise.setTitle(title.getText().toString());
        exercise.setQuantity(quantity.getText().toString()); // TODO: change to int
        exercise.setDescription(description.getText().toString());
        exercise.setTimestamp(time.getText().toString());

        db.exerciseDao().add(exercise);

        Exercise found2 = db.exerciseDao().findByTitle(title.getText().toString());

        TextView list2 = findViewById(R.id.list2);
        list2.setText(found2.toString());

    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
