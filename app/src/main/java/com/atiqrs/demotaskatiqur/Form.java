package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atiqrs.demotaskatiqur.Database.DatabaseHelper;
import com.atiqrs.demotaskatiqur.Database.model.Information;

import java.util.ArrayList;
import java.util.List;

public class Form extends AppCompatActivity /*implements View.OnClickListener*/ {
    private EditText nameForm,mobileForm,emailForm;
    private Button submitButtonForm,skipButtonForm;
    private TextView noInfoView;
    CustomAdapter infoAdapter;
    private List<Information> infoList = new ArrayList<>();
    private int count=0;
    private DatabaseHelper db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = new DatabaseHelper(this);

        Log.d("info","Form class");
        nameForm = findViewById(R.id.nameForm);
        mobileForm = findViewById(R.id.mobileForm);
        emailForm = findViewById(R.id.emailForm);
        submitButtonForm = findViewById(R.id.submitButtonForm);
        skipButtonForm = findViewById(R.id.skipButtonForm);
        noInfoView = findViewById(R.id.noInfoView);

        skipButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),ViewAllData.class);
                startActivity(intent);
            }
        });

        submitButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertInformation(nameForm.getText().toString(),
                            mobileForm.getText().toString(),
                            emailForm.getText().toString());
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Insert info on DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertInformation(String name, String mobile, String email) {
        long id = db.insertInformation(name,mobile,email);
        Information i = db.getInfo(id);

        if (i != null) {
            // adding new note to array list at 0 position
            infoList.add(count, i);
            // refreshing the list
            //infoAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
            count++;
        }
    }

    private void toggleEmptyNotes() {
        if (db.getNotesCount() > 0) {
            noInfoView.setVisibility(View.GONE);
        } else {
            noInfoView.setVisibility(View.VISIBLE);
        }
    }
}