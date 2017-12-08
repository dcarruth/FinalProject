package com.example.carruth.finalproject;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
/**
 * Created by Daniel Thomsen on 11/20/2017.
 */

public class Employee {

    private Map<String, String> employeeInfo;
    private List<String> availableTimes;

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
        DatabaseReference ref = data.getReference(employeeInfo.get("username"));
        ref.setValue(gson.toJson(this));
    }

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