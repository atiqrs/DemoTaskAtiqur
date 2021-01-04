package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atiqrs.demotaskatiqur.Database.DatabaseHelper;
import com.atiqrs.demotaskatiqur.Database.model.Information;

import java.util.ArrayList;
import java.util.List;

public class ViewAllData extends AppCompatActivity {

    TextView noInfoView;
    ListView allInfoView;
    List<Information> informations = new ArrayList<>();
    private DatabaseHelper db;
    Intent intent;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        setTitle("Database all info");
        allInfoView = findViewById(R.id.allInfoView);
        //noInfoView = findViewById(R.id.noInfoView);

        db = new DatabaseHelper(this);
        informations.addAll(db.getAllInfo());

        adapter = new CustomAdapter(this,informations);
        allInfoView.setAdapter(adapter);
        allInfoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("hello", "111");
                intent = new Intent(getApplicationContext(),Form.class);
                Log.d("hello", "222");
                intent.putExtra("val",informations.get(position));
                Log.d("hello", "333");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(getApplicationContext(),Form.class);
        Log.d("hello", "222");
        intent.putExtra("backPressed","backPressed");
        Log.d("hello", "333");
        startActivity(intent);
        finish();
    }
}