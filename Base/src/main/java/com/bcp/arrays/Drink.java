package com.bcp.arrays;

public class Drink extends Product{
    private boolean isAlcoholic;

    public Drink(String name, double price, boolean isAlcoholic) {
        super(name, price);
        this.isAlcoholic = isAlcoholic;
    }

    @Override
    public void showInfo() {
        System.out.println("Bebida: " + name + " | Precio: $" + price + " | Alcoholica: " + (isAlcoholic ? "Sí" : "No"));
    }
}
