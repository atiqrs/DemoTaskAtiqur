package com.atiqrs.demotaskatiqur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText loginUsername,loginPass;
    private Button logInButton;
    protected String USERNAME = "admin";
    protected String PASSWORD = "admin1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername= findViewById(R.id.loginUsername);
        loginPass= findViewById(R.id.loginPass);
        logInButton= findViewById(R.id.logInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginUsername.getText().toString();
                String password = loginPass.getText().toString();
                if (username.equals(USERNAME) && password.equals(PASSWORD)){
                    Intent intent = new Intent(getApplicationContext(),Form.class);
                    startActivity(intent);
                }
            }
        });

    }
}