package com.bcp.concurrentcollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SpecializedCollectionsExample {

    public static void main(String[] args) {
        
        // ===== UNMODIFIABLE SET EXAMPLE =====
        System.out.println("=== Unmodifiable Set Example ===");
        
        Set<Product> originalSet = new HashSet<>();
        originalSet.add(new Food("Cookie"));
        originalSet.add(new Drink("Coffee"));
        originalSet.add(new Food("Pizza"));
        
        Set<Product> readOnlySet = Collections.unmodifiableSet(originalSet);
        
        System.out.println("Original set: " + originalSet);
        System.out.println("Read-only set: " + readOnlySet);
        
        // Try to modify the read-only set (will throw exception)
        try {
            readOnlySet.add(new Food("Cake")); // This will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify unmodifiable set: " + e.getMessage());
        }
        
        // Original set can still be modified
        originalSet.add(new Food("Cake"));
        System.out.println("Original set after modification: " + originalSet);
        System.out.println("Read-only set reflects changes: " + readOnlySet);
        
        // ===== SYNCHRONIZED MAP EXAMPLE =====
        System.out.println("\n=== Synchronized Map Example ===");
        
        Map<Product, Integer> originalMap = new HashMap<>();
        originalMap.put(new Food("Cookie"), 5);
        originalMap.put(new Drink("Coffee"), 3);
        originalMap.put(new Food("Pizza"), 2);
        
        Map<Product, Integer> syncMap = Collections.synchronizedMap(originalMap);
        
        System.out.println("Original map: " + originalMap);
        System.out.println("Synchronized map: " + syncMap);
        
        // Demonstrate thread safety with synchronized map
        System.out.println("\n--- Thread Safety Demo with Synchronized Map ---");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Thread 1: Add items
        executor.submit(() -> {
            for (int i = 0; i < 5; i++) {
                syncMap.put(new Food("Thread1_Item_" + i), i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Thread 2: Remove items
        executor.submit(() -> {
            for (int i = 0; i < 3; i++) {
                Iterator<Map.Entry<Product, Integer>> iterator = syncMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Product, Integer> entry = iterator.next();
                    if (entry.getValue() == i) {
                        iterator.remove();
                        break;
                    }
                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Thread 3: Read items
        executor.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 3 reading: " + syncMap.size() + " items");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        executor.shutdown();
        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Final synchronized map: " + syncMap);
        
        // ===== COPY-ON-WRITE LIST EXAMPLE =====
        System.out.println("\n=== Copy-On-Write List Example ===");
        
        List<Product> originalList = new ArrayList<>();
        originalList.add(new Food("Cookie"));
        originalList.add(new Drink("Coffee"));
        originalList.add(new Food("Pizza"));
        
        List<Product> copyOnWriteList = new CopyOnWriteArrayList<>(originalList);
        
        System.out.println("Original list: " + originalList);
        System.out.println("Copy-on-write list: " + copyOnWriteList);
        
        // Demonstrate thread safety with copy-on-write list
        System.out.println("\n--- Thread Safety Demo with Copy-On-Write List ---");
        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        
        // Thread 1: Add items
        executor2.submit(() -> {
            for (int i = 0; i < 5; i++) {
                copyOnWriteList.add(new Food("Thread1_Item_" + i));
                System.out.println("Thread 1 added item, size: " + copyOnWriteList.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Thread 2: Remove items
        executor2.submit(() -> {
            for (int i = 0; i < 3; i++) {
                if (!copyOnWriteList.isEmpty()) {
                    Product removed = copyOnWriteList.remove(0);
                    System.out.println("Thread 2 removed: " + removed);
                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Thread 3: Iterate over list (safe with CopyOnWriteArrayList)
        executor2.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 3 iteration " + i + ":");
                for (Product product : copyOnWriteList) {
                    System.out.println("  - " + product);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        executor2.shutdown();
        try {
            executor2.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Final copy-on-write list: " + copyOnWriteList);
        
        // ===== COMPARISON OF THREAD SAFETY =====
        System.out.println("\n=== Thread Safety Comparison ===");
        
        // Regular ArrayList (not thread-safe)
        List<String> regularList = new ArrayList<>();
        regularList.add("A");
        regularList.add("B");
        regularList.add("C");
        
        // CopyOnWriteArrayList (thread-safe)
        List<String> cowList = new CopyOnWriteArrayList<>(regularList);
        
        System.out.println("Regular ArrayList: " + regularList);
        System.out.println("CopyOnWriteArrayList: " + cowList);
        
        // Demonstrate the difference in iteration safety
        System.out.println("\n--- Iteration Safety Demo ---");
        
        // This would cause ConcurrentModificationException with regular ArrayList
        // if modified during iteration in a multi-threaded environment
        
        // CopyOnWriteArrayList allows safe iteration even during modifications
        for (String item : cowList) {
            System.out.println("Reading: " + item);
            cowList.add("New_" + item); // Safe to modify during iteration
        }
        
        System.out.println("After safe iteration: " + cowList);
        
        // ===== PERFORMANCE CONSIDERATIONS =====
        System.out.println("\n=== Performance Considerations ===");
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            syncMap.put(new Food("Item_" + i), i);
        }
        long syncTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            copyOnWriteList.add(new Food("Item_" + i));
        }
        long cowTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Synchronized Map time: " + syncTime + "ms");
        System.out.println("Copy-On-Write List time: " + cowTime + "ms");
        System.out.println("Note: Copy-On-Write is slower for writes but faster for reads");
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