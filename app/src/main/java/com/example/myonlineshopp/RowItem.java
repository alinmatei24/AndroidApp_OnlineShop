package com.example.myonlineshopp;

public class RowItem {
    private String name;
    private String price;
    private int img;

    public void setName(String name){
        this.name=name;
    }
    public void setPrice(String price){
        this.price=price;
    }
    public void setImg(int img){
        this.img=img;
    }

    public String getName(){
        return this.name;
    }
    public String getPrice(){
        return this.price;
    }
    public int getImg(){
        return this.img;
    }
}
