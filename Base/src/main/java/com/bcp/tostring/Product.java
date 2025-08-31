package com.bcp.tostring;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Product {

    private Long id;
    private String name;
    private BigDecimal price;

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}


class Food extends Product {

    private LocalDate bestBefore;

    public Food(Long id, String name, BigDecimal price, LocalDate bestBefore) {
        super(id, name, price);
        this.bestBefore = bestBefore;
    }

    @Override
    public String toString() {
        return super.toString() + "Food{" +
                "bestBefore=" + bestBefore +
                '}';
    }
}


class Drink extends Product {
    public Drink(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}


class Test{
    public static void main(String[] args) {
        Product p = new Food(1L, "Test food", BigDecimal.TEN,  LocalDate.now());
        System.out.println(p);
    }
}


