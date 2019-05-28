package com.example.healthtracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static SharedPreferences getMyFilePreferences(Activity activity) {
        String filename = activity.getString(R.string.my_main_pref_file);
        SharedPreferences preferences = activity.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences;
    }
}
