package com.example.healthtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthtracker.Database.Exercise;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseLayoutAdapter extends RecyclerView.Adapter<ExerciseLayoutAdapter.ExerciseHolder> {


    public static class ExerciseHolder extends RecyclerView.ViewHolder {

        public TextView textTitle;
        public TextView textQuantity;
        public TextView textDescription;
        public TextView textTime;

        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);

            this.textTitle = itemView.findViewById(R.id.text_title);
            this.textQuantity = itemView.findViewById(R.id.text_quantity);
            this.textDescription = itemView.findViewById(R.id.text_description);
            this.textTime = itemView.findViewById(R.id.text_time);
        }

        public void setExercise(Exercise exercise) {
            this.textTitle.setText(exercise.getTitle());
            this.textQuantity.setText(exercise.getQuantity());
            this.textDescription.setText(exercise.getDescription());
            this.textTime.setText(exercise.getTimeStamp());
        }


    }

    private List<Exercise> exercises;

    public ExerciseLayoutAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void removeExercise(int index) {
        this.notifyItemRemoved(index);
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.exercise_view, parent, false);

        ExerciseHolder holder = new ExerciseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.setExercise(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


}
