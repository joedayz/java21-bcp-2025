package com.bcp.streamssamples;

import java.math.BigDecimal;

public class Drink extends Product {
    
    public Drink(String name, double price) {
        super(name, price);
    }
    
    public Drink(String name, BigDecimal price) {
        super(name, price);
    }
    
    @Override
    public String toString() {
        return "Drink{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", discount=" + getDiscount() +
                '}';
    }
} 