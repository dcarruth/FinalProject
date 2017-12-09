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
import android.widget.Toast;

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
        bund = getIntent().getExtras();
        bund.putString("date","0/00/0000");
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public Bundle bundle;
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {

                String date = (month + 1) + "/" + dayOfMonth + "/"+ year ;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
                bundle = getIntent().getExtras();
                bundle.putString("date",date);
                bund = bundle;
            }

        });

    }

    /**
     * Creates the Menu necessary for navigation for User account editing and logging out
     * @param menu UI for menu option
     * @return returns true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu,menu);
        return true;
    }
    /**
     * This code dictates the upper-bar menu options that allows for easier navigation and access to
     * certain normally non-reachable activities, such as editing account information
     * @param item items that populate the menu
     * @return the item that was selected by the user
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_acc:
                Intent intent = new Intent(getApplicationContext(),EditAccountActivity.class);
                intent.putExtra("user",getIntent().getExtras().getString("user"));
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
                intent3.putExtra("user",getIntent().getExtras().getString("user"));
                startActivity(intent3);
                return true;
            case R.id.camera:
                Intent intent4 = new Intent(getApplicationContext(),CameraActivity.class);
                intent4.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Allows the User to select a date that User wants for the employee to come by and perform
     * a service for them. It also updates this information to the database.
     * @param view UI view-ability
     */
    public void onChooseDate(View view) {

        if (bund.get("date").equals("0/00/0000")) {
            Toast.makeText(getApplicationContext(), "No date selected!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
            intent.putExtras(bund);
            startActivity(intent);
        }
    }

}
