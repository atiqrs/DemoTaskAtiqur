package com.atiqrs.demotaskatiqur.Database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String SHARED_PREF_DATA = "defaultLoginData";
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Context context;

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(SHARED_PREF_DATA, Activity.MODE_PRIVATE);
         editor = sharedPreferences.edit();
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void setBooleanValue(String key, boolean isBool) {
        editor.putBoolean(key, isBool);
        editor.apply();
    }

    public void setBooleanValueFalse(String key) {
        editor.putBoolean(key, false);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
