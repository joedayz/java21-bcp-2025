package com.bcp;

public class Product {

    String name;

    public Product(String name) {
        this.name = name;
    }
}


class Food extends Product {

    public Food(){
        super("Pizza");
    }
}