package com.example.carruth.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    //Private User user;
EditText p1 = (EditText)findViewById(R.id.password);
EditText p2 = (EditText)findViewById(R.id.password_confirm);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public String hashPassword(String Password){
        return "";
    }

    public Boolean checkPass(String p, String pp){
        return false;
    }
    // NOT FINISHED YET
    public Boolean comparePass(EditText p1, EditText p2){
        if(p1.getText().toString().equals( p2.getText().toString())){
         return true;
        }
        else {
            return false;
        }
    }
//


    public Boolean everythingFilled(String fName, String lName, String address, String city,
                                    String zip, String email, String password, String password2){
        return false;
    }
    public Boolean validateData(){
        return false;
    }

}
