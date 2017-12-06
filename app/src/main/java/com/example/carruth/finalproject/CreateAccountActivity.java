package com.example.carruth.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();
    }


    public Boolean comparePass(String pass, String pass2){
        return pass.equals(pass2);
    }


    /**
     * Verifies that all fields are filled in the activity
     * @param fName
     * @param lName
     * @param address
     * @param city
     * @param zip
     * @param email
     * @param password
     * @param password2
     * @param answer
     * @return
     */
    public Boolean everythingFilled(String fName, String lName, String address, String city,
                                    String zip, String email, String password, String password2, String answer){

        return !(fName.isEmpty() || lName.isEmpty() || address.isEmpty() || city.isEmpty() || zip.isEmpty()
                || email.isEmpty() || password.isEmpty() || password2.isEmpty() || answer.isEmpty());
    }


    /************************************************************
     * Gathers info from the create account screen and save data in
     * a map. Then creates a User object for the rest of the app.
     * @param view User Interface
     * @throws Exception //If the parameter and code fails to load,
     * exception is thrown
     *************************************************************/
    public void validateData(View view) throws Exception {

        Map<String,String> userInfo = new HashMap<>();
        User user = new User(userInfo);
        SharedPreferences.Editor edit = getSharedPreferences("email.txt",MODE_PRIVATE).edit();

        //Get email to save to Shared Preferences
        EditText text = (EditText)findViewById(R.id.email_create_account);
        final String email = text.getText().toString();
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
        userInfo.put("password",password);

        //Get confirmed password from UI and check against original
        text = (EditText)findViewById(R.id.password_confirm);
        String passwordConfirm = text.getText().toString();

        //Get security question from UI and add to map
        Spinner sq = (Spinner) findViewById(R.id.security_question);
        String ques = sq.getSelectedItem().toString();
        userInfo.put("security_question",ques);

        //Get answer to security question and add to map
        text = (EditText)findViewById(R.id.security_answer);
        String answer = text.getText().toString();
        userInfo.put("security_answer",answer);


        user.setInformation(userInfo);




        if (everythingFilled(firstName,lastName,address,city,zip,email,password,passwordConfirm,answer)) {
            if (comparePass(password, passwordConfirm)) {

                final Gson gson = new Gson();
                final String userI = gson.toJson(user);
                user.saveUserToDataBase(userI);
                // Creates login in firebase that can be accessed anytime
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Invalid email or password"
                                            , Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Log.d("", "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    Log.i("Create User","Successfully created new authentication.");
                                    //Save object to json string to add it to the intent. Reconstruct in new activity
                                    Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                                    intent.putExtra("user", email);
                                    startActivity(intent);
                                }

                                // ...
                            }
                        });



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

    public void onStop(){
        super.onStop();
        mAuth = null;
    }
}
