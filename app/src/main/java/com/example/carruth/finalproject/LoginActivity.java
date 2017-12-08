package com.example.carruth.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Boolean canLogin;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canLogin = false;
        // Get access to data base
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);

        // Upload Email in UI if User already created account
        SharedPreferences s = getSharedPreferences("email.txt",MODE_PRIVATE);
        String email = s.getString("email", null);

        EditText t = (EditText) findViewById(R.id.login_email);
        if (email != null) {
            t.setText(email);
        }

        //Use FireBase to authenticate logins from users
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d("","onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("","onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    public void validateLogin(View view) {


        // Get email and password from UI
        EditText text = (EditText) findViewById(R.id.login_email);
        final String email = text.getText().toString();
        EditText pass = (EditText) findViewById(R.id.login_password);
        String password = pass.getText().toString();

        // Assert that both email and password are not empty
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
        } else {
            // Validate user's email and password through firebase
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("", "signInWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()){
                                Log.i("Login","Successful Login");
                                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                                intent.putExtra("user",email);
                                startActivity(intent);
                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {

                                Log.w("", "signInWithEmail:failed", task.getException());
                                Toast.makeText(getApplicationContext(), "Login Failed! Please try again!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        canLogin = false;
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
}
