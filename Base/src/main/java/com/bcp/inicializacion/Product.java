package com.bcp.inicializacion;

public class Product extends Object{

    static {
        System.out.println("Static block of Product");
    }

    {
        System.out.println("Instance initializer block of Product");
    }

    public Product() {
        System.out.println("Constructor of Product");
    }
}
