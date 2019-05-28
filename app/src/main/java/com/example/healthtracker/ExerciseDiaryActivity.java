package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthtracker.Database.AppDatabase;
import com.example.healthtracker.Database.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDiaryActivity extends AppCompatActivity {

    public AppDatabase db;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ExerciseLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary_activiy);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "exercise-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Exercise> exercises = db.exerciseDao().getAll();

        recyclerView = findViewById(R.id.recycle_exercise);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExerciseLayoutAdapter(exercises);
        recyclerView.setAdapter(adapter);

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

    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
