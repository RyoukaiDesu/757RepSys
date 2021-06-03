package com.example.a757repsys;

public class Users {
    private String email,password,firstName,lastName,phoneNumber,Address;
    public Users() {
    }
    public Users(String tempemail, String password){
        this.email=tempemail;
        this.password=password;
    }

    public Users(String tempemail, String password, String fname, String lname, String contact, String add ) {
        this.email=tempemail;
        this.password=password;
        this.firstName=fname;
        this.lastName=lname;
        this.phoneNumber=contact;
        this.Address=add;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return Address;
    }
}
