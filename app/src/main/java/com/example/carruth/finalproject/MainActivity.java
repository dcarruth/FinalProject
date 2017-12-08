package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * The initial start activity in the app that redirects individuals to either create an account,
 * login to an already existing account, or the ability to retrieve a forgotten password
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Link to activity where user can log into app
     * @param view UI view-ability
     */
    public void onLogin(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Link to create a new account activity
     * @param view UI view-ability
     */
    public void onCreateNewAccount(View view){
        Intent intent = new Intent(getApplicationContext(),CreateAccountActivity.class);
        startActivity(intent);
    }

    /**
     * Link to activity that allows user to reset a forgotten password.
     * @param view UI view-ability
     */
    public void onForgotPassword(View view){
        Intent intent = new Intent(getApplicationContext(),CreateNewPasswordActivity.class);
        startActivity(intent);
    }

}
