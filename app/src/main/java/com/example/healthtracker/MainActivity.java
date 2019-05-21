package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        TextView numCounter;
        Button countUp;

        int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numCounter = findViewById(R.id.incrementor);
        countUp = findViewById(R.id.countUp);

        numCounter.setText("0");

        countUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter + 1;
                numCounter.setText(String.valueOf(counter));
            }
        });

    }

}
