package com.example.carruth.finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import com.google.gson.Gson;

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
                bundle = getIntent().getExtras();
                bundle.putString("date",date);
                bund = bundle;
            }

        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_acc:
                startActivity(new Intent(this, EditAccountActivity.class));
                return true;
            case R.id.edit_app:
                startActivity(new Intent(this, EditAppointmentActivity.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onChooseDate(View view){
        new User().updateDataBase(getIntent().getExtras().getString("user"),"date",bund.getString("date"));
        Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
        intent.putExtras(bund);
        startActivity(intent);
    }

}
