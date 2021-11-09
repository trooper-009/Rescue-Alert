package com.example.rescuealertadmin;

public class MainData {
    String address, name, phoneNum, UID, lName;


    MainData () {};

    public MainData(String address, String name, String phoneNum, String UID) {
        this.address = address;
        this.name = name;
        this.phoneNum = phoneNum;
        this.UID = UID;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
