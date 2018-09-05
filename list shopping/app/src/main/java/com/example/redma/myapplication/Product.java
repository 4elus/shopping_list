package com.example.redma.myapplication;

public class Product {
    private String name;
    private String total;
    private String unit;

    Product(String name, String unit){
        this.name = name;
        this.total="Not Bought";
        this.unit = unit;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}