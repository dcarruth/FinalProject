package com.example.carruth.finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

public class CalanderActivity extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    private Bundle bund;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public Bundle bundle;
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/"+ year ;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
                Intent intent = new Intent(CalanderActivity.this,ChooseTimeActivity.class);
                bundle = new Bundle();
                bundle.putString("date",date);
                bund = bundle;
            }

        });

    }
    public void onChooseDate(View view){
        Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
        intent.putExtras(bund);
        startActivity(intent);
    }

}
