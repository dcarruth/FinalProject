package com.example.carruth.finalproject;


import android.app.Activity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
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
    private TimeCallback callerActivity;

    public interface TimeCallback {
        void call(List<String> arr);
    }

    public Employee(Activity activity) {
        callerActivity = (TimeCallback)activity;
    }

    Employee(Map<String, String> em) {
        employeeInfo = em;
        availableTimes = null;

    }

    Employee (){
        employeeInfo = null;
        availableTimes = null;
    }

    //Just the Getters

    public Map<String, String> getEmployeeInfo() {
        return employeeInfo;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }




    //Just the Setters

    public void setEmployeeInfo(Map<String, String> employeeInfo) {
        this.employeeInfo = employeeInfo;
    }


    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void updateMap(String key, String info){
        employeeInfo.put(key,info);
    }

    public void saveData(){

        Gson gson = new Gson();

        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Available Times").child(employeeInfo.get("username"));
        ref.setValue(gson.toJson(this));

    }

    public void getTimesFromFireBase(final String employee){
        FirebaseDatabase Ref = FirebaseDatabase.getInstance();
        final String emp = getCorrectEmployee(employee);
        final DatabaseReference myRef = Ref.getReference("Available Times").child(emp);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = String.valueOf(dataSnapshot.getValue().toString());
                Log.d("Data from Data base", "Value is: " + value);

                Gson gson = new Gson();

                Employee emp = gson.fromJson(value, Employee.class);
                    if (emp != null) {
                    List<String> times = new ArrayList<String>();
                    times.add(emp.getAvailableTimes().get(0));
                    times.add(emp.getAvailableTimes().get(1));
                    times.add(emp.getAvailableTimes().get(2));
                    callerActivity.call(times);
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
     * Numeric value assigned in place of Employee - think as employee number for the sake of the
     * app
     * @param employee - the employee information, basically
     * @return returns the employee number in binary
     */
    private String getCorrectEmployee(String employee) {
        switch (employee) {
            case "0":
                return "0001";
            case "1":
                return "0010";
            case "2":
                return "0011";
            default:
                return "";
        }
    }

    /**
     * Creates unique employees, mainly for test purposes - gives available times, name, username,
     * and password
     */
    public void createEmployees(){
        //Create some employees to use
        Map<String,String> map1 = new HashMap<>();
        map1.put("name","John");
        map1.put("username","0001");
        map1.put("password","00000000");
        Employee employee1 = new Employee(map1);

        Map<String,String> map2 = new HashMap<>();
        map2.put("name","Julie");
        map2.put("username","0010");
        map2.put("password","00000000");
        Employee employee2 = new Employee(map2);

        Map<String,String> map3 = new HashMap<>();
        map3.put("name","Kevin");
        map3.put("username","0011");
        map3.put("password","00000000");
        Employee employee3 = new Employee(map3);

        //Add some available times
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();

        list1.add("1:00 PM");
        list1.add("3:00 PM");
        list1.add("5:00 PM");

        list2.add("11:00 AM");
        list2.add("2:00 PM");
        list2.add("4:00 PM");

        list3.add("8:00 AM");
        list3.add("10:00 AM");
        list3.add("12:00 PM");

        employee1.setAvailableTimes(list1);
        employee2.setAvailableTimes(list2);
        employee3.setAvailableTimes(list3);

        //Save employees
        employee1.saveData();
        employee2.saveData();
        employee3.saveData();
    }


}