package com.example.healthtracker;

import com.example.healthtracker.Database.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DiaryService {
    @GET("/exercise/all")
    Call<List<Exercise>> getAllExercises();

    @POST("/exercise/")
    Call<Void> createExercise(@Body Exercise exercise);
}
