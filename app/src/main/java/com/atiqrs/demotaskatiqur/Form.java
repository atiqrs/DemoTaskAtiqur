package com.atiqrs.demotaskatiqur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atiqrs.demotaskatiqur.Database.DatabaseHelper;
import com.atiqrs.demotaskatiqur.Database.SharedPref;
import com.atiqrs.demotaskatiqur.Database.model.Information;

import java.util.ArrayList;
import java.util.List;

public class Form extends AppCompatActivity /*implements View.OnClickListener*/ {
    TextView formTitle;
    private EditText nameForm, mobileForm, emailForm;
    private Button submitButtonForm, showButtonForm, updateButtonForm, deleteButtonForm;
    private TextView noInfoView;
    CustomAdapter infoAdapter;
    private int count = 0;
    private DatabaseHelper db;
    private Intent intent;
    private Information information;

    private static final String USER_NAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_KEY = "remember";

    private SharedPref sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        sp = new SharedPref(this);//Shared Preferences;


        formTitle = findViewById(R.id.formTitle);
        nameForm = findViewById(R.id.nameForm);
        mobileForm = findViewById(R.id.mobileForm);
        emailForm = findViewById(R.id.emailForm);
        submitButtonForm = findViewById(R.id.submitButtonForm);
        showButtonForm = findViewById(R.id.showButtonForm);
        updateButtonForm = findViewById(R.id.updateButtonForm);
        deleteButtonForm = findViewById(R.id.deleteButtonForm);
        //noInfoView = findViewById(R.id.noInfoView);

        if (getIntent().getSerializableExtra("val") != null) {
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
        } else if (getIntent().getSerializableExtra("backPressed") != null) {
            setForm();
        } else {
            setForm();
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
                    //clear all field
                    nameForm.setText("");
                    mobileForm.setText("");
                    emailForm.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error! cant insert info on DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ViewAllData.class);
                startActivity(intent);
                finish();
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
                    intent = new Intent(getApplicationContext(), ViewAllData.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Update info on DB!", Toast.LENGTH_SHORT).show();
                    nameForm.setText("");
                    mobileForm.setText("");
                    emailForm.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error! cant updating info on DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteInfo(information);
                    intent = new Intent(getApplicationContext(), ViewAllData.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Delete info from DB!", Toast.LENGTH_SHORT).show();
                    nameForm.setText("");
                    mobileForm.setText("");
                    emailForm.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error! cant Delete info from DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {

            sp.setStringValue(USER_NAME_KEY, "");
            sp.setStringValue(PASSWORD_KEY, "");

            sp.setBooleanValue(REMEMBER_KEY, false);

            String a = String.valueOf(sp.getBooleanValue(REMEMBER_KEY));
            Log.d("a", "Form: " + a);
            startActivity(new Intent(Form.this, MainActivity.class));
            finish();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    private void setForm() {
        setTitle("Form Page");
        formTitle.setText("Input your Information");
        updateButtonForm.setVisibility(View.GONE);
        deleteButtonForm.setVisibility(View.GONE);
        showButtonForm.setVisibility(View.VISIBLE);
        submitButtonForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setForm();
        finish();
    }
}