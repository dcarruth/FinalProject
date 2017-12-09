package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        new Employee().createEmployees();

    }
    /**
     * Creates the Menu necessary for navigation for User account editing and logging out
     * @param menu UI for menu option
     * @return returns true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);
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
                intent.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
                intent3.putExtra("user",getIntent().getStringExtra("user"));
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
     * Grabs the information on the job(s) chosen, adds the estimated cost of the jobs and then
     * converts the monetary values and jobs into a String to be passed to the next activity
     * @param view
     */
    public void onContinue(View view){
        String user = getIntent().getStringExtra("user");
        double total = 0.0;
        String activities = "";
        String addServ = "";
        int employee = 0;
        // Check the check boxes
        CheckBox check1 = (CheckBox) findViewById(R.id.service1);
        CheckBox check2 = (CheckBox) findViewById(R.id.service2);
        CheckBox check3 = (CheckBox) findViewById(R.id.service3);
        CheckBox check4 = (CheckBox) findViewById(R.id.service4);
        CheckBox check5 = (CheckBox) findViewById(R.id.service5);
        CheckBox check6 = (CheckBox) findViewById(R.id.service6);

        if (check1.isChecked()) {
            total += 22.99;
            activities += check1.getText().toString();
            addServ += check1.getText().toString() + ", ";
            activities += "\n";
            employee = 0;
        }
        if (check2.isChecked()) {
            total += 49.99;
            activities += check2.getText().toString();
            addServ += check2.getText().toString() + ", ";
            activities += "\n";
            employee = 0;
        }
        if (check3.isChecked()) {
            total += 69.99;
            activities += check3.getText().toString();
            addServ += check3.getText().toString() + ", ";
            activities += "\n";
            employee = 1;
        }
        if (check4.isChecked()) {
            total += 69.99;
            activities += check4.getText().toString();
            addServ += check4.getText().toString() + ", ";
            activities += "\n";
            employee = 1;
        }
        if (check5.isChecked()) {
            total += 79.99;
            activities += check5.getText().toString();
            addServ += check5.getText().toString() + ", ";
            activities += "\n";
            employee = 2;
        }
        if (check6.isChecked()) {
            total += 99.99;
            activities += check6.getText().toString();
            addServ += check6.getText().toString() + ", ";
            activities += "\n";
            employee = 2;
        }

        if (total == 0.0) {
            Toast.makeText(getApplicationContext(),"Please select a service!", Toast.LENGTH_SHORT).show();
        } else {
            double howMuch = Math.round(total * 100.0)/100.0;
            addServ += "cost $" + Double.toString(howMuch);
            Intent intent = new Intent(getApplicationContext(), CalanderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("cost",Double.toString(howMuch));
            bundle.putString("user",user);
            bundle.putString("job",activities);
            bundle.putString("job2",addServ);
            bundle.putString("employee",Integer.toString(employee));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
