package com.bcp.collection;

import java.util.*;

public class SequencedCollectionsExample {

    public static void main(String[] args) {
        
        // ===== SEQUENCED MAP EXAMPLE =====
        System.out.println("=== SequencedMap Example ===");
        
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        
        map.put("A", 1);
        map.put("B", 2);
        map.putFirst("C", 3); // Inserta al inicio
        
        System.out.println(map); // {C=3, A=1, B=2}
        
        SequencedMap<String, Integer> reversedMap = map.reversed();
        System.out.println(reversedMap); // {B=2, A=1, C=3}
        
        // Additional SequencedMap operations
        System.out.println("\n--- Additional SequencedMap Operations ---");
        System.out.println("First entry: " + map.firstEntry()); // C=3
        System.out.println("Last entry: " + map.lastEntry());   // B=2
        
        // Remove first and last entries
        Map.Entry<String, Integer> firstRemoved = map.pollFirstEntry();
        Map.Entry<String, Integer> lastRemoved = map.pollLastEntry();
        System.out.println("Removed first: " + firstRemoved); // C=3
        System.out.println("Removed last: " + lastRemoved);   // B=2
        System.out.println("Map after removals: " + map);     // {A=1}
        
        // ===== SEQUENCED SET EXAMPLE =====
        System.out.println("\n=== SequencedSet Example ===");
        
        SequencedSet<String> set = new LinkedHashSet<>();
        
        set.add("One");
        set.add("Two");
        set.addFirst("Zero");
        
        System.out.println(set); // [Zero, One, Two]
        
        set.removeLast(); // Elimina "Two"
        System.out.println(set); // [Zero, One]
        
        // Additional SequencedSet operations
        System.out.println("\n--- Additional SequencedSet Operations ---");
        System.out.println("First element: " + set.getFirst()); // Zero
        System.out.println("Last element: " + set.getLast());   // One
        
        set.addLast("Three");
        set.addFirst("MinusOne");
        System.out.println("After adding first and last: " + set); // [MinusOne, Zero, One, Three]
        
        // Remove first and last
        String firstRemovedElement = set.removeFirst();
        String lastRemovedElement = set.removeLast();
        System.out.println("Removed first: " + firstRemovedElement); // MinusOne
        System.out.println("Removed last: " + lastRemovedElement);   // Three
        System.out.println("Set after removals: " + set);           // [Zero, One]
        
        // Reversed view
        SequencedSet<String> reversedSet = set.reversed();
        System.out.println("Reversed set: " + reversedSet); // [One, Zero]
        
        // ===== COMPARISON WITH TRADITIONAL COLLECTIONS =====
        System.out.println("\n=== Comparison with Traditional Collections ===");
        
        // Traditional HashMap (no order guarantee)
        Map<String, Integer> traditionalMap = new HashMap<>();
        traditionalMap.put("A", 1);
        traditionalMap.put("B", 2);
        traditionalMap.put("C", 3);
        System.out.println("Traditional HashMap: " + traditionalMap); // Order not guaranteed
        
        // Traditional HashSet (no order guarantee)
        Set<String> traditionalSet = new HashSet<>();
        traditionalSet.add("One");
        traditionalSet.add("Two");
        traditionalSet.add("Three");
        System.out.println("Traditional HashSet: " + traditionalSet); // Order not guaranteed
        
        // ===== PRACTICAL EXAMPLE =====
        System.out.println("\n=== Practical Example: Shopping Cart ===");
        
        SequencedMap<String, Integer> cart = new LinkedHashMap<>();
        cart.put("Apple", 3);
        cart.put("Banana", 2);
        cart.putFirst("Milk", 1); // Most important item first
        
        System.out.println("Shopping cart: " + cart);
        
        // Add item at the end
        cart.putLast("Bread", 1);
        System.out.println("After adding bread: " + cart);
        
        // Remove first item (most important)
        Map.Entry<String, Integer> mostImportant = cart.pollFirstEntry();
        System.out.println("Removed most important: " + mostImportant);
        System.out.println("Updated cart: " + cart);
        
        // Get reversed view for checkout
        SequencedMap<String, Integer> checkoutOrder = cart.reversed();
        System.out.println("Checkout order (reversed): " + checkoutOrder);
    }
} 