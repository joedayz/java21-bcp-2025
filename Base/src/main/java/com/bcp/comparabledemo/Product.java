package com.bcp.comparabledemo;

public class Product  implements Comparable<Product> {
    private String name;

    public Product(String name) {
        this.name = name;
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
