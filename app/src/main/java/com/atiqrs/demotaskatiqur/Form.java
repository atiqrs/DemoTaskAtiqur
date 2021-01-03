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
    private Button submitButtonForm,skipButtonForm,editButtonForm;
    private TextView noInfoView;
    CustomAdapter infoAdapter;
    private List<Information> infoList = new ArrayList<>();
    private int count=0;
    private DatabaseHelper db;
    private Intent intent;
    private Information information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameForm = findViewById(R.id.nameForm);
        mobileForm = findViewById(R.id.mobileForm);
        emailForm = findViewById(R.id.emailForm);
        submitButtonForm = findViewById(R.id.submitButtonForm);
        skipButtonForm = findViewById(R.id.skipButtonForm);
        editButtonForm = findViewById(R.id.editButtonForm);
        //noInfoView = findViewById(R.id.noInfoView);

        if(getIntent().getSerializableExtra("val") != null){
            information = (Information) getIntent().getSerializableExtra("val");
            nameForm.setText(information.getName());
            mobileForm.setText(information.getMobile());
            emailForm.setText(information.getEmail());

            editButtonForm.setVisibility(View.VISIBLE);
            skipButtonForm.setVisibility(View.GONE);
            submitButtonForm.setVisibility(View.GONE);
        }
        db = new DatabaseHelper(this);

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
                    db.insertInformation(nameForm.getText().toString(),
                            mobileForm.getText().toString(),
                            emailForm.getText().toString());
                    Toast.makeText(getApplicationContext(), "Insert info on DB!", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error! cant insert info on DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    information.setName(nameForm.getText().toString());
                    information.setMobile(mobileForm.getText().toString());
                    information.setEmail(emailForm.getText().toString());
                    db.updateInfo(information);
                    intent = new Intent(getApplicationContext(),ViewAllData.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Update info on DB!", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error! cant updating info on DB!", Toast.LENGTH_SHORT).show();
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

            count++;
        }
    }



}