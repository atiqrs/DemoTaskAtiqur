package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText loginUsername,loginPass;
    private Button logInButton;
    private CheckBox remember;
    protected static String USERNAME = "admin";
    protected static String PASSWORD = "admin1";

    private static final String SHARED_PREF_DATA = "defaultLoginData";
    private static final String USER_NAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_KEY = "remember";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSharePref();
        setContentView(R.layout.activity_main);
        if (sharedPref.getBoolean(REMEMBER_KEY,false)){
            Intent intent = new Intent(getApplicationContext(),Form.class);
            startActivity(intent);
        } else{
            setTitle("Log in");
            loginUsername= findViewById(R.id.loginUsername);
            loginPass= findViewById(R.id.loginPass);
            logInButton= findViewById(R.id.logInButton);
            remember= findViewById(R.id.rememberCheckBox);

            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = loginUsername.getText().toString();
                    String password = loginPass.getText().toString();
                    if (username.equals(USERNAME) && password.equals(PASSWORD)){
                        Intent intent = new Intent(getApplicationContext(),Form.class);
                        startActivity(intent);

                        if (remember.isChecked()){
                            initSharePref();
                            editor.putString(USER_NAME_KEY,USERNAME);
                            editor.putString(PASSWORD_KEY,PASSWORD);
                            editor.putBoolean(REMEMBER_KEY,true);
                            editor.apply();
                        }
                        finish();
                    }
                }
            });
        }

    }
    public void initSharePref(){
        sharedPref = getSharedPreferences(SHARED_PREF_DATA,MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}