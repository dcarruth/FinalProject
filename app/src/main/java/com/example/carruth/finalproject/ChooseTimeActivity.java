package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.LineNumberReader;

public class ChooseTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
    }

    public void onSelectTime(View view){
        Intent intent = new Intent(getApplicationContext(),ConfirmActivity.class);
        startActivity(intent);
    }
}
