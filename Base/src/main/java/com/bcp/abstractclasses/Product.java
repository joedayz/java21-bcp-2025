package com.bcp.abstractclasses;

public abstract class Product {

    public abstract void serve();
}

class Food extends Product {
    @Override
    public void serve() {
        System.out.println("Food serving");
    }
}

class Drink extends Product {
    @Override
    public void serve() {
        System.out.println("Drink serving");
    }
}

class Test {

    public static void main(String[] args) {
        Product p1 = new Food();
        Product p2 = new Drink();
    }
}
