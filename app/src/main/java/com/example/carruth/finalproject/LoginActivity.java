package com.example.carruth.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context c = getApplicationContext();
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(c);
        String email = s.getString("email", null);
        System.out.println(email);
        EditText t = (EditText) findViewById(R.id.login_email);
        if (email != null) {
            t.setText(email);
        }
    }

    public void validateLogin(View view){
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(intent);
    }
}
