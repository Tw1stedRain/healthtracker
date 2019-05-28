package com.example.healthtracker.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
}

