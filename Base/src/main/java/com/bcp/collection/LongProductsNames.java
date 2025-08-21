package com.bcp.collection;

import java.util.*;
import java.util.function.Predicate;

// Use removeIf(Predicate<I> p) method to remove all matching elements from the collection.

// Predicate implementation
public class LongProductsNames implements Predicate<Product> {
    
    @Override
    public boolean test(Product product) {
        return product.getName().length() > 3;
    }
}

class RemoveIfExample {
    
    public static void main(String[] args) {
        
        // List usage with removeIf
        List<Product> menu = new ArrayList<>();
        
        menu.add(new Food("Cake"));      // length 4
        menu.add(new Drink("Tea"));      // length 3
        menu.add(new Food("Cookie"));    // length 6
        
        System.out.println("Original menu: " + menu);
        
        // Convert to array (extraneous to removeIf core example but present)
        Product[] array = new Product[2];
        array = menu.toArray(array);
        System.out.println("Array from list: " + Arrays.toString(array));
        // Note: Product array is re-created to accommodate extra elements.
        
        // Core removeIf example
        menu.removeIf(new LongProductsNames());
        System.out.println("Menu after removeIf: " + menu);
        // Method removeIf removes all products except tea.
        
        // ===== ADDITIONAL EXAMPLES =====
        System.out.println("\n=== Additional removeIf Examples ===");
        
        // Example 1: Using lambda expression
        List<Product> menu2 = new ArrayList<>();
        menu2.add(new Food("Pizza"));
        menu2.add(new Drink("Coffee"));
        menu2.add(new Food("Salad"));
        menu2.add(new Drink("Water"));
        
        System.out.println("Menu 2 before: " + menu2);
        menu2.removeIf(product -> product.getName().length() > 5);
        System.out.println("Menu 2 after removing long names: " + menu2);
        
        // Example 2: Remove all Food items
        List<Product> menu3 = new ArrayList<>();
        menu3.add(new Food("Burger"));
        menu3.add(new Drink("Soda"));
        menu3.add(new Food("Fries"));
        menu3.add(new Drink("Juice"));
        
        System.out.println("Menu 3 before: " + menu3);
        menu3.removeIf(product -> product instanceof Food);
        System.out.println("Menu 3 after removing Food items: " + menu3);
        
        // Example 3: Remove items starting with specific letter
        List<Product> menu4 = new ArrayList<>();
        menu4.add(new Food("Apple"));
        menu4.add(new Drink("Beer"));
        menu4.add(new Food("Carrot"));
        menu4.add(new Drink("Dew"));
        
        System.out.println("Menu 4 before: " + menu4);
        menu4.removeIf(product -> product.getName().startsWith("A"));
        System.out.println("Menu 4 after removing items starting with 'A': " + menu4);
        
        // Example 4: Complex predicate
        List<Product> menu5 = new ArrayList<>();
        menu5.add(new Food("ExpensiveSteak"));
        menu5.add(new Drink("CheapWater"));
        menu5.add(new Food("MediumPizza"));
        menu5.add(new Drink("PremiumWine"));
        
        System.out.println("Menu 5 before: " + menu5);
        menu5.removeIf(product -> 
            product.getName().length() > 8 || 
            product.getName().contains("Expensive") ||
            product.getName().contains("Premium")
        );
        System.out.println("Menu 5 after complex removal: " + menu5);
    }
}

class Product {
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