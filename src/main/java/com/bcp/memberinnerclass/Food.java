package com.bcp.memberinnerclass;

/**
 * Clase Food que extiende Product
 */
public class Food extends Product {
    
    public Food(String name) {
        super(name, 8.00); // Precio base para comidas
    }
    
    public Food(String name, double price) {
        super(name, price);
    }
    
    @Override
    public String toString() {
        return "Food{name='" + getName() + "', price=$" + getPrice() + "}";
    }
} 