package com.bcp.streamssamples;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private double discount;

    public Product(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.discount = 0.0;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.discount = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
} 