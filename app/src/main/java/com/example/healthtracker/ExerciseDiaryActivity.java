package com.example.healthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthtracker.Database.AppDatabase;
import com.example.healthtracker.Database.Exercise;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class ExerciseDiaryActivity extends AppCompatActivity {

    public AppDatabase db;

    TextView text;

    TextView locationText;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ExerciseLayoutAdapter adapter;

    Retrofit retrofit;
    DiaryService service;

    FusedLocationProviderClient client;
    public final static int MY_LOCATION_REQUEST = 345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary_activiy);

        this.text = findViewById(R.id.rest_view);
        this.locationText = findViewById(R.id.location_text);

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

        String baseUrl = "https://healthtracker-backend-spring.herokuapp.com/";
        service = retrofit.create(DiaryService.class);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = LocationServices.getFusedLocationProviderClient(this);

    }

    public void onclick(View view) {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                showLocationRequestDialog();
            } else {
                requestLocationPermission();
            }
            return;
        }
        client.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            Log.e("Diary Activity", "Location was null");
                        } else {
                            locationText.setText(location.getLatitude() + " " + location.getLongitude());
                        }
                    }
                });
    }


    public void requestLocationPermission() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_LOCATION_REQUEST
        );
    }


    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case MY_LOCATION_REQUEST: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("ExerciseDiaryActivity", "I KNOW WHERE YOU ARE NOW! MUAHAHAHA");
                } else {
                    Log.i("ExerciseDiaryActivity", "sad :(");
                }
                return;
            }
        }
    }

    public void showLocationRequestDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Let me know where you are!");
        builder.setMessage("Pleeeeeaaaaeeeee let me know where you are");

        builder.setPositiveButton("Fine", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestLocationPermission();
            }
        });

        builder.setNegativeButton("no.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
        exercise.setTimeStamp(time.getText().toString());

        service.createExercise(exercise).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                text.setText(String.format("%d", response.code()));

                // TODO : change to return exercise, right now, just returns response code
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

//        db.exerciseDao().add(exercise);
    }

    public void onRetrofit(View view) {

        service.getAllExercises().enqueue(new Callback<List<Exercise>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                text.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.e("ERROR!!!", t.toString());
            }
        });
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
