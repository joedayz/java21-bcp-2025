package com.bcp.collectionutils;

import java.util.*;

public class CollectionUtils {

    public static void main(String[] args) {
        
        // Product initialization
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
        
        // List creation and population
        List<Product> menu = new ArrayList<>();
        menu.add(p1);
        menu.add(p2);
        menu.add(p3);
        
        System.out.println("=== Collections Utility Methods Demo ===");
        System.out.println("Original menu: " + menu);
        
        // ===== SORT =====
        System.out.println("\n--- Collections.sort() ---");
        System.out.println("Before sort: " + menu);
        Collections.sort(menu);
        System.out.println("After sort: " + menu);
        
        // ===== REVERSE =====
        System.out.println("\n--- Collections.reverse() ---");
        System.out.println("Before reverse: " + menu);
        Collections.reverse(menu);
        System.out.println("After reverse: " + menu);
        // reverse method changes the order to opposite
        
        // ===== SHUFFLE =====
        System.out.println("\n--- Collections.shuffle() ---");
        System.out.println("Before shuffle: " + menu);
        Collections.shuffle(menu);
        System.out.println("After shuffle: " + menu);
        // shuffle method randomly reorders collection
        
        // ===== BINARY SEARCH =====
        System.out.println("\n--- Collections.binarySearch() ---");
        // Note: binarySearch requires sorted list, but we just shuffled it!
        System.out.println("Current menu (shuffled): " + menu);
        System.out.println("Searching for: " + p2);
        
        // First, let's sort it for proper binary search
        Collections.sort(menu);
        System.out.println("After sorting for binary search: " + menu);
        
        int index = Collections.binarySearch(menu, p2);
        if (index >= 0) {
            System.out.println("Found " + p2 + " at index: " + index);
        } else {
            System.out.println(p2 + " not found. Should be inserted at index: " + (-index - 1));
        }
        
        // ===== FILL =====
        System.out.println("\n--- Collections.fill() ---");
        System.out.println("Before fill: " + menu);
        Collections.fill(menu, new Food("Pie"));
        System.out.println("After fill: " + menu);
        
        // ===== ADDITIONAL COLLECTIONS METHODS =====
        System.out.println("\n=== Additional Collections Methods ===");
        
        // Create a new list for additional examples
        List<Product> menu2 = new ArrayList<>();
        menu2.add(new Food("Pizza"));
        menu2.add(new Drink("Coffee"));
        menu2.add(new Food("Salad"));
        menu2.add(new Drink("Water"));
        
        System.out.println("New menu: " + menu2);
        
//        // MIN and MAX
//        System.out.println("\n--- Collections.min() and Collections.max() ---");
//        Product minProduct = Collections.min(menu2);
//        Product maxProduct = Collections.max(menu2);
//        System.out.println("Minimum: " + minProduct);
//        System.out.println("Maximum: " + maxProduct);
//
//        // FREQUENCY
//        System.out.println("\n--- Collections.frequency() ---");
//        menu2.add(new Food("Pizza")); // Add duplicate
//        menu2.add(new Food("Pizza")); // Add another duplicate
//        System.out.println("Menu with duplicates: " + menu2);
//        int pizzaCount = Collections.frequency(menu2, new Food("Pizza"));
//        System.out.println("Frequency of Pizza: " + pizzaCount);
//
//        // DISJOINT
//        System.out.println("\n--- Collections.disjoint() ---");
//        List<Product> menu3 = new ArrayList<>();
//        menu3.add(new Drink("Beer"));
//        menu3.add(new Food("Burger"));
//
//        boolean areDisjoint = Collections.disjoint(menu2, menu3);
//        System.out.println("Menu2: " + menu2);
//        System.out.println("Menu3: " + menu3);
//        System.out.println("Are disjoint: " + areDisjoint);
//
//        // SWAP
//        System.out.println("\n--- Collections.swap() ---");
//        System.out.println("Before swap: " + menu2);
//        Collections.swap(menu2, 0, 2);
//        System.out.println("After swap(0, 2): " + menu2);
//
//        // ROTATE
//        System.out.println("\n--- Collections.rotate() ---");
//        System.out.println("Before rotate: " + menu2);
//        Collections.rotate(menu2, 1);
//        System.out.println("After rotate(1): " + menu2);
//
//        // ===== VISUAL REPRESENTATION =====
//        System.out.println("\n=== Visual Representation ===");
//
//        // Reverse example visualization
//        List<String> visualList = new ArrayList<>(Arrays.asList("Tea", "Cookie"));
//        System.out.println("Reverse Example:");
//        System.out.println("Before: " + visualList);
//        Collections.reverse(visualList);
//        System.out.println("After:  " + visualList);
//
//        // Fill example visualization
//        List<String> fillList = new ArrayList<>(Arrays.asList("Item1", "Item2", "Item3"));
//        System.out.println("\nFill Example:");
//        System.out.println("Before: " + fillList);
//        Collections.fill(fillList, "Pie");
//        System.out.println("After:  " + fillList);
    }
}

class Product implements Comparable<Product> {
    String name;

    Product(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
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