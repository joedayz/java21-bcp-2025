package com.bcp.instanceofsample;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Product {

    private int id;
    private String name;
    private BigDecimal price;

    public void order(Product p){  // Product p = new Food() | Product p = new Drink()
        BigDecimal price = p.getPrice();
        BigDecimal discount = BigDecimal.ZERO;
        if(p instanceof Food f && f.getBestBefore().isBefore(LocalDate.now())){

            discount =  f.getBestBefore().isEqual(LocalDate.now().plusDays(1))
                    ? price.multiply(BigDecimal.valueOf(0.1))
                    : BigDecimal.ZERO;
        }

//        if(p instanceof Food f || f.getBestBefore().isBefore(LocalDate.now())){
//
//        }

        if(p instanceof Drink){
            LocalTime now = LocalTime.now();
            discount = (now.isAfter(LocalTime.of(17,30)) &&
                    now.isBefore(LocalTime.of(18,30)))
                    ? price.multiply(BigDecimal.valueOf(0.2))
                    : BigDecimal.ZERO;
        }
        price = price.subtract(discount);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return  "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
