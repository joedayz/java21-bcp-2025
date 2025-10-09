package com.bcp.comparabledemo;

public class Product  implements Comparable<Product>, Cloneable {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Sobrescribimos clone()
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}
