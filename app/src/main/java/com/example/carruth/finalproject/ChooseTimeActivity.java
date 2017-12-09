package com.example.carruth.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity implements Employee.TimeCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        Bundle bund = getIntent().getExtras();
        String dated = bund.getString("date");
        String TAG = "Date";
        TextView textView = (TextView) findViewById(R.id.timeDate);
        textView.setText(dated);
        Log.d("On create", "1");
        new Employee(this).getTimesFromFireBase(bund.getString("employee"));
        Log.d("On create", bund.getString("employee"));
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

    @Override
    public void call(List<String> arr) {

        RadioGroup radio = (RadioGroup)findViewById(R.id.radioGroup);

        List <Integer> id = new ArrayList<>();

        id.add(R.id.radioButton);
        id.add(R.id.radioButton2);
        id.add(R.id.radioButton3);

        Log.d("Times",arr.toString());
        for (int i = 0; i < arr.size(); i++){
            RadioButton radioButton1 = new RadioButton(getApplicationContext());
            radioButton1.setBackgroundResource(R.drawable.radio_layout);
            radioButton1.setId(id.get(i));
            radioButton1.setText(arr.get(i));
            radioButton1.setTextSize(18);
            radioButton1.setTypeface(Typeface.DEFAULT_BOLD);
            radioButton1.setTextColor(Color.parseColor("#10105d"));
            radioButton1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#ffc000")));
            radioButton1.setY(16*(i+1));
            radio.addView(radioButton1);
        }

        TextView textView = new TextView(this);
        textView.setY(50);
        radio.addView(textView);

    }
}
