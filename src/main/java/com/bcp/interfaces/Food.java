package com.bcp.interfaces;

import java.math.BigDecimal;

public class Food extends Product {
    private BigDecimal price;

    public Food(String name, BigDecimal price) {
        super(name);
        this.price = price;
    }

    public Food(String name, double price) {
        super(name);
        this.price = BigDecimal.valueOf(price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + getName() + '\'' +
                ", price=" + price +
                '}';
    }
} 