package com.bcp.iteracion;

import java.util.*;

public class IterationSamples {

    public static void main(String[] args) {
        // Map Iteration Example
        System.out.println("=== Map Iteration Example ===");
        Map<Product, Integer> items = new HashMap<>();
        items.put(new Food("Cake"), 2);
        items.put(new Drink("Tea"), 3);
        items.put(new Food("Pizza"), 1);
        
        Set<Product> keys = items.keySet();
        Collection<Integer> values = items.values();
        
        // Iterate over keys
        System.out.println("Iterating over keys:");
        for (Product product : keys) {
            Integer quantity = items.get(product);
            System.out.println("Product: " + product + ", Quantity: " + quantity);
            // use product and quantity objects
        }
        
        // Iterate over values
        System.out.println("\nIterating over values:");
        for (Integer quantity : values) {
            System.out.println("Quantity: " + quantity);
            // use quantity object
        }
        
        // List Iteration and Iterator for Removal Example
        System.out.println("\n=== List Iteration and Iterator Example ===");
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake"));
        menu.add(new Drink("Tea"));
        menu.add(new Food("Pizza"));
        menu.add(new Drink("Coffee"));
        
        System.out.println("Original menu: " + menu);
        
        // Enhanced for-loop (read-only iteration)
        System.out.println("\nEnhanced for-loop iteration:");
        for (Product product : menu) {
            System.out.println("Product: " + product);
            // use product object
        }
        
        // Iterator for safe removal
        System.out.println("\nIterator with removal capability:");
        System.out.println("Removing products with name 'Cake':");
        
        Iterator<Product> iter = menu.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            System.out.println("Checking: " + product);
            // use product object
            if (product.toString().equals("Cake")) {
                iter.remove(); // Safe removal during iteration
                System.out.println("Removed: " + product);
            }
        }
        
        System.out.println("Menu after removal: " + menu);
        
        // Demonstrate why Iterator is needed for removal
        System.out.println("\n=== Why Iterator is needed for removal ===");
        List<String> demoList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        System.out.println("Original list: " + demoList);
        
         //This would throw ConcurrentModificationException:
//         for (String item : demoList) {
//             if (item.equals("B")) {
//                 demoList.remove(item); // This causes exception!
//             }
//         }
        
        // Correct way using Iterator:
        Iterator<String> demoIter = demoList.iterator();
        while (demoIter.hasNext()) {
            String item = demoIter.next();
            if (item.equals("B")) {
                demoIter.remove(); // Safe removal
            }
        }
        System.out.println("List after safe removal: " + demoList);
    }
}

class Product {
    String name;

    Product(String name) {
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Food extends Product {
    Food(String name) {
        super(name);
    }
}

class Drink extends Product {
    Drink(String name) {
        super(name);
    }
} 