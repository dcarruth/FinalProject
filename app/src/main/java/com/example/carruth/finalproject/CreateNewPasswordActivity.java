package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateNewPasswordActivity extends AppCompatActivity {

    // private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);
    }

    public void createRandomPassword(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //Will need to create a randomized password and save it to the User data.
        Toast t = Toast.makeText(getApplicationContext(),
                "Please check your email for your new password.",Toast.LENGTH_LONG);
        t.show();
        startActivity(intent);
    }
}
