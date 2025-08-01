package com.bcp.records;

import java.time.LocalDateTime;

public class RecordMain {

    public static void main(String[] args) {
        // Crear un objeto Product
        Product product = new Product("Laptop", 1500.0);

        // Crear un objeto Delivery
        Delivery delivery = new Delivery(product, LocalDateTime.now());

        // Usar instanceof con patrón deconstructivo (Java 21+)
        Object obj = delivery;
        if (obj instanceof Delivery(Product(String name, double price), var time)) {
            System.out.println(name + " " + price + " " + time);
        }
    }
}
