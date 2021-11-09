package com.example.rescuealert;

public class Data {
    String address, name, phoneNum, emergencyNum, lName;

    public Data(String address, String name, String phoneNum, String emergencyNum, String lName) {
        this.address = address;
        this.name = name;
        this.phoneNum = phoneNum;
        this.emergencyNum = emergencyNum;
        this.lName = lName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(String emergencyNum) {
        this.emergencyNum = emergencyNum;
    }
}

