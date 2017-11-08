package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }

    public void onConfirm(View view){
        Toast t = Toast.makeText(getApplicationContext(),"Thank you for your appointment!", Toast.LENGTH_LONG);
        t.show();
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(intent);
    }
}
