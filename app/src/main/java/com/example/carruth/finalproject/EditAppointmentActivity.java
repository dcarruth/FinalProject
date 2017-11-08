package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
    }

    public void changeTime(View view){
        Intent intent = new Intent(getApplicationContext(),ChooseTimeActivity.class);
        startActivity(intent);
    }

    public void changeDate(View view){
        Intent intent = new Intent(getApplicationContext(),CalanderActivity.class);
        startActivity(intent);
    }

    public void changeService(View view){
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(intent);
    }

    public void onChange(View view){
        RadioButton radio = (RadioButton) findViewById(R.id.date);
        RadioButton radio2 = (RadioButton) findViewById(R.id.time);
        RadioButton radio3 = (RadioButton) findViewById(R.id.jobs);

        if (radio.isChecked()){
            changeDate(view);
        }
        else if (radio2.isChecked()){
            changeTime(view);
        }
        else if (radio3.isChecked()){
            changeService(view);
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(),"Please select an option to change!"
                    ,Toast.LENGTH_LONG);
            t.show();
        }
    }
}

