package com.example.carruth.finalproject;






import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



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
        if (finalKey.equals("")){
            Log.d("Failed Parse","Failed to parse Email");
        }
        return finalKey;
    }

    public void saveUserToDataBase(String save){

        //Save to database
        FirebaseDatabase data = FirebaseDatabase.getInstance();

        // Parse email into firebase approved string
        DatabaseReference ref = data.getReference(parseEmailToKey(information.get("email")));
        ref.setValue(save);
        Log.i("Saving Data","Successfully saved data to data base");
    }

}
