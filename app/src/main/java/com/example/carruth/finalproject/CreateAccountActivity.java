package com.example.carruth.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    //Private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public String hashPassword(String Password){
        return "";
    }

    public Boolean validateData(){
        return false;
    }

}
