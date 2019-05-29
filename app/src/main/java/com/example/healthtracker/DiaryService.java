package com.example.healthtracker;

import com.example.healthtracker.Database.Exercise;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DiaryService {
    @GET("/all")
    Call<Exercise> getAllExercises();

    @POST("/")
    Call<Exercise> createExercise(@Body Exercise exercise);
}
