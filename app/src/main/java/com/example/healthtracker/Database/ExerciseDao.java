package com.example.healthtracker.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM Exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM Exercise WHERE title = :title")
    Exercise findByTitle(String title);

    @Insert
    void add(Exercise exercise);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

}
