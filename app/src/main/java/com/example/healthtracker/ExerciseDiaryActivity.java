package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthtracker.Database.AppDatabase;
import com.example.healthtracker.Database.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDiaryActivity extends AppCompatActivity {

    public AppDatabase db;

    TextView text;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ExerciseLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary_activiy);

        this.text = findViewById(R.id.rest_view);

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
        exercise.setQuantity(quantity.getText().toString());
        exercise.setDescription(description.getText().toString());
        exercise.setTimestamp(time.getText().toString());

        db.exerciseDao().add(exercise);
    }

    public void onRetrofit(View view) {
        String baseUrl = "localhost:8080/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiaryService service = retrofit.create(DiaryService.class);

        service.getAllExercises().enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                text.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                Log.e("ERROR!!!", t.toString());
            }
        });

        service.createExercise(
                
        )
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
