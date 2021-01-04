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
    TextView formTitle;
    private EditText nameForm,mobileForm,emailForm;
    private Button submitButtonForm,showButtonForm,updateButtonForm,deleteButtonForm;
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

        formTitle = findViewById(R.id.formTitle);
        nameForm = findViewById(R.id.nameForm);
        mobileForm = findViewById(R.id.mobileForm);
        emailForm = findViewById(R.id.emailForm);
        submitButtonForm = findViewById(R.id.submitButtonForm);
        showButtonForm = findViewById(R.id.showButtonForm);
        updateButtonForm = findViewById(R.id.updateButtonForm);
        deleteButtonForm = findViewById(R.id.deleteButtonForm);
        //noInfoView = findViewById(R.id.noInfoView);

        if(getIntent().getSerializableExtra("val") != null){
            information = (Information) getIntent().getSerializableExtra("val");

            setTitle("Update and Delete");
            formTitle.setText("Edit/Delete Info");
            updateButtonForm.setVisibility(View.VISIBLE);
            deleteButtonForm.setVisibility(View.VISIBLE);
            showButtonForm.setVisibility(View.GONE);
            submitButtonForm.setVisibility(View.GONE);

            nameForm.setText(information.getName());
            mobileForm.setText(information.getMobile());
            emailForm.setText(information.getEmail());
        } else {
            setTitle("Form Page");
            formTitle.setText("Input your Information");
            updateButtonForm.setVisibility(View.GONE);
            deleteButtonForm.setVisibility(View.GONE);
            showButtonForm.setVisibility(View.VISIBLE);
            submitButtonForm.setVisibility(View.VISIBLE);
        }

        db = new DatabaseHelper(this);

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

        showButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),ViewAllData.class);
                startActivity(intent);
            }
        });

        updateButtonForm.setOnClickListener(new View.OnClickListener() {
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

        deleteButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteInfo(information);
                    intent = new Intent(getApplicationContext(),ViewAllData.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Update info on DB!", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error! cant updating info on DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}