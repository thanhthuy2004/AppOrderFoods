package com.ltdd.orderfood.Model;



public class User {
    private  String Name;
    private  String Password,Phone,IsStaff;

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
        IsStaff = "false";
    }
}
