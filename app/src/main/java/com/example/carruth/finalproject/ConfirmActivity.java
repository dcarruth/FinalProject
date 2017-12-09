package com.example.carruth.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle bund = getIntent().getExtras();


        TextView date = (TextView)findViewById(R.id.date_of_appointment);
        date.setText(bund.getString("date"));

        TextView time = (TextView)findViewById(R.id.time_of_appointment);
        time.setText(bund.getString("time"));

        TextView jobs = (TextView)findViewById(R.id.repair);
        jobs.setText(bund.getString("job"));

        TextView cost = (TextView)findViewById(R.id.cost);
        cost.setText(bund.getString("cost"));

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
     * When the User presses the confirm button, the information is then stored
     * @param view UI view-ability
     */
    public void onConfirm(View view){
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("user");
        String cost = bundle.getString("cost");
        String date = bundle.getString("date");
        String job = bundle.getString("job2");
        String time = bundle.getString("time");

        Map<String,String> map = new HashMap<>();
        map.put("email",email);
        map.put("cost",cost);
        map.put("date",date);
        map.put("job",job);
        map.put("time",time);

        new User().jobCheck(email,map);
        Toast t = Toast.makeText(getApplicationContext(),"Thank you for your appointment!", Toast.LENGTH_LONG);
        t.show();
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}
