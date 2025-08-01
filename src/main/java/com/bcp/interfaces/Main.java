package com.bcp.interfaces;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Product[] products = {
                new Product("Tea"),
                new Product("Coffee"),
                new Product("Cake")
        };

        Arrays.sort(products, new ProductNameSorter());

        for (Product p : products) {
            System.out.println(p);
        }
    }
}
