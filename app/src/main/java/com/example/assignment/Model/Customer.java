package com.example.assignment.Model;

public class Customer {
    public static final String TABLE_NAME = "customer";
    public static final String COLOMN_ID = "id";
    public static final String COLOMN_EMAIL = "email";
    public static final String COLOMN_FULLNAME = "fullname";
    public static final String COLOMN_PHONE_NUMBER = "phonenumber";
    public static final String COLOMN_PASSWORD = "password";
    public static final String COLOMN_ACCESSCONTROL = "accesscontrol";

    private int id;
    private String Email;
    private String Fullname;
    private String PhoneNumber;
    private String Password;
    private int AccessControl=2;

    public Customer(){

    }

    public Customer(String Email,String Fullname,String PhoneNumber,String Password){
        this.Email=Email;
        this.Fullname=Fullname;
        this.PhoneNumber=PhoneNumber;
        this.Password=Password;
    }

    public void setId(int id) {
        this.id = id;
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
        this.AccessControl = AccessControl;
    }

    public int getId() {
        return id;
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
