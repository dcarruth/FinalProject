package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.LineNumberReader;

public class ChooseTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        Bundle bund = getIntent().getExtras();
        String dated = bund.getString("date");
        String TAG = "Date";
        TextView textView = (TextView) findViewById(R.id.timeDate);
        textView.setText(dated);
        Log.d(TAG, "1234");
    }
    //Create menu
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

    public void onSelectTime(View view){
        Intent intent = new Intent(getApplicationContext(),ConfirmActivity.class);
        startActivity(intent);
    }
}
