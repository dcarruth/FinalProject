package com.example.carruth.finalproject;





import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class User {


    private Map<String,String> information;
    private int appointmentsSet;


    User(Map<String,String> m){
        information = m;
        appointmentsSet = 0;

    }

    User(){
        information = new HashMap<>();
        appointmentsSet = 0;
    }

    public void pullFromDataBase(String email){

        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference myRef = data.getReference(parseEmailToKey(email));

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Here it is!", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
    }


    public void setAppointmentsSet(int i){
        if (i != 0)
            appointmentsSet += i;
        else
            appointmentsSet = 0;
    }
    public void setInformation(Map<String,String> mMap){
        information = mMap;
    }
    public String getInformation(String requested){
        return information.get(requested);
    }

    public void updateInformation(String key, String info){
        information.put(key,info);
    }


    public String parseEmailToKey(String email){

        // String to store final email to be used as key in data base
        String finalKey = "";

        // Convert email to char array for parsing
        char [] middle = email.toCharArray();

        // Parsing and replacing @ and . characters with ( and )
        for (int i = 0; i < email.length(); i++){
            if (middle[i] == '@'){
                middle[i] = '(';
            }
            if (middle[i] == '.'){
                middle[i] = ')';
            }
            // Add one char at a time to the final string
            finalKey = finalKey + middle[i];
        }
        return finalKey;
    }

    public void saveUserToDataBase(String save){

        //Save to database
        FirebaseDatabase data = FirebaseDatabase.getInstance();

        // Parse email into firebase approved string
        DatabaseReference ref = data.getReference(parseEmailToKey(information.get("email")));
        ref.setValue(save);

    }

}
