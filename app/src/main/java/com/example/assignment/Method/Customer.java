package com.example.assignment.Method;

public class Customer {
    private String Email;
    private String Fullname;
    private String PhoneNumber;
    private String Password;

    public Customer(){

    }

    public Customer(String Email,String Fullname,String PhoneNumber,String Password){
        this.Email=Email;
        this.Fullname=Fullname;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
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
}
