package com.example.carruth.finalproject;






import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Stores user information for appointment setting. Uses a map as main storage structure.
 */
public class User {


    private Map<String,String> information;
    private String json = null;

    User(Map<String,String> m){
        information = m;
    }

    User(){
        information = new HashMap<>();
    }


    public void setInformation(Map<String,String> mMap){
        information = mMap;
    }

    /**
     * Gets specific information from User map
     * @param requested The piece of info needed
     * @return The info requested
     */
    public String getInformation(String requested) {

        if (information.containsKey(requested)) {
            return information.get(requested);
        } else {
            return null;
        }
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
     * Saves the User's information to the database, such as name, email and appointments
     * @param email - e-mail user information
     */
    public void saveUserToDataBase(String email){
        final FirebaseDatabase ref = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = ref.getReference("Appointments");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            List<String> list = new ArrayList<String>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String myObject = data.getKey();
                    list.add(myObject);
                }

                int i = Integer.parseInt(list.get(list.size()-1));
                i++;
                String child = Integer.toString(i);
                for(Map.Entry<String, String> map : information.entrySet()){
                    myRef.child(child).child("information").child(map.getKey()).setValue(map.getValue());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method pulls JSON strings from the data base to restore user data
     * @param email The email that is used to store each user
     * @return JSON string of the User's object
     */
    public void updateDataBase(final String email, final String info, final String save){

        FirebaseDatabase Ref = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = Ref.getReference("Appointments");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String myObject = data.getKey();

                    if (data.child("information").child("email").getValue().toString().equals(email)){
                        myRef.child(myObject).child("information").child(info).setValue(save);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.w("Failed Read", "Failed to read value.", error.toException());
            }
        });

    }

    /**
     * Inserts the information, such as name, address, etc. and adds it too the database in the
     * section that contains the email passed in.
     * @param email - the email passed in to find the correct user information for updating purposes
     * @param map - Mainly meant to store the information into a single parsable variable that can
     *            be easily accessed.
     */
    public void jobCheck(final String email, final Map<String,String> map){
        FirebaseDatabase Ref = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = Ref.getReference("Appointments");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String myObject = data.getKey();

                    if (data.child("information").child("email").getValue().toString().equals(email)){
                        if (data.child("information").child("job").exists()){
                            map.put("firstName",data.child("information").child("firstName").getValue().toString());
                            map.put("lastName",data.child("information").child("lastName").getValue().toString());
                            map.put("address",data.child("information").child("address").getValue().toString());
                            map.put("city",data.child("information").child("city").getValue().toString());
                            map.put("state",data.child("information").child("state").getValue().toString());
                            map.put("zip",data.child("information").child("zip").getValue().toString());
                            map.put("phone",data.child("information").child("phone").getValue().toString());
                            information = map;
                            saveUserToDataBase(email);
                        } else {
                            for (Map.Entry<String,String> map1 : map.entrySet()){
                                updateDataBase(email,map1.getKey(),map1.getValue());
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.w("Failed Read", "Failed to read value.", error.toException());
            }
        });

    }

    /**
     * Changes the user's password when they forget what their password is
     * @param email User's email to check for validation
     * @param address User's address for validation
     * @param phone User's phone for validation
     */
    public void updatePassword(final Context c, final String email, final String address, final String phone, final String newPass){
        FirebaseDatabase Ref = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = Ref.getReference("Appointments");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Boolean test = true;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String myObject = data.getKey();
                    if (data.child("information").child("email").getValue().toString().equals(email)) {
                        test = false;
                        if (data.child("information").child("address").getValue().toString().equals(address)) {
                            saveUserToDataBase(email);
                            if (data.child("information").child("phone").getValue().toString().equals(phone)) {
                                myRef.child(myObject).child("information").child("password").setValue(newPass);
                                Toast.makeText(c, "Successfully Changed Password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(c, "Incorrect Phone Number!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(c,"Incorrect Address!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (test){
                    Toast.makeText(c,"Email not found. Please create an account!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.w("Failed Read", "Failed to read value.", error.toException());
            }
        });

    }

}