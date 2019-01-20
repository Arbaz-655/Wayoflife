package com.example.mh1535547.wayoflife;

public class savedatatofirebase {
  String firstname,lastname,password,email_id,city,state,mobile_no;

    public savedatatofirebase() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }




    public savedatatofirebase(String firstname, String lastname, String password, String email_id, String city, String state, String mobile_no) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email_id = email_id;
        this.city = city;
        this.state = state;
        this.mobile_no = mobile_no;

    }
}

