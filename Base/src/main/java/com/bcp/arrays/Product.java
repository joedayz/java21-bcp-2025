package com.bcp.arrays;

public class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void showInfo() {
        System.out.println("Producto: " + name + " | Precio: $" + price);
    }
}
