package com.bcp.streamssamples;

import java.math.BigDecimal;

public class Food extends Product {
    
    public Food(String name, double price) {
        super(name, price);
    }
    
    public Food(String name, BigDecimal price) {
        super(name, price);
    }
    
    @Override
    public String toString() {
        return "Food{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", discount=" + getDiscount() +
                '}';
    }
} 