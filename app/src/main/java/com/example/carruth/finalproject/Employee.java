package com.example.carruth.finalproject;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
/**
 * Unused code - possible future implementation for the employee side of the app
 *
 */

public class Employee {

    private Map<String, String> employeeInfo;
    private List<String> availableTimes;
    private List<String> availableTasks;
    private List<String> availableAreas;
    private Map<String, String> taskLength;
    private DatabaseReference theDatabase;
    private Employee employee;
    Employee(Map<String, String> em) {
        employeeInfo = em;
        availableTimes = null;
        availableAreas = null;
        availableTasks = null;
    }

    //Just the Getters

    public List<String> getAvailableTasks() {
        return availableTasks;
    }

    public Map<String, String> getEmployeeInfo() {
        return employeeInfo;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public List<String> getAvailableAreas() {
        return availableAreas;
    }


    //Just the Setters

    public void setEmployeeInfo(Map<String, String> employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public void setAvailableTasks(List<String> availableTasks) {
        this.availableTasks = availableTasks;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void setAvailableAreas(List<String> availableAreas) {
        this.availableAreas = availableAreas;
    }

    public void saveData(String save){
        FirebaseDatabase data = FirebaseDatabase.getInstance();



    }

    public Employee retrieveData(){




        return employee;
    }


}