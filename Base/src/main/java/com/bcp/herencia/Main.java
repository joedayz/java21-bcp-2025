package com.bcp.herencia;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Product product = new Product();
        Product p1 = new Drink();
        Product p2 = new Food();

        System.out.println("Product = "+ product);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);

        Object x1 = new Product();
        Product x2 = new Food();
        Food x3 = new Food();

        System.out.println("x1 = " + x1);
        System.out.println("x2 = " + x2);
        System.out.println("x3 = " + x3);

        p1.setPrice(new BigDecimal(100));
        p2.setPrice(new BigDecimal(200));

        product.order(p1);
        product.order(p2);

    }
}
