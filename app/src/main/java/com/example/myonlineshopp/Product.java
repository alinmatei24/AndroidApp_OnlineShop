package com.example.myonlineshopp;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private String stoc;
    private String description;

    public Product(String name, String price, String stoc, String description){
        this.name=name;
        this.price=price;
        this.stoc=stoc;
        this.description=description;
    }
    public String getName(){
        return this.name;
    }
    public String getPrice(){
        return this.price;
    }
    public String getStoc(){
        return this.stoc;
    }
    public String getDescription(){
        return this.description;
    }
}
