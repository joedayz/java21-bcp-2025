package com.bcp.concurrentstreams;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoreParallelStreamExamples {
    
    public static void main(String[] args) {
        System.out.println("=== MÁS EJEMPLOS DE STREAMS PARALELOS ===");
        System.out.println("Ejemplos adicionales y casos de uso prácticos de parallel streams\n");
        
        // Crear una lista grande de productos para demostrar beneficios del paralelismo
        List<Product> products = createLargeProductList();
        System.out.println("Total de productos: " + products.size());
        System.out.println();
        
        // ========================================
        // 1. FILTRADO Y TRANSFORMACIÓN PARALELA
        // ========================================
        System.out.println("=== 1. FILTRADO Y TRANSFORMACIÓN PARALELA ===");
        
        // Filtrar productos caros y transformar en paralelo
        List<String> expensiveProductNames = products.stream()
            .parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
            .map(Product::getName)
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println("Productos caros (>$100) en mayúsculas: " + expensiveProductNames.size() + " productos");
        System.out.println("Primeros 5: " + expensiveProductNames.stream().limit(5).collect(Collectors.toList()));
        System.out.println();
        
        // ========================================
        // 2. CÁLCULOS ESTADÍSTICOS PARALELOS
        // ========================================
        System.out.println("=== 2. CÁLCULOS ESTADÍSTICOS PARALELOS ===");
        
        // Calcular estadísticas en paralelo
        DoubleSummaryStatistics stats = products.stream()
            .parallel()
            .mapToDouble(p -> p.getPrice().doubleValue())
            .summaryStatistics();
        
        System.out.println("Estadísticas de precios (paralelo):");
        System.out.println("  • Cantidad: " + stats.getCount());
        System.out.println("  • Suma: $" + String.format("%.2f", stats.getSum()));
        System.out.println("  • Mínimo: $" + String.format("%.2f", stats.getMin()));
        System.out.println("  • Máximo: $" + String.format("%.2f", stats.getMax()));
        System.out.println("  • Promedio: $" + String.format("%.2f", stats.getAverage()));
        System.out.println();
        
        // ========================================
        // 3. AGRUPACIÓN PARALELA CON MÚLTIPLES COLECTORES
        // ========================================
        System.out.println("=== 3. AGRUPACIÓN PARALELA CON MÚLTIPLES COLECTORES ===");
        
        // Agrupar por categoría y calcular estadísticas
        Map<String, DoubleSummaryStatistics> statsByCategory = products.stream()
            .parallel()
            .collect(Collectors.groupingByConcurrent(
                Product::getCategory,
                Collectors.summarizingDouble(p -> p.getPrice().doubleValue())
            ));
        
        System.out.println("Estadísticas por categoría:");
        statsByCategory.forEach((category, categoryStats) -> {
            System.out.println("  " + category + ":");
            System.out.println("    - Cantidad: " + categoryStats.getCount());
            System.out.println("    - Promedio: $" + String.format("%.2f", categoryStats.getAverage()));
            System.out.println("    - Total: $" + String.format("%.2f", categoryStats.getSum()));
        });
        System.out.println();
        
        // ========================================
        // 4. REDUCCIÓN PARALELA CON OPERACIONES COMPLEJAS
        // ========================================
        System.out.println("=== 4. REDUCCIÓN PARALELA CON OPERACIONES COMPLEJAS ===");
        
        // Encontrar el producto más caro por categoría en paralelo
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .parallel()
            .collect(Collectors.groupingByConcurrent(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparing(Product::getPrice))
            ));
        
        System.out.println("Producto más caro por categoría:");
        mostExpensiveByCategory.forEach((category, product) -> 
            product.ifPresent(p -> 
                System.out.println("  " + category + ": " + p.getName() + " - $" + p.getPrice())
            )
        );
        System.out.println();
        
        // ========================================
        // 5. TRANSFORMACIÓN Y CONCATENACIÓN PARALELA
        // ========================================
        System.out.println("=== 5. TRANSFORMACIÓN Y CONCATENACIÓN PARALELA ===");
        
        // Crear un reporte concatenado de productos caros
        String report = products.stream()
            .parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)
            .sorted(Comparator.comparing(Product::getPrice).reversed())
            .map(p -> p.getName() + ": $" + p.getPrice() + " (" + p.getCategory() + ")")
            .collect(Collectors.joining("\n"));
        
        System.out.println("Reporte de productos caros (>$50):");
        System.out.println("Total de líneas: " + report.split("\n").length);
        System.out.println("Primeras 3 líneas:");
        Arrays.stream(report.split("\n"))
            .limit(3)
            .forEach(line -> System.out.println("  " + line));
        System.out.println();
        
        // ========================================
        // 6. OPERACIONES CON INTSTREAM PARALELO
        // ========================================
        System.out.println("=== 6. OPERACIONES CON INTSTREAM PARALELO ===");
        
        // Calcular suma de números grandes en paralelo
        int sum = IntStream.range(1, 1_000_000)
            .parallel()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .sum();
        
        System.out.println("Suma de cuadrados de números pares (1-1,000,000): " + sum);
        System.out.println();
        
        // Calcular promedio en paralelo
        OptionalDouble avgResult = IntStream.range(1, 100_000)
            .parallel()
            .filter(n -> n % 3 == 0)
            .average();
        
        avgResult.ifPresent(a -> System.out.println("Promedio de múltiplos de 3 (1-100,000): " + String.format("%.2f", a)));
        System.out.println();
        
        // ========================================
        // 7. PARTITIONING PARALELO
        // ========================================
        System.out.println("=== 7. PARTITIONING PARALELO ===");
        
        // Dividir productos en caros y baratos en paralelo
        Map<Boolean, List<Product>> partitioned = products.stream()
            .parallel()
            .collect(Collectors.partitioningBy(
                p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0
            ));
        
        System.out.println("Productos divididos por precio:");
        System.out.println("  Caros (>$50): " + partitioned.get(true).size() + " productos");
        System.out.println("  Baratos (≤$50): " + partitioned.get(false).size() + " productos");
        System.out.println();
        
        // Partitioning con conteo
        Map<Boolean, Long> countPartitioned = products.stream()
            .parallel()
            .collect(Collectors.partitioningBy(
                p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0,
                Collectors.counting()
            ));
        
        System.out.println("Conteo por partición:");
        System.out.println("  Caros: " + countPartitioned.get(true) + " productos");
        System.out.println("  Baratos: " + countPartitioned.get(false) + " productos");
        System.out.println();
        
        // ========================================
        // 8. AGRUPACIÓN ANIDADA PARALELA
        // ========================================
        System.out.println("=== 8. AGRUPACIÓN ANIDADA PARALELA ===");
        
        // Agrupar por categoría y luego por rango de precio
        var nestedGrouping = products.stream()
            .parallel()
            .collect(Collectors.groupingByConcurrent(
                Product::getCategory,
                Collectors.groupingByConcurrent(p -> {
                    BigDecimal price = p.getPrice();
                    if (price.compareTo(BigDecimal.valueOf(25)) <= 0) return "Económico";
                    else if (price.compareTo(BigDecimal.valueOf(100)) <= 0) return "Medio";
                    else return "Premium";
                })
            ));
        
        System.out.println("Agrupación anidada (Categoría -> Rango de precio):");
        nestedGrouping.forEach((category, priceRanges) -> {
            System.out.println("  " + category + ":");
            priceRanges.forEach((range, prods) -> 
                System.out.println("    " + range + ": " + prods.size() + " productos")
            );
        });
        System.out.println();
        
        // ========================================
        // 9. COMPARACIÓN DE RENDIMIENTO: SECUENCIAL vs PARALELO
        // ========================================
        System.out.println("=== 9. COMPARACIÓN DE RENDIMIENTO: SECUENCIAL vs PARALELO ===");
        
        // Operación costosa: filtrar, transformar y ordenar
        long startTime = System.currentTimeMillis();
        List<String> sequentialResult = products.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)
            .map(p -> p.getName().toUpperCase())
            .sorted()
            .collect(Collectors.toList());
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<String> parallelResult = products.stream()
            .parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)
            .map(p -> p.getName().toUpperCase())
            .sorted()
            .collect(Collectors.toList());
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Operación: Filtrar, transformar y ordenar " + products.size() + " productos");
        System.out.println("  Secuencial: " + sequentialTime + "ms");
        System.out.println("  Paralelo: " + parallelTime + "ms");
        if (sequentialTime > 0) {
            double speedup = (double) sequentialTime / parallelTime;
            System.out.println("  Mejora: " + String.format("%.2f", speedup) + "x");
        }
        System.out.println("  Resultados iguales: " + sequentialResult.equals(parallelResult));
        System.out.println();
        
        // ========================================
        // 10. STREAM PARALELO CON OPERACIONES DE I/O SIMULADAS
        // ========================================
        System.out.println("=== 10. STREAM PARALELO CON OPERACIONES COSTOSAS ===");
        
        // Simular operaciones costosas (como llamadas a API o cálculos complejos)
        List<String> processedNames = products.stream()
            .parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(30)) > 0)
            .map(p -> {
                // Simular procesamiento costoso
                try {
                    Thread.sleep(1); // Simular I/O o cálculo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return processProductName(p);
            })
            .collect(Collectors.toList());
        
        System.out.println("Productos procesados (simulando operaciones costosas): " + processedNames.size());
        System.out.println("Primeros 3: " + processedNames.stream().limit(3).collect(Collectors.toList()));
        System.out.println();
        
        // ========================================
        // 11. COMBINACIÓN DE MÚLTIPLES OPERACIONES PARALELAS
        // ========================================
        System.out.println("=== 11. COMBINACIÓN DE MÚLTIPLES OPERACIONES PARALELAS ===");
        
        // Pipeline complejo con múltiples transformaciones
        Map<String, Double> categoryAverages = products.stream()
            .parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)
            .collect(Collectors.groupingByConcurrent(
                Product::getCategory,
                Collectors.averagingDouble(p -> p.getPrice().doubleValue())
            ));
        
        System.out.println("Promedio de precios por categoría (productos >$10):");
        categoryAverages.forEach((category, avg) -> 
            System.out.println("  " + category + ": $" + String.format("%.2f", avg))
        );
        System.out.println();
        
        // ========================================
        // 12. STREAM PARALELO CON DISTINCT Y LIMIT
        // ========================================
        System.out.println("=== 12. STREAM PARALELO CON DISTINCT Y LIMIT ===");
        
        // Obtener categorías únicas y limitar resultados
        List<String> uniqueCategories = products.stream()
            .parallel()
            .map(Product::getCategory)
            .distinct()
            .sorted()
            .limit(5)
            .collect(Collectors.toList());
        
        System.out.println("Categorías únicas (limitadas a 5): " + uniqueCategories);
        System.out.println();
        
        // ========================================
        // RESUMEN Y MEJORES PRÁCTICAS
        // ========================================
        System.out.println("=== RESUMEN Y MEJORES PRÁCTICAS ===");
        System.out.println("✅ CUÁNDO USAR STREAMS PARALELOS:");
        System.out.println("  • Datasets grandes (>10,000 elementos)");
        System.out.println("  • Operaciones costosas (I/O, cálculos complejos)");
        System.out.println("  • Múltiples núcleos disponibles");
        System.out.println("  • Operaciones independientes sin estado compartido");
        System.out.println();
        System.out.println("✅ COLLECTORS THREAD-SAFE PARA PARALELO:");
        System.out.println("  • toList(), toSet() - Thread-safe automáticamente");
        System.out.println("  • toConcurrentMap() - Para mapas en paralelo");
        System.out.println("  • groupingByConcurrent() - Para agrupación paralela");
        System.out.println("  • partitioningBy() - Thread-safe automáticamente");
        System.out.println();
        System.out.println("❌ EVITAR EN STREAMS PARALELOS:");
        System.out.println("  • forEach con colecciones no thread-safe");
        System.out.println("  • Modificar variables externas desde lambdas");
        System.out.println("  • Operaciones que dependen del orden");
        System.out.println("  • Efectos secundarios no controlados");
    }
    
    // Método auxiliar para crear una lista grande de productos
    private static List<Product> createLargeProductList() {
        List<Product> products = new ArrayList<>();
        String[] categories = {"Electrónica", "Bebida", "Comida", "Ropa", "Hogar", "Deportes"};
        String[] names = {"Laptop", "Mouse", "Keyboard", "Monitor", "Headphones", 
                         "Coffee", "Tea", "Juice", "Pizza", "Burger", "Salad",
                         "Shirt", "Pants", "Shoes", "Table", "Chair", "Lamp",
                         "Ball", "Racket", "Bike"};
        
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String name = names[random.nextInt(names.length)] + " " + (i + 1);
            BigDecimal price = BigDecimal.valueOf(5 + random.nextDouble() * 500);
            String category = categories[random.nextInt(categories.length)];
            products.add(new Product(name, price, category));
        }
        return products;
    }
    
    // Método auxiliar para simular procesamiento costoso
    private static String processProductName(Product p) {
        return p.getName().toUpperCase() + " [PROCESADO] - $" + p.getPrice();
    }
}

