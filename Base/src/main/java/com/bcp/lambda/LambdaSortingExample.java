package com.bcp.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaSortingExample {
    
    public static void main(String[] args) {
        // Crear lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        
        System.out.println("=== LISTA ORIGINAL ===");
        products.forEach(System.out::println);
        
        // Ejemplo 1: Ordenamiento con clase anónima (Java tradicional)
        System.out.println("\n=== ORDENAMIENTO CON CLASE ANÓNIMA ===");
        List<Product> products1 = new ArrayList<>(products);
        Collections.sort(products1, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
        products1.forEach(System.out::println);
        
        // Ejemplo 2: Ordenamiento con lambda expression (Java 8+)
        System.out.println("\n=== ORDENAMIENTO CON LAMBDA EXPRESIÓN ===");
        List<Product> products2 = new ArrayList<>(products);
        Collections.sort(products2, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        products2.forEach(System.out::println);
        
        // Ejemplo 3: Ordenamiento con lambda y método de referencia
        System.out.println("\n=== ORDENAMIENTO CON LAMBDA Y MÉTODO DE REFERENCIA ===");
        List<Product> products3 = new ArrayList<>(products);
        Collections.sort(products3, Comparator.comparing(Product::getPrice));
        products3.forEach(System.out::println);

        // Ejemplo 4: Ordenamiento descendente con lambda
        System.out.println("\n=== ORDENAMIENTO DESCENDENTE CON LAMBDA ===");
        List<Product> products4 = new ArrayList<>(products);
        Collections.sort(products4, (p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));
        products4.forEach(System.out::println);

        // Ejemplo 5: Ordenamiento por nombre con lambda
        System.out.println("\n=== ORDENAMIENTO POR NOMBRE CON LAMBDA ===");
        List<Product> products5 = new ArrayList<>(products);
        Collections.sort(products5, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        products5.forEach(System.out::println);

        // Ejemplo 6: Usando stream() con lambda (más moderno)
        System.out.println("\n=== ORDENAMIENTO CON STREAM Y LAMBDA ===");
        products.stream()
               .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
               .forEach(System.out::println);
    }
} 