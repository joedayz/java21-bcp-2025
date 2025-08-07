package com.bcp.partitioninggrouping;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private String name;
    private BigDecimal price;
    private String category;
    private LocalDate bestBefore;

    public Product(String name, BigDecimal price, String category, LocalDate bestBefore) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.bestBefore = bestBefore;
    }

    public Product(String name, BigDecimal price, String category) {
        this(name, price, category, LocalDate.now().plusDays(30));
    }

    public Product(String name, BigDecimal price) {
        this(name, price, "General", LocalDate.now().plusDays(30));
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", bestBefore=" + bestBefore +
                '}';
    }
} 