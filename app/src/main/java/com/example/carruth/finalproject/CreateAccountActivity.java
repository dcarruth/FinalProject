package com.example.carruth.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    //Private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public String hashPassword(String Password){
        return "";
    }

    public Boolean comparePass(){

        EditText p1 = (EditText)findViewById(R.id.password);
        EditText p2 = (EditText)findViewById(R.id.password_confirm);

        if(p1.getText().toString().equals( p2.getText().toString())){
         return true;
        }
        else {
            return false;
        }
    }



    public Boolean everythingFilled(String fName, String lName, String address, String city,
                                    String zip, String email, String password, String password2){
        return false;
    }
    public void validateData(View view){

        Context c = getApplicationContext();
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor edit = s.edit();

        EditText text = (EditText)findViewById(R.id.email_create_account);
        String email = text.getText().toString();

        edit.putString("email",email);
        edit.commit();

        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(intent);

    }

}
