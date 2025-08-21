package com.bcp.collectorsamples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class BasicCollectorsExample {
    public static void main(String[] args) {
        // Crear una lista de productos para los ejemplos
        List<Product> list = Arrays.asList(
            new Product("Laptop", BigDecimal.valueOf(1200.0), "Electrónica"),
            new Product("Mouse", BigDecimal.valueOf(25.0), "Electrónica"),
            new Product("Keyboard", BigDecimal.valueOf(80.0), "Electrónica"),
            new Product("Monitor", BigDecimal.valueOf(300.0), "Electrónica"),
            new Product("Headphones", BigDecimal.valueOf(150.0), "Electrónica"),
            new Drink("Coffee", BigDecimal.valueOf(5.0)),
            new Drink("Tea", BigDecimal.valueOf(3.0)),
            new Drink("Juice", BigDecimal.valueOf(4.0)),
            new Food("Pizza", BigDecimal.valueOf(12.0)),
            new Food("Burger", BigDecimal.valueOf(8.0)),
            new Food("Salad", BigDecimal.valueOf(6.0))
        );

        System.out.println("=== COLECCIONES BÁSICAS CON COLLECTORS ===");
        System.out.println("Lista de productos:");
        list.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (" + p.getCategory() + ")"));
        System.out.println();

        // ========================================
        // 1. CALCULATING SUMMARY VALUES
        // ========================================
        System.out.println("=== 1. CALCULATING SUMMARY VALUES ===");
        System.out.println("Calculating summary values such as average, min, max, count, sum.");
        
        DoubleSummaryStatistics stats = list.stream()
            .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));
        
        System.out.println("Estadísticas de precios:");
        System.out.println("  • Cantidad de productos: " + stats.getCount());
        System.out.println("  • Suma total: $" + stats.getSum());
        System.out.println("  • Precio mínimo: $" + stats.getMin());
        System.out.println("  • Precio máximo: $" + stats.getMax());
        System.out.println("  • Precio promedio: $" + String.format("%.2f", stats.getAverage()));
        System.out.println();

        // Ejemplos adicionales de summary values
        System.out.println("=== EJEMPLOS ADICIONALES DE SUMMARY VALUES ===");
        
        // Contar productos por categoría
        long electronicCount = list.stream()
            .filter(p -> "Electrónica".equals(p.getCategory()))
            .count();
        System.out.println("Productos de electrónica: " + electronicCount);

        // Suma de precios de bebidas
        double drinksTotal = list.stream()
            .filter(p -> p instanceof Drink)
            .mapToDouble(p -> p.getPrice().doubleValue())
            .sum();
        System.out.println("Suma de precios de bebidas: $" + drinksTotal);

        // Precio promedio de comidas
        double foodAverage = list.stream()
            .filter(p -> p instanceof Food)
            .mapToDouble(p -> p.getPrice().doubleValue())
            .average()
            .orElse(0.0);
        System.out.println("Precio promedio de comidas: $" + String.format("%.2f", foodAverage));
        System.out.println();

        // ========================================
        // 2. MAPPING AND JOINING STREAM ELEMENTS
        // ========================================
        System.out.println("=== 2. MAPPING AND JOINING STREAM ELEMENTS ===");
        System.out.println("Mapping and joining stream elements.");
        
        String s1 = list.stream()
            .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",")));
        
        System.out.println("Nombres de productos (separados por comas):");
        System.out.println("  " + s1);
        System.out.println();

        // Ejemplos adicionales de mapping y joining
        System.out.println("=== EJEMPLOS ADICIONALES DE MAPPING AND JOINING ===");
        
        // Nombres separados por pipes
        String namesWithPipes = list.stream()
            .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(" | ")));
        System.out.println("Nombres con pipes: " + namesWithPipes);

        // Nombres en mayúsculas separados por espacios
        String upperCaseNames = list.stream()
            .collect(Collectors.mapping(p -> p.getName().toUpperCase(), Collectors.joining(" ")));
        System.out.println("Nombres en mayúsculas: " + upperCaseNames);

        // Nombres con precios
        String namesWithPrices = list.stream()
            .collect(Collectors.mapping(p -> p.getName() + "($" + p.getPrice() + ")", Collectors.joining(", ")));
        System.out.println("Nombres con precios: " + namesWithPrices);

        // Nombres por categoría
        String namesByCategory = list.stream()
            .collect(Collectors.mapping(p -> p.getCategory() + ": " + p.getName(), Collectors.joining("; ")));
        System.out.println("Nombres por categoría: " + namesByCategory);
        System.out.println();

        // ========================================
        // 3. GATHERING STREAM ELEMENTS INTO A COLLECTION
        // ========================================
        System.out.println("=== 3. GATHERING STREAM ELEMENTS INTO A COLLECTION ===");
        System.out.println("Gathering stream elements into a collection such as list, set, or map.");
        
        List<Product> drinks = list.stream()
            .filter(p -> p instanceof Drink)
            .collect(Collectors.toList());
        
        System.out.println("Bebidas recolectadas en lista:");
        drinks.forEach(drink -> System.out.println("  - " + drink.getName() + ": $" + drink.getPrice()));
        System.out.println();

        // Ejemplos adicionales de gathering
        System.out.println("=== EJEMPLOS ADICIONALES DE GATHERING ===");
        
        // Lista de comidas
        List<Product> foods = list.stream()
            .filter(p -> p instanceof Food)
            .collect(Collectors.toList());
        System.out.println("Comidas recolectadas en lista:");
        foods.forEach(food -> System.out.println("  - " + food.getName() + ": $" + food.getPrice()));

        // Lista de productos caros (>$100)
        List<Product> expensiveProducts = list.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
            .collect(Collectors.toList());
        System.out.println("Productos caros (>$100):");
        expensiveProducts.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice()));

        // Lista de productos de electrónica
        List<Product> electronics = list.stream()
            .filter(p -> "Electrónica".equals(p.getCategory()))
            .collect(Collectors.toList());
        System.out.println("Productos de electrónica:");
        electronics.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        System.out.println();

        // ========================================
        // EJEMPLOS AVANZADOS DE COLLECTORS
        // ========================================
        System.out.println("=== EJEMPLOS AVANZADOS DE COLLECTORS ===");
        
        // Agrupar por categoría
        System.out.println("Agrupando productos por categoría:");
        var groupedByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        
        groupedByCategory.forEach((category, products) -> {
            System.out.println("  " + category + ":");
            products.forEach(p -> System.out.println("    - " + p.getName() + ": $" + p.getPrice()));
        });
        System.out.println();

        // Contar productos por categoría
        System.out.println("Contando productos por categoría:");
        var countByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        
        countByCategory.forEach((category, count) -> 
            System.out.println("  " + category + ": " + count + " productos"));
        System.out.println();

        // Suma de precios por categoría
        System.out.println("Suma de precios por categoría:");
        var sumByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory, 
                Collectors.summingDouble(p -> p.getPrice().doubleValue())));
        
        sumByCategory.forEach((category, sum) -> 
            System.out.println("  " + category + ": $" + String.format("%.2f", sum)));
        System.out.println();

        // Producto más caro por categoría
        System.out.println("Producto más caro por categoría:");
        var maxByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.maxBy((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))));
        
        maxByCategory.forEach((category, product) -> 
            product.ifPresent(p -> System.out.println("  " + category + ": " + p.getName() + " - $" + p.getPrice())));
        System.out.println();

        // ========================================
        // RESUMEN DE LOS TRES TIPOS BÁSICOS
        // ========================================
        System.out.println("=== RESUMEN DE LOS TRES TIPOS BÁSICOS DE COLLECTORS ===");
        System.out.println("1. SUMMARY VALUES:");
        System.out.println("   • summarizingDouble() - Estadísticas completas");
        System.out.println("   • counting() - Conteo de elementos");
        System.out.println("   • summingDouble() - Suma de valores");
        System.out.println("   • averagingDouble() - Promedio de valores");
        System.out.println("   • maxBy() / minBy() - Valores extremos");
        System.out.println();
        
        System.out.println("2. MAPPING AND JOINING:");
        System.out.println("   • mapping() + joining() - Transformar y unir");
        System.out.println("   • joining() - Unir elementos en cadena");
        System.out.println("   • joining(delimiter) - Con separador personalizado");
        System.out.println("   • joining(delimiter, prefix, suffix) - Con prefijo y sufijo");
        System.out.println();
        
        System.out.println("3. GATHERING INTO COLLECTIONS:");
        System.out.println("   • toList() - Recolectar en List");
        System.out.println("   • toSet() - Recolectar en Set");
        System.out.println("   • toMap() - Recolectar en Map");
        System.out.println("   • toCollection() - Recolectar en colección específica");
        System.out.println();
        
        System.out.println("=== CASOS DE USO COMUNES ===");
        System.out.println("• Análisis de datos: summarizingDouble() para estadísticas");
        System.out.println("• Generación de reportes: mapping() + joining() para texto");
        System.out.println("• Filtrado y agrupación: toList() + groupingBy() para organización");
        System.out.println("• Agregación: summingDouble() + groupingBy() para totales por categoría");
    }
} 