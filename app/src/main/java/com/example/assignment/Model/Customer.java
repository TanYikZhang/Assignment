package com.example.assignment.Model;

public class Customer {
    private String Email;
    private String Fullname;
    private String PhoneNumber;
    private String Password;
    private int AccessControl;

    public Customer(){

    }

    public Customer(String Email,String Fullname,String PhoneNumber,String Password,int AccessControl){
        this.Email=Email;
        this.Fullname=Fullname;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
        this.AccessControl=AccessControl;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setAccessControl(int AccessControl){
    }

    public String getEmail() {
        return Email;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public int getAccessControl() {
        return AccessControl;
    }
}
