package com.example.msdhainizam.loginschoolapp;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class GuardianData {

    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<String> childData;

    public GuardianData() {

    }

    public GuardianData(String name,
                       String email,
                       String phoneNumber,
                        ArrayList<String> childData) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.childData = childData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setChildData(ArrayList<String> childData) {
        this.childData = childData;
    }

    public ArrayList<String> getChildData() {
        return childData;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phoneNumber;
    }
}
