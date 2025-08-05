package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamOperationsExample {
    
    public static void main(String[] args) {
        // Crear lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 15.99));
        products.add(new Product("Tablet", 599.99));
        products.add(new Product("Webcam", 45.99));
        products.add(new Product("Speaker", 75.99));
        
        System.out.println("=== OPERACIONES AVANZADAS CON STREAMS ===");
        System.out.println("Lista original:");
        products.forEach(System.out::println);
        
        // Ejemplo 1: filter - Filtrar productos por precio
        System.out.println("\n=== FILTER ===");
        System.out.println("Productos con precio > $50:");
        products.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)
            .forEach(System.out::println);
        
        // Ejemplo 2: map - Transformar productos
        System.out.println("\n=== MAP ===");
        System.out.println("Nombres de productos en mayúsculas:");
        products.stream()
            .map(p -> p.getName().toUpperCase())
            .forEach(System.out::println);

        // Ejemplo 3: map con transformación compleja
        System.out.println("\n=== MAP CON TRANSFORMACIÓN COMPLEJA ===");
        System.out.println("Productos con descuento del 20%:");
        products.stream()
                .map(p -> {
                    p.setDiscount(0.2);
                    return p;
                })
                .forEach(System.out::println);

        // Ejemplo 4: sorted - Ordenar productos
        System.out.println("\n=== SORTED ===");
        System.out.println("Productos ordenados por precio (ascendente):");
        products.stream()
                .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                .forEach(System.out::println);

        // Ejemplo 5: limit - Limitar resultados
        System.out.println("\n=== LIMIT ===");
        System.out.println("Top 3 productos más caros:");
        products.stream()
                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice())) // Orden descendente
                .limit(3)
                .forEach(System.out::println);

        // Ejemplo 6: skip - Saltar elementos
        System.out.println("\n=== SKIP ===");
        System.out.println("Productos saltando los primeros 2:");
        products.stream()
                .skip(2)
                .forEach(System.out::println);

        // Ejemplo 7: distinct - Elementos únicos
        System.out.println("\n=== DISTINCT ===");
        System.out.println("Precios únicos:");
        products.stream()
                .map(Product::getPrice)
                .distinct()
                .forEach(System.out::println);

        // Ejemplo 8: reduce - Reducir a un valor
        System.out.println("\n=== REDUCE ===");
        BigDecimal totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Precio total: $" + totalPrice);

        // Ejemplo 9: collect - Recolectar resultados
        System.out.println("\n=== COLLECT ===");
        List<String> productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println("Lista de nombres: " + productNames);

        // Ejemplo 10: collect con groupingBy
        System.out.println("\n=== COLLECT CON GROUPINGBY ===");
        Map<BigDecimal, List<Product>> productsByPrice = products.stream()
                .collect(Collectors.groupingBy(Product::getPrice));

        productsByPrice.forEach((price, productList) -> {
            System.out.println("Precio $" + price + ":");
            productList.forEach(p -> System.out.println("  - " + p.getName()));
        });

        // Ejemplo 11: anyMatch, allMatch, noneMatch
        System.out.println("\n=== MATCHING OPERATIONS ===");
        boolean hasExpensiveProduct = products.stream()
                .anyMatch(p -> p.getPrice().compareTo(BigDecimal.valueOf(500)) > 0);
        System.out.println("¿Hay productos caros (>$500)? " + hasExpensiveProduct);

        boolean allProductsExpensive = products.stream()
                .allMatch(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0);
        System.out.println("¿Todos los productos cuestan más de $10? " + allProductsExpensive);

        boolean noFreeProducts = products.stream()
                .noneMatch(p -> p.getPrice().compareTo(BigDecimal.ZERO) == 0);
        System.out.println("¿No hay productos gratis? " + noFreeProducts);

        // Ejemplo 12: findFirst, findAny
        System.out.println("\n=== FINDING OPERATIONS ===");
        Product firstExpensiveProduct = products.stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .findFirst()
                .orElse(null);
        System.out.println("Primer producto caro (>$100): " + firstExpensiveProduct);

        // Ejemplo 13: Pipeline complejo
        System.out.println("\n=== PIPELINE COMPLEJO ===");
        System.out.println("Productos caros (>$50) ordenados por precio, con descuento del 15%:");
        products.stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)
                .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                .map(p -> {
                    p.setDiscount(0.15);
                    return p;
                })
                .forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice() +
                        " (Descuento: " + (p.getDiscount() * 100) + "%)"));
    }
} 