package com.example.msdhainizam.loginschoolapp;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class GuardianData {

    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<String> childName;
    private ArrayList<String> childICNumber;

    public GuardianData() {

    }

    public GuardianData(String name,
                       String email,
                       String phoneNumber,
                        ArrayList<String> childName,
                        ArrayList<String> childICNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.childName = childName;
        this.childICNumber = childICNumber;
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

    public void setChildName(ArrayList<String> childName) {
        this.childName = childName;
    }

    public ArrayList<String> getChildName() {
        return childName;
    }

    public void setChildICNumber(ArrayList<String> childICNumber) {
        this.childICNumber = childICNumber;
    }

    public ArrayList<String> getChildICNumber() {
        return childICNumber;
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phoneNumber;
    }
}
