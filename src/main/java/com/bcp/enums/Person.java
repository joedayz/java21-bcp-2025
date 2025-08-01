package com.bcp.enums;

public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void consume(Product product) {
        System.out.println(name + " is consuming the product.");
        System.out.println("Caution: " + product.getCaution());
    }
}
