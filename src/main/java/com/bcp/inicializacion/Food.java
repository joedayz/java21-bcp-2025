package com.bcp.inicializacion;

public class Food extends Product{

    static {
        System.out.println("Static block of Food");
    }

    {
        System.out.println("Instance initializer block of Food");
    }

    public Food() {
        System.out.println("Constructor of Food");
    }
}
