package com.example.carruth.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class CreateNewPasswordActivity extends AppCompatActivity implements CreateNewPasswordFragment.OnFragmentInteractionListener{

    // private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);
        // Upload Email in UI if User already created account
        SharedPreferences s = getSharedPreferences("email.txt",MODE_PRIVATE);
        String email = s.getString("email", null);

        EditText t = (EditText) findViewById(R.id.email_forgot);
        if (email != null) {
            t.setText(email);
        }
    }

    public void createRandomPassword(View view) {


        EditText editText = (EditText)findViewById(R.id.email_forgot);

            FragmentManager fragMan = getSupportFragmentManager();
            FragmentTransaction fragTran = fragMan.beginTransaction();
            CreateNewPasswordFragment newPass = new CreateNewPasswordFragment();
            fragTran.add(R.id.fragment,newPass);
            fragTran.commit();
            Button button = (Button)findViewById(R.id.button);
            button.setVisibility(View.INVISIBLE);

    }

    public void onResetPass(View view) {
        EditText editText = (EditText) findViewById(R.id.security_question);
        EditText editText1 = (EditText) findViewById(R.id.security_answer);
        EditText editText2 = (EditText) findViewById(R.id.email_forgot);
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your address", Toast.LENGTH_SHORT).show();
        } else if (editText1.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
        } else {
            new User().updatePassword(editText2.getText().toString(), editText.getText().toString(), editText1.getText().toString());
            Toast.makeText(getApplicationContext(), "If your address and phone number match, your password will be reset to \"1234\"", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
    public Boolean validEmail(String email){
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Not sure what this needs to do but without it the app crashes
    }
}
