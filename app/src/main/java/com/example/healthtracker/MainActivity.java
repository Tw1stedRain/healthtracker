package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        int[] pictures = {
                R.drawable.ty,
                R.drawable.fred,
                R.drawable.cooper
        };

        String[] quotes = {
                "You can do it?",
                "I believe in you!",
                "Work that phone!"
        };

        int currentPic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        ImageView image = findViewById(R.id.slider);
        image.setImageResource(pictures[currentPic]);

        TextView quote = findViewById(R.id.quotes);
        quote.setText(quotes[currentPic]);


        TextView text = findViewById(R.id.tracker);
        text.setText(String.valueOf(currentPic+1 + "/3"));
            currentPic++;
        if (currentPic == pictures.length) {
            currentPic = 0;
        }
    }

    public void fingerExercise(View view) {
        Intent intent = new Intent(this, FingerActivity.class);
        startActivity(intent);
    }

    public void diary(View view) {
        Intent intent = new Intent(this, ExerciseDiaryActivity.class);
        startActivity(intent);
    }


}
