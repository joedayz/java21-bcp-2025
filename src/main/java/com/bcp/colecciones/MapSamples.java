package com.bcp.colecciones;

import java.util.*;

public class MapSamples {

    public static void main(String[] args) {
        // Previous example - commented out
        /*
        // Four different ways to initialize Map<Product, Integer> objects
        Map<Product, Integer> items1 = new HashMap<>();
        Map<Product, Integer> items2 = new HashMap<>(20);
        Map<Product, Integer> items3 = new HashMap<>(items1);
        Map<Product, Integer> items4 = Map.of(new Food("Cake"), Integer.valueOf(2),
                                              new Drink("Tea"), Integer.valueOf(3));
        
        System.out.println("Map initialization examples:");
        System.out.println("items1 (empty HashMap): " + items1);
        System.out.println("items2 (HashMap with initial capacity 20): " + items2);
        System.out.println("items3 (HashMap from items1): " + items3);
        System.out.println("items4 (immutable Map.of): " + items4);
        */
        
        // HashMap operations example
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        
        Map<Product, Integer> items = new HashMap<>();
        
        items.put(p1, Integer.valueOf(2));                    // insert first element
        items.put(p2, Integer.valueOf(2));                    // insert second element
        Integer n1 = items.put(p1, Integer.valueOf(5));       // update element, returns previous value
        Integer n2 = items.remove(p2);                        // remove element, returns removed value
        boolean hasTea = items.containsKey(p2);               // check if key exists
        boolean hasTwo = items.containsValue(n1);             // check if value exists
        Integer quantity = items.get(p1);                     // get value by key
        
        System.out.println("HashMap operations example:");
        System.out.println("Previous value of p1 (n1): " + n1);
        System.out.println("Removed value of p2 (n2): " + n2);
        System.out.println("Contains Tea key: " + hasTea);
        System.out.println("Contains value 2: " + hasTwo);
        System.out.println("Quantity of Cake: " + quantity);
        System.out.println("Final map: " + items);
    }
}


