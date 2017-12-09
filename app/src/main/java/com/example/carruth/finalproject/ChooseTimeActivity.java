package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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

    }
    //Create menu
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
            case R.id.edit_app:
                Intent intent2 = new Intent(getApplicationContext(),EditAppointmentActivity.class);
                intent2.putExtra("user",getIntent().getExtras().getString("user"));
                startActivity(intent2);
                return true;
            case R.id.logout:
                Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
                intent3.putExtra("user",getIntent().getExtras().getString("user"));
                startActivity(intent3);
            case R.id.camera:
                Intent intent4 = new Intent(getApplicationContext(),CameraActivity.class);
                intent4.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent4);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * This allows users to check anytime that the employee is available on a given day.
     * Values are currently hardcoded for demonstration purposes only
     * @param view UI view-ability
     */
    public void onSelectTime(View view){

        Bundle bund = getIntent().getExtras();
        String user = bund.getString("user");

        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radioButton);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton)findViewById(R.id.radioButton3);

        if (radioButton1.isChecked()){
            new User().updateDataBase(user,"time",radioButton1.getText().toString());
            bund.putString("time",radioButton1.getText().toString());
            Intent intent = new Intent(getApplicationContext(),ConfirmActivity.class);
            intent.putExtras(bund);
            startActivity(intent);
        }

        else if (radioButton2.isChecked()){
            new User().updateDataBase(user,"time",radioButton2.getText().toString());
            bund.putString("time",radioButton2.getText().toString());
            Intent intent = new Intent(getApplicationContext(),ConfirmActivity.class);
            intent.putExtras(bund);
            startActivity(intent);
        }

        else if (radioButton3.isChecked()){
            new User().updateDataBase(user,"time",radioButton3.getText().toString());
            bund.putString("time",radioButton3.getText().toString());
            Intent intent = new Intent(getApplicationContext(),ConfirmActivity.class);
            intent.putExtras(bund);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Please select a time!",Toast.LENGTH_SHORT).show();
        }
    }
}
