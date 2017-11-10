package com.example.carruth.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
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

    public Boolean comparePass(String pass, String pass2){
        return pass.equals(pass2);
    }



    public Boolean everythingFilled(String fName, String lName, String address, String city,
                                    String zip, String email, String password, String password2){

        return !(fName.isEmpty() || lName.isEmpty() || address.isEmpty() || city.isEmpty() || zip.isEmpty()
                || email.isEmpty() || password.isEmpty() || password2.isEmpty());
    }

    /************************************************************
     * Gathers info from the create account screen and save data in
     * a map. Then creates a User object for the rest of the app.
     *************************************************************/
    public void validateData(View view){

        Map<String,String> userInfo = new HashMap<>();

        Context c = getApplicationContext();
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor edit = s.edit();

        //Get email to save to Shared Preferences
        EditText text = (EditText)findViewById(R.id.email_create_account);
        String email = text.getText().toString();
        userInfo.put("email",email);

        edit.putString("email",email);
        edit.apply();

        //Get first name from UI and add to map
        text = (EditText)findViewById(R.id.first_name);
        String firstName = text.getText().toString();
        userInfo.put("firstName",firstName);

        //Get last name from UI and add to map
        text = (EditText)findViewById(R.id.last_name);
        String lastName = text.getText().toString();
        userInfo.put("lastName",lastName);

        //Get address from UI and add to map
        text = (EditText)findViewById(R.id.address);
        String address = text.getText().toString();
        userInfo.put("address",address);

        //Get city from UI and add to map
        text = (EditText)findViewById(R.id.city);
        String city = text.getText().toString();
        userInfo.put("city",city);

        //Get state from UI and add to map
        Spinner st = (Spinner) findViewById(R.id.state);
        String state = st.getSelectedItem().toString();
        userInfo.put("state",state);

        //Get zip from UI and add to map
        text = (EditText)findViewById(R.id.zip);
        String zip = text.getText().toString();
        userInfo.put("zip",zip);

        //Get phone from UI and add to map
        text = (EditText)findViewById(R.id.phone);
        String phone = text.getText().toString();
        userInfo.put("phone",phone);

        //Get password from UI and add to map
        text = (EditText)findViewById(R.id.password);
        String password = text.getText().toString();
        userInfo.put("password",hashPassword(password));

        //Get confirmed password from UI and add to map
        text = (EditText)findViewById(R.id.password_confirm);
        String passwordConfirm = text.getText().toString();

        User user = new User(userInfo);


        if (everythingFilled(firstName,lastName,address,city,zip,email,password,passwordConfirm)) {
            if (comparePass(password, passwordConfirm)) {

                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);

                //Save object to json string to add it to the intent. Reconstruct in new activity
                Gson gson = new Gson();
                String userI = gson.toJson(user);

                intent.putExtra("user", userI);
                startActivity(intent);
            } else {
                Toast t = Toast.makeText(getApplicationContext(), "Please make sure your passwords match!", Toast.LENGTH_LONG);
                t.show();
            }
        }
        else {
            Toast t = Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_LONG);
            t.show();
        }
    }

}
