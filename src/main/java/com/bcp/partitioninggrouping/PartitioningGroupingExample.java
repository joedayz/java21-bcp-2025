package com.bcp.partitioninggrouping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningGroupingExample {
    public static void main(String[] args) {
        // Crear fechas para los ejemplos
        LocalDate date1 = LocalDate.of(2019, 3, 7);
        LocalDate date2 = LocalDate.of(2019, 3, 8);
        LocalDate date3 = LocalDate.of(2019, 3, 14);

        // Crear una lista de productos para los ejemplos
        List<Product> list = Arrays.asList(
            new Product("Laptop", BigDecimal.valueOf(1200.0), "Electrónica", date1),
            new Product("Mouse", BigDecimal.valueOf(25.0), "Electrónica", date1),
            new Product("Keyboard", BigDecimal.valueOf(80.0), "Electrónica", date3),
            new Product("Monitor", BigDecimal.valueOf(300.0), "Electrónica", date3),
            new Product("Headphones", BigDecimal.valueOf(150.0), "Electrónica", date2),
            new Drink("Coffee", BigDecimal.valueOf(5.0), date1),
            new Drink("Tea", BigDecimal.valueOf(3.0), date2),
            new Drink("Juice", BigDecimal.valueOf(4.0), date3),
            new Food("Pizza", BigDecimal.valueOf(12.0), date1),
            new Food("Burger", BigDecimal.valueOf(8.0), date2),
            new Food("Salad", BigDecimal.valueOf(6.0), date3)
        );

        System.out.println("=== PARTITIONING Y GROUPING CON COLLECTORS ===");
        System.out.println("Class Collectors provides Collector objects to subdivide stream elements into partitions or groups.");
        System.out.println();

        System.out.println("Lista de productos:");
        list.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice() + 
            " (" + p.getCategory() + ") - Vence: " + p.getBestBefore()));
        System.out.println();

        // ========================================
        // 1. PARTITIONING
        // ========================================
        System.out.println("=== 1. PARTITIONING ===");
        System.out.println("Partitioning divides content into a map with two key values (boolean true/false) using Predicate.");
        System.out.println();

        // Ejemplo del código de la imagen
        Map<Boolean, List<Product>> productTypes = list.stream()
            .collect(Collectors.partitioningBy(p -> p instanceof Drink));

        System.out.println("Productos divididos por tipo (Bebidas vs No Bebidas):");
        System.out.println("TRUE (Bebidas):");
        productTypes.get(true).forEach(p -> 
            System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (Vence: " + p.getBestBefore() + ")"));
        
        System.out.println("FALSE (No Bebidas):");
        productTypes.get(false).forEach(p -> 
            System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (Vence: " + p.getBestBefore() + ")"));
        System.out.println();

        // Ejemplos adicionales de partitioning
        System.out.println("=== EJEMPLOS ADICIONALES DE PARTITIONING ===");

        // Partitioning por precio (caros vs baratos)
        Map<Boolean, List<Product>> byPrice = list.stream()
            .collect(Collectors.partitioningBy(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0));

        System.out.println("Productos divididos por precio (>$100):");
        System.out.println("TRUE (Caros >$100):");
        byPrice.get(true).forEach(p ->
            System.out.println("  - " + p.getName() + ": $" + p.getPrice()));

        System.out.println("FALSE (Baratos ≤$100):");
        byPrice.get(false).forEach(p ->
            System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        System.out.println();

        // Partitioning por fecha de vencimiento (próximos a vencer)
        LocalDate today = LocalDate.now();
        Map<Boolean, List<Product>> byExpiration = list.stream()
            .collect(Collectors.partitioningBy(p -> p.getBestBefore().isBefore(today.plusDays(7))));

        System.out.println("Productos divididos por vencimiento (próximos a vencer):");
        System.out.println("TRUE (Próximos a vencer):");
        byExpiration.get(true).forEach(p ->
            System.out.println("  - " + p.getName() + " (Vence: " + p.getBestBefore() + ")"));

        System.out.println("FALSE (No próximos a vencer):");
        byExpiration.get(false).forEach(p ->
            System.out.println("  - " + p.getName() + " (Vence: " + p.getBestBefore() + ")"));
        System.out.println();

        // Partitioning con conteo
        Map<Boolean, Long> countByType = list.stream()
            .collect(Collectors.partitioningBy(p -> p instanceof Drink, Collectors.counting()));

        System.out.println("Conteo de productos por tipo:");
        System.out.println("Bebidas: " + countByType.get(true) + " productos");
        System.out.println("No Bebidas: " + countByType.get(false) + " productos");
        System.out.println();

        // ========================================
        // 2. GROUPING
        // ========================================
        System.out.println("=== 2. GROUPING ===");
        System.out.println("Grouping divides content into a map of multiple key values using Function.");
        System.out.println();

        // Ejemplo del código de la imagen
        Map<LocalDate, List<Product>> productGroups = list.stream()
            .collect(Collectors.groupingBy(p -> p.getBestBefore()));

        System.out.println("Productos agrupados por fecha de vencimiento:");
        productGroups.forEach((date, products) -> {
            System.out.println("Fecha: " + date);
            products.forEach(p ->
                System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (" + p.getCategory() + ")"));
        });
        System.out.println();

        // Ejemplos adicionales de grouping
        System.out.println("=== EJEMPLOS ADICIONALES DE GROUPING ===");

        // Grouping por categoría
        Map<String, List<Product>> byCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Productos agrupados por categoría:");
        byCategory.forEach((category, products) -> {
            System.out.println("Categoría: " + category);
            products.forEach(p ->
                System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (Vence: " + p.getBestBefore() + ")"));
        });
        System.out.println();

        // Grouping por mes de vencimiento
        Map<String, List<Product>> byMonth = list.stream()
            .collect(Collectors.groupingBy(p -> p.getBestBefore().getMonth().toString()));

        System.out.println("Productos agrupados por mes de vencimiento:");
        byMonth.forEach((month, products) -> {
            System.out.println("Mes: " + month);
            products.forEach(p ->
                System.out.println("  - " + p.getName() + " (Vence: " + p.getBestBefore() + ")"));
        });
        System.out.println();

        // Grouping con conteo
        Map<String, Long> countByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

        System.out.println("Conteo de productos por categoría:");
        countByCategory.forEach((category, count) ->
            System.out.println("  " + category + ": " + count + " productos"));
        System.out.println();

        // Grouping con suma de precios
        Map<String, Double> sumByCategory = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.summingDouble(p -> p.getPrice().doubleValue())));

        System.out.println("Suma de precios por categoría:");
        sumByCategory.forEach((category, sum) ->
            System.out.println("  " + category + ": $" + String.format("%.2f", sum)));
        System.out.println();

        // Grouping con múltiples collectors
        Map<String, Map<LocalDate, List<Product>>> byCategoryAndDate = list.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.groupingBy(Product::getBestBefore)));

        System.out.println("Productos agrupados por categoría y fecha:");
        byCategoryAndDate.forEach((category, dateMap) -> {
            System.out.println("Categoría: " + category);
            dateMap.forEach((date, products) -> {
                System.out.println("  Fecha: " + date);
                products.forEach(p ->
                    System.out.println("    - " + p.getName() + ": $" + p.getPrice()));
            });
        });
        System.out.println();

        // ========================================
        // COMPARACIÓN: PARTITIONING vs GROUPING
        // ========================================
        System.out.println("=== COMPARACIÓN: PARTITIONING vs GROUPING ===");

        System.out.println("PARTITIONING:");
        System.out.println("  • Siempre divide en 2 grupos (TRUE/FALSE)");
        System.out.println("  • Usa Predicate (boolean function)");
        System.out.println("  • Resultado: Map<Boolean, List<T>>");
        System.out.println("  • Útil para clasificaciones binarias");
        System.out.println();

        System.out.println("GROUPING:");
        System.out.println("  • Divide en múltiples grupos");
        System.out.println("  • Usa Function (puede devolver cualquier tipo)");
        System.out.println("  • Resultado: Map<K, List<T>>");
        System.out.println("  • Útil para clasificaciones múltiples");
        System.out.println();

        // ========================================
        // EJEMPLOS PRÁCTICOS
        // ========================================
        System.out.println("=== EJEMPLOS PRÁCTICOS ===");

        // Análisis de inventario
        System.out.println("Análisis de inventario:");

        // Productos por rango de precio
        Map<String, List<Product>> byPriceRange = list.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p.getPrice().compareTo(BigDecimal.valueOf(50)) <= 0) return "Económico";
                else if (p.getPrice().compareTo(BigDecimal.valueOf(200)) <= 0) return "Medio";
                else return "Premium";
            }));

        byPriceRange.forEach((range, products) -> {
            System.out.println("Rango " + range + ":");
            products.forEach(p ->
                System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        });
        System.out.println();

        // Productos que necesitan atención (próximos a vencer o caros)
        Map<Boolean, List<Product>> needsAttention = list.stream()
            .collect(Collectors.partitioningBy(p ->
                p.getBestBefore().isBefore(today.plusDays(7)) ||
                p.getPrice().compareTo(BigDecimal.valueOf(500)) > 0));

        System.out.println("Productos que necesitan atención:");
        System.out.println("TRUE (Necesitan atención):");
        needsAttention.get(true).forEach(p ->
            System.out.println("  - " + p.getName() + " (Vence: " + p.getBestBefore() + ", Precio: $" + p.getPrice() + ")"));

        System.out.println("FALSE (No necesitan atención):");
        needsAttention.get(false).forEach(p ->
            System.out.println("  - " + p.getName() + " (Vence: " + p.getBestBefore() + ", Precio: $" + p.getPrice() + ")"));
        System.out.println();

        // ========================================
        // RESUMEN
        // ========================================
        System.out.println("=== RESUMEN ===");
        System.out.println("• PARTITIONING: Para clasificaciones binarias (sí/no, verdadero/falso)");
        System.out.println("• GROUPING: Para clasificaciones múltiples (categorías, fechas, rangos)");
        System.out.println("• Ambos pueden combinarse con otros collectors (counting, summing, etc.)");
        System.out.println("• Útiles para análisis de datos y reportes");
        System.out.println("• Permiten procesamiento eficiente de grandes volúmenes de datos");
    }
} 