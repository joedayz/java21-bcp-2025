package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsExamples {
    public static void main(String[] args) {
        // Crear una lista de productos para los ejemplos
        List<Product> list = Arrays.asList(
            new Product("Laptop", BigDecimal.valueOf(1200.0)),
            new Product("Mouse", BigDecimal.valueOf(25.0)),
            new Product("Keyboard", BigDecimal.valueOf(80.0)),
            new Product("Monitor", BigDecimal.valueOf(300.0)),
            new Product("Headphones", BigDecimal.valueOf(150.0)),
            new Drink("Coffee", BigDecimal.valueOf(5.0)),
            new Drink("Tea", BigDecimal.valueOf(3.0)),
            new Food("Pizza", BigDecimal.valueOf(12.0)),
            new Food("Burger", BigDecimal.valueOf(8.0))
        );

        System.out.println("=== EJEMPLO 1: Summarizing Double Values ===");
        // Calcular estadísticas resumidas de los precios
        DoubleSummaryStatistics stats = list.stream()
            .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));
        
        System.out.println("Estadísticas de precios:");
        System.out.println("  Cantidad: " + stats.getCount());
        System.out.println("  Suma total: $" + stats.getSum());
        System.out.println("  Precio mínimo: $" + stats.getMin());
        System.out.println("  Precio máximo: $" + stats.getMax());
        System.out.println("  Precio promedio: $" + String.format("%.2f", stats.getAverage()));
        System.out.println();

        System.out.println("=== EJEMPLO 2: Mapping and Joining Strings ===");
        // Extraer nombres y unirlos en una cadena separada por comas
        String s1 = list.stream()
            .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",")));

        System.out.println("Nombres de productos (separados por comas):");
        System.out.println("  " + s1);
        System.out.println();

        // Variación: unir con un separador diferente
        String s2 = list.stream()
            .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(" | ")));

        System.out.println("Nombres de productos (separados por |):");
        System.out.println("  " + s2);
        System.out.println();

        System.out.println("=== EJEMPLO 3: Filtering and Collecting to a List ===");
        // Filtrar solo bebidas y recolectarlas en una lista
        List<Product> drinks = list.stream()
            .filter(p -> p instanceof Drink)
            .collect(Collectors.toList());

        System.out.println("Solo bebidas:");
        drinks.forEach(drink -> System.out.println("  - " + drink.getName() + ": $" + drink.getPrice()));
        System.out.println();

        // Variación: filtrar solo comidas
        List<Product> foods = list.stream()
            .filter(p -> p instanceof Food)
            .collect(Collectors.toList());

        System.out.println("Solo comidas:");
        foods.forEach(food -> System.out.println("  - " + food.getName() + ": $" + food.getPrice()));
        System.out.println();

        // Variación: filtrar productos con precio mayor a $100
        List<Product> expensiveProducts = list.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
            .collect(Collectors.toList());

        System.out.println("Productos caros (>$100):");
        expensiveProducts.forEach(product ->
            System.out.println("  - " + product.getName() + ": $" + product.getPrice()));
        System.out.println();

        System.out.println("=== EJEMPLOS ADICIONALES CON COLLECTORS ===");

        // Ejemplo 4: Agrupar por tipo de producto
        System.out.println("Agrupando por tipo de producto:");
        var groupedByType = list.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p instanceof Drink) return "Bebida";
                else if (p instanceof Food) return "Comida";
                else return "Producto";
            }));

        groupedByType.forEach((type, products) -> {
            System.out.println("  " + type + ":");
            products.forEach(p -> System.out.println("    - " + p.getName()));
        });
        System.out.println();

        // Ejemplo 5: Contar productos por tipo
        System.out.println("Contando productos por tipo:");
        var countByType = list.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p instanceof Drink) return "Bebida";
                else if (p instanceof Food) return "Comida";
                else return "Producto";
            }, Collectors.counting()));

        countByType.forEach((type, count) ->
            System.out.println("  " + type + ": " + count + " productos"));
        System.out.println();

        // Ejemplo 6: Obtener el producto más caro
        System.out.println("Producto más caro:");
        list.stream()
            .collect(Collectors.maxBy((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())))
            .ifPresent(p -> System.out.println("  " + p.getName() + ": $" + p.getPrice()));
        System.out.println();

        // Ejemplo 7: Obtener el producto más barato
        System.out.println("Producto más barato:");
        list.stream()
            .collect(Collectors.minBy((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())))
            .ifPresent(p -> System.out.println("  " + p.getName() + ": $" + p.getPrice()));
    }
} 