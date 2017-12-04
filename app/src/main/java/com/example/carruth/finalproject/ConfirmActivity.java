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
        jobs.setText(bund.getString("service"));

        TextView cost = (TextView)findViewById(R.id.cost);
        cost.setText(bund.getString("cost"));
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
    public void onConfirm(View view){
        Toast t = Toast.makeText(getApplicationContext(),"Thank you for your appointment!", Toast.LENGTH_LONG);
        t.show();
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}
