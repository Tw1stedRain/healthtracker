package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UsernameActivity extends AppCompatActivity {

    TextView text;
    EditText edit;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        text = findViewById(R.id.text_name);
        edit = findViewById(R.id.editText);

        SharedPreferences preferences = PreferencesHelper.getMyFilePreferences(this);

        name = preferences.getString(MainActivity.NAME_KEY, "New User!");

        text.setText(name);
        edit.setText(name);
    }

    public void onNameEditClick(View view) {
        this.name = edit.getText().toString();
        text.setText(this.name);
    }
}
