package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onLogin(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    public void onCreateNewAccount(View view){
        Intent intent = new Intent(getApplicationContext(),CreateAccountActivity.class);
        startActivity(intent);
    }

    public void onForgotPassword(View view){
        Intent intent = new Intent(getApplicationContext(),CreateNewPasswordActivity.class);
        startActivity(intent);
    }

}
