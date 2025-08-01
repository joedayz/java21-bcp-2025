package com.bcp.colecciones;

import java.util.*;

public class SetSamples {

    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
//        List<Product> list = new ArrayList<>();
//        list.add(p1);
//        list.add(p2);
//
//        Set<Product> productSet1 = new HashSet<>();
//        Set<Product> productSet2 = new HashSet<>(20); // capacidad inicial 20
//        Set<Product> productSet3 = new HashSet<>(20, 0.85f); // capacidad y factor de carga
//        Set<Product> productSet4 = new HashSet<>(list); // desde una lista
//        Set<Product> productSet5 = Set.of(p1, p2); // inmutable (Java 9+)
//
//        // Mostrar contenido
//        System.out.println("productSet1: " + productSet1);
//        System.out.println("productSet2: " + productSet2);
//        System.out.println("productSet3: " + productSet3);
//        System.out.println("productSet4: " + productSet4);
//        System.out.println("productSet5: " + productSet5);

        //list5.add(p2);

        Set<Product> menu = new HashSet<>();

        menu.add(p1); // insert "Cake"
        menu.add(p2); // insert "Tea"
        menu.add(p2); // duplicate, no effect
        menu.add(p3); // insert "Cookie"

        menu.remove(p1); // remove "Cake"
        menu.remove(p1); // already removed, nothing happens

        boolean hasTea = menu.contains(p2); // true

        // Mostrar estado del menú
        System.out.println("Menu: " + menu);
        System.out.println("Has Tea? " + hasTea);
    }
}
