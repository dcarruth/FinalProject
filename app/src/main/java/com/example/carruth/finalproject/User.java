package com.example.carruth.finalproject;


import java.util.Map;

public class User {

    private Map<String,String> information;
    private int appointmentsSet;

    User(Map<String,String> m){
        information = m;
        appointmentsSet = 0;
    }

    User(String email, String password){
        //Get JSON from data base and fill map with data
    }


    public void setAppointmentsSet(int i){
        if (i != 0)
            appointmentsSet += i;
        else
            appointmentsSet = 0;
    }

    public String getInformation(String requested){
        return information.get(requested);
    }

    public void updateInformation(String key, String info){
        information.put(key,info);
    }

    public void saveUserToDataBase(){

    }

}
