package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        private TextView text;

        private String name;

        public final static String NAME_KEY = "user-name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text_name);

        String filename = getString(R.string.my_main_pref_file);
        SharedPreferences preferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        name = preferences.getString(NAME_KEY, "New User!");

        updateText();
    }

    public void usernameClick(View view) {

        SharedPreferences preferences = PreferencesHelper.getMyFilePreferences(this);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.apply();
        updateText();

    }

    private void updateText() {
        text.setText(this.name);
    }


    // buttons
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

    // navigation buttons
    public void fingerExercise(View view) {
        Intent intent = new Intent(this, FingerActivity.class);
        startActivity(intent);
    }

    public void diary(View view) {
        Intent intent = new Intent(this, ExerciseDiaryActivity.class);
        startActivity(intent);
    }


}
