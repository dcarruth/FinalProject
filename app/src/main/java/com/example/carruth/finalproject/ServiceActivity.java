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

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);
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

    public void onContinue(View view) {
        Bundle bundle;
        bundle = getIntent().getExtras();
        String json = bundle.getString("user");
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        double total = 0.0;

        // Check the check boxes

        CheckBox check1 = (CheckBox) findViewById(R.id.service1);
        CheckBox check2 = (CheckBox) findViewById(R.id.service2);
        CheckBox check3 = (CheckBox) findViewById(R.id.service3);
        CheckBox check4 = (CheckBox) findViewById(R.id.service4);
        CheckBox check5 = (CheckBox) findViewById(R.id.service5);
        CheckBox check6 = (CheckBox) findViewById(R.id.service6);

        if (check1.isChecked()) {
            total += 22.99;
            user.updateInformation("service1",check1.getText().toString());
        }
        if (check2.isChecked()) {
            total += 49.99;
            user.updateInformation("service2",check2.getText().toString());
        }
        if (check3.isChecked()) {
            total += 69.99;
            user.updateInformation("service3",check3.getText().toString());
        }
        if (check4.isChecked()) {
            total += 69.99;
            user.updateInformation("service4",check4.getText().toString());
        }
        if (check5.isChecked()) {
            total += 79.99;
            user.updateInformation("service5",check5.getText().toString());
        }
        if (check6.isChecked()) {
            total += 99.99;
            user.updateInformation("service6",check6.getText().toString());
        }

        if (total == 0.0) {
            Toast.makeText(getApplicationContext(),"Please select a service!", Toast.LENGTH_SHORT).show();
        } else {
            user.updateInformation("cost", Double.toString(total));
            bundle.putString("user", gson.toJson(user));
            Intent intent = new Intent(getApplicationContext(), CalanderActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
