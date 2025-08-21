package com.bcp.interfaces;

import java.math.BigDecimal;

public class Drink extends Product {
    private BigDecimal price;

    public Drink(String name, BigDecimal price) {
        super(name);
        this.price = price;
    }

    public Drink(String name, double price) {
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
        return "Drink{" +
                "name='" + getName() + '\'' +
                ", price=" + price +
                '}';
    }
} 