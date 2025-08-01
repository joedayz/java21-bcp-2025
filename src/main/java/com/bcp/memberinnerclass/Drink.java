package com.bcp.memberinnerclass;

/**
 * Clase Drink que extiende Product
 */
public class Drink extends Product {
    
    public Drink(String name) {
        super(name, 3.50); // Precio base para bebidas
    }
    
    public Drink(String name, double price) {
        super(name, price);
    }
    
    @Override
    public String toString() {
        return "Drink{name='" + getName() + "', price=$" + getPrice() + "}";
    }
} 