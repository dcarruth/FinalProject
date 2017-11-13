package com.example.carruth.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences s = getSharedPreferences("email.txt",MODE_PRIVATE);
        String email = s.getString("email", null);

        EditText t = (EditText) findViewById(R.id.login_email);
        if (email != null) {
            t.setText(email);
        }
    }

    public void validateLogin(View view){

        Log.d("","1");
        EditText text = (EditText)findViewById(R.id.login_email);
        String email = text.getText().toString();
        Log.d("","1");
        EditText pass = (EditText)findViewById(R.id.login_password);
        String password = pass.getText().toString();
        Log.d("","1");
        new User().pullFromDataBase(email);
        Log.d("","2");
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        intent.putExtra("user","");

        startActivity(intent);
    }
}
