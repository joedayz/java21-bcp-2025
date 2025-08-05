package com.bcp.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleLambdaExample {
    
    public static void main(String[] args) {
        // Crear lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        
        System.out.println("Lista original:");
        products.forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
        
        // CONTEXTO DE INVOCACIÓN
        // List<Product> products = ...;
        // Collections.sort(products, <Comparator>);
        
        System.out.println("\n--- CLASE ANÓNIMA ---");
        List<Product> productsAnon = new ArrayList<>(products);
        Collections.sort(productsAnon, new java.util.Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
        productsAnon.forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
        
        System.out.println("\n--- LAMBDA EXPRESIÓN ---");
        List<Product> productsLambda = new ArrayList<>(products);
        Collections.sort(productsLambda, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        productsLambda.forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
        
        System.out.println("\n--- LAMBDA MÁS SIMPLE ---");
        List<Product> productsSimple = new ArrayList<>(products);
        productsSimple.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        productsSimple.forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
    }
} 