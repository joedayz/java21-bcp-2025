package com.bcp.arrays;

public class Food extends Product {

    private String expirationDate;


    public Food(String name, double price, String expirationDate) {
        super(name, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public void showInfo() {
        System.out.println("Comida: " + name + " | Precio: $" + price + " | Vence: " + expirationDate);
    }
}
