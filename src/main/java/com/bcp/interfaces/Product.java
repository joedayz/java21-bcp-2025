package com.bcp.interfaces;

public class Product implements Comparable<Product>{
    String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Product p) {
        return this.name.compareTo(p.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
