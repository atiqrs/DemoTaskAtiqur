package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewAllData extends AppCompatActivity {

    CustomAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        //infoAdapter = new CustomAdapter(this,name,mobile,email);
        //listView.setAdapter(infoAdapter);
    }
}