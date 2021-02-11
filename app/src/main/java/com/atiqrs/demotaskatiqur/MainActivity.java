package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.atiqrs.demotaskatiqur.Database.SharedPref;

public class MainActivity extends AppCompatActivity {
    private EditText loginUsername,loginPass;
    private Button logInButton;
    private CheckBox remember;

    protected static String USERNAME = "admin";
    protected static String PASSWORD = "admin1";

    private static final String USER_NAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_KEY = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPref sp = new SharedPref(this);
        boolean bool= sp.getBooleanValue(REMEMBER_KEY);

        if (bool){
            String a = String.valueOf(bool);
            Log.d("a","Test: "+a);
            Intent intent = new Intent(getApplicationContext(),Form.class);
            startActivity(intent);
        } else if (bool != true){
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
                            sp.setStringValue(USER_NAME_KEY,USERNAME);
                            sp.setStringValue(PASSWORD_KEY,PASSWORD);
                            sp.setBooleanValue(REMEMBER_KEY, true);
                            String a = String.valueOf(sp.getBooleanValue(REMEMBER_KEY));
                            Log.d("a","Reme: "+a);
                        }
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}