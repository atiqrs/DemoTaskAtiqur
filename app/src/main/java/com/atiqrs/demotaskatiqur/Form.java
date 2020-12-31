package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Form extends AppCompatActivity {
    private EditText nameForm,mobileForm,emailForm;
    private Button submitButtonForm,skipButtonForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Log.d("info","Form class");
        nameForm = findViewById(R.id.nameForm);
        mobileForm = findViewById(R.id.mobileForm);
        emailForm = findViewById(R.id.emailForm);
        submitButtonForm = findViewById(R.id.submitButtonForm);
        skipButtonForm = findViewById(R.id.skipButtonForm);
    }
}