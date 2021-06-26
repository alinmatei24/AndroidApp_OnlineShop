package com.example.myonlineshopp;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String birtDate;
    private String address;

    public User(String name, String email,  String password, String birtDate, String address){
        this.name=name;
        this.email=email;
        this.password=password;
        this.birtDate=birtDate;
        this.address=address;
    }

    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getBirtDate(){
        return this.birtDate;
    }
    public String getAddress(){
        return this.address;
    }

}
