package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
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
    CustomAdapter infoAdapter;
    List<Information> informations = new ArrayList<>();
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        allInfoView = findViewById(R.id.allInfoView);
        //noInfoView = findViewById(R.id.noInfoView);

        db = new DatabaseHelper(this);
        informations.addAll(db.getAllInfo());

        CustomAdapter adapter = new CustomAdapter(this,informations);
        allInfoView.setAdapter(adapter);
        allInfoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String value = ListItemName[position];
//                Toast.makeText(MainActivity.this, "Selected item is: "+value, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Form.class);
                intent.putExtra("val",informations.get(position));
                startActivity(intent);
            }
        });

    }
}