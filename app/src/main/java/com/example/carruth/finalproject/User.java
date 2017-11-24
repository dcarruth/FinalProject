package com.example.carruth.finalproject;






import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Stores user information for appointment setting. Uses a map as main storage structure.
 */
public class User {


    private Map<String,String> information;
    private int appointmentsSet;
    private String json;

    User(Map<String,String> m){
        information = m;
        appointmentsSet = 0;
        json = "";
    }

    User(){
        information = new HashMap<>();
        appointmentsSet = 0;
        json = "";
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

    /**
     * Updates specific element of user map
     * @param key item being updated
     * @param info item replacing what is being updated
     */
    public void updateInformation(String key, String info){
        information.put(key,info);
    }


    /**
     * Parses the email from the user to make it a string that FireBase can use as a key
     * @param email email from the user's information
     * @return parsed email that contains no '@' or '.'
     */
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
        if (finalKey.equals("")){
            Log.d("Failed Parse","Failed to parse Email");
        }
        return finalKey;
    }


    /**
     * Used when users forget their password to update their password that is emailed to them
     * @param email email from the user
     * @param newPassword the password used to replace the current password
     */
    public void changePassword(String email, String newPassword){

    }

    /**
     * This method pulls JSON strings from the data base to restore user data
     * @param email The email that is used to store each user
     * @return JSON string of the User's object
     */
    public String getUserDataFromDataBase(String email){

        FirebaseDatabase Ref = FirebaseDatabase.getInstance();
        DatabaseReference myRef = Ref.getReference(parseEmailToKey(email));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Data from Data base", "Value is: " + value);
                json = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed Read", "Failed to read value.", error.toException());
            }
        });

        return json;
    }

    /**
     * This method saves User information to the data base as JSON
     * @param save JSON  string of the User Object
     */
    public void saveUserToDataBase(String save){

        //Save to database
        FirebaseDatabase data = FirebaseDatabase.getInstance();

        // Parse email into firebase approved string
        DatabaseReference ref = data.getReference(parseEmailToKey(information.get("email")));
        ref.setValue(save);
        Log.i("Saving Data","Successfully saved data to data base");
    }

}
