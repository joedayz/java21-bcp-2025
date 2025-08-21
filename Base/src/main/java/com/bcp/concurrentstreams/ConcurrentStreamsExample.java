package com.bcp.concurrentstreams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcurrentStreamsExample {
    public static void main(String[] args) {
        // Crear una lista de productos para los ejemplos
        List<Product> list = Arrays.asList(
            new Product("Laptop", BigDecimal.valueOf(1200.0), "Electrónica"),
            new Product("Mouse", BigDecimal.valueOf(25.0), "Electrónica"),
            new Product("Keyboard", BigDecimal.valueOf(80.0), "Electrónica"),
            new Product("Monitor", BigDecimal.valueOf(300.0), "Electrónica"),
            new Product("Headphones", BigDecimal.valueOf(150.0), "Electrónica"),
            new Product("Coffee", BigDecimal.valueOf(5.0), "Bebida"),
            new Product("Tea", BigDecimal.valueOf(3.0), "Bebida"),
            new Product("Juice", BigDecimal.valueOf(4.0), "Bebida"),
            new Product("Pizza", BigDecimal.valueOf(12.0), "Comida"),
            new Product("Burger", BigDecimal.valueOf(8.0), "Comida"),
            new Product("Salad", BigDecimal.valueOf(6.0), "Comida")
        );

        System.out.println("=== STREAMS PARALELOS Y CONCURRENCIA ===");
        System.out.println("Ejemplos de manejo correcto e incorrecto de recursos compartidos en modo paralelo.");
        System.out.println();

        System.out.println("Lista de productos:");
        list.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice() + " (" + p.getCategory() + ")"));
        System.out.println();

        // ========================================
        // 1. PROBLEMÁTICO: Modificar recursos compartidos con forEach
        // ========================================
        System.out.println("=== 1. PROBLEMÁTICO: Modificar recursos compartidos con forEach ===");
        System.out.println("⚠️  ADVERTENCIA: Este enfoque puede causar condiciones de carrera y resultados impredecibles.");
        System.out.println();

        // Ejemplo problemático del código de la imagen
        List<BigDecimal> prices = new ArrayList<>();
        System.out.println("Código problemático:");
        System.out.println("List<BigDecimal> prices = new ArrayList<>();");
        System.out.println("list.stream()");
        System.out.println("    .parallel()");
        System.out.println("    .peek(p->System.out.println(p))");
        System.out.println("    .map(p->p.getPrice())");
        System.out.println("    .forEach(p->prices.add(p));");
        System.out.println();

        // Demostrar el problema (ejecutar varias veces para mostrar inconsistencia)
        System.out.println("Ejecutando el código problemático 3 veces:");
        for (int i = 1; i <= 3; i++) {
            List<BigDecimal> problematicPrices = new ArrayList<>();
            list.stream()
                .parallel()
                .peek(p -> System.out.print("Procesando: " + p.getName() + " "))
                .map(p -> p.getPrice())
                .forEach(p -> problematicPrices.add(p));
            
            System.out.println("\nResultado " + i + ": " + problematicPrices.size() + " precios recolectados");
            System.out.println("Precios: " + problematicPrices);
            System.out.println();
        }

        // ========================================
        // 2. CORRECTO: Usar Collectors.toList()
        // ========================================
        System.out.println("=== 2. CORRECTO: Usar Collectors.toList() ===");
        System.out.println("✅ CORRECTO: Este enfoque es thread-safe y produce resultados consistentes.");
        System.out.println();

        // Ejemplo correcto del código de la imagen
        List<BigDecimal> correctPrices = list.stream()
            .parallel()
            .map(p -> p.getPrice())
            .collect(Collectors.toList());

        System.out.println("Código correcto:");
        System.out.println("List<BigDecimal> correctPrices = list.stream()");
        System.out.println("    .parallel()");
        System.out.println("    .map(p->p.getPrice())");
        System.out.println("    .collect(Collectors.toList());");
        System.out.println();

        System.out.println("Resultado correcto: " + correctPrices.size() + " precios recolectados");
        System.out.println("Precios: " + correctPrices);
        System.out.println();

        // ========================================
        // 3. CORRECTO: Usar Collectors.toConcurrentMap()
        // ========================================
        System.out.println("=== 3. CORRECTO: Usar Collectors.toConcurrentMap() ===");
        System.out.println("✅ CORRECTO: Este enfoque es thread-safe para crear mapas en streams paralelos.");
        System.out.println();

        // Ejemplo correcto del código de la imagen
        Map<String, BigDecimal> namesAndPrices = list.stream()
            .parallel()
            .collect(Collectors.toConcurrentMap(p -> p.getName(), p -> p.getPrice()));

        System.out.println("Código correcto:");
        System.out.println("Map<String, BigDecimal> namesAndPrices = list.stream()");
        System.out.println("    .parallel()");
        System.out.println("    .collect(Collectors.toConcurrentMap(p->p.getName(), p->p.getPrice()));");
        System.out.println();

        System.out.println("Resultado correcto: " + namesAndPrices.size() + " entradas en el mapa");
        System.out.println("Mapa nombres-precios:");
        namesAndPrices.forEach((name, price) -> 
            System.out.println("  " + name + ": $" + price));
        System.out.println();

        // ========================================
        // 4. EJEMPLOS ADICIONALES DE COLLECTORS THREAD-SAFE
        // ========================================
        System.out.println("=== 4. EJEMPLOS ADICIONALES DE COLLECTORS THREAD-SAFE ===");

        // toConcurrentMap con merge function
        System.out.println("--- toConcurrentMap con merge function ---");
        Map<String, BigDecimal> categoryPrices = list.stream()
            .parallel()
            .collect(Collectors.toConcurrentMap(
                p -> p.getCategory(),
                p -> p.getPrice(),
                (existing, replacement) -> existing.add(replacement) // Sumar precios si hay duplicados
            ));

        System.out.println("Precios totales por categoría:");
        categoryPrices.forEach((category, totalPrice) -> 
            System.out.println("  " + category + ": $" + totalPrice));
        System.out.println();

        // groupingByConcurrent
        System.out.println("--- groupingByConcurrent ---");
        Map<String, List<Product>> productsByCategory = list.stream()
            .parallel()
            .collect(Collectors.groupingByConcurrent(p -> p.getCategory()));

        System.out.println("Productos agrupados por categoría:");
        productsByCategory.forEach((category, products) -> {
            System.out.println("  " + category + ":");
            products.forEach(p -> 
                System.out.println("    - " + p.getName() + ": $" + p.getPrice()));
        });
        System.out.println();

        // counting con parallel
        System.out.println("--- counting con parallel ---");
        long totalProducts = list.stream()
            .parallel()
            .collect(Collectors.counting());

        System.out.println("Total de productos: " + totalProducts);
        System.out.println();

        // summing con parallel
        System.out.println("--- summing con parallel ---");
        double totalPrice = list.stream()
            .parallel()
            .collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));

        System.out.println("Precio total: $" + String.format("%.2f", totalPrice));
        System.out.println();

        // ========================================
        // 5. COMPARACIÓN: SECUENCIAL vs PARALELO
        // ========================================
        System.out.println("=== 5. COMPARACIÓN: SECUENCIAL vs PARALELO ===");

        // Medir tiempo de ejecución secuencial
        long startTime = System.currentTimeMillis();
        List<BigDecimal> sequentialPrices = list.stream()
            .map(p -> p.getPrice())
            .collect(Collectors.toList());
        long sequentialTime = System.currentTimeMillis() - startTime;

        // Medir tiempo de ejecución paralela
        startTime = System.currentTimeMillis();
        List<BigDecimal> parallelPrices = list.stream()
            .parallel()
            .map(p -> p.getPrice())
            .collect(Collectors.toList());
        long parallelTime = System.currentTimeMillis() - startTime;

        System.out.println("Comparación de rendimiento:");
        System.out.println("  Secuencial: " + sequentialTime + "ms");
        System.out.println("  Paralelo: " + parallelTime + "ms");
        System.out.println("  Mejora: " + String.format("%.1f", (double)sequentialTime/parallelTime) + "x");
        System.out.println();

        // ========================================
        // 6. PROBLEMAS COMUNES Y SOLUCIONES
        // ========================================
        System.out.println("=== 6. PROBLEMAS COMUNES Y SOLUCIONES ===");

        System.out.println("❌ PROBLEMAS COMUNES:");
        System.out.println("  • Usar forEach para modificar colecciones compartidas");
        System.out.println("  • Usar peek para efectos secundarios en streams paralelos");
        System.out.println("  • Modificar variables externas desde lambdas en streams paralelos");
        System.out.println("  • Usar colecciones no thread-safe en streams paralelos");
        System.out.println();

        System.out.println("✅ SOLUCIONES RECOMENDADAS:");
        System.out.println("  • Usar collect() con Collectors thread-safe");
        System.out.println("  • Usar toConcurrentMap() para mapas en streams paralelos");
        System.out.println("  • Usar groupingByConcurrent() para agrupación paralela");
        System.out.println("  • Evitar efectos secundarios en streams paralelos");
        System.out.println("  • Usar reduce() para operaciones de reducción thread-safe");
        System.out.println();

        // ========================================
        // 7. EJEMPLOS PRÁCTICOS
        // ========================================
        System.out.println("=== 7. EJEMPLOS PRÁCTICOS ===");

        // Análisis de productos por categoría con streams paralelos
        System.out.println("Análisis de productos por categoría (paralelo):");
        Map<String, Map<String, Object>> analysis = list.stream()
            .parallel()
            .collect(Collectors.groupingByConcurrent(
                p -> p.getCategory(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    products -> {
                        Map<String, Object> stats = new ConcurrentHashMap<>();
                        stats.put("count", products.size());
                        stats.put("totalPrice", products.stream()
                            .mapToDouble(p -> p.getPrice().doubleValue())
                            .sum());
                        stats.put("avgPrice", products.stream()
                            .mapToDouble(p -> p.getPrice().doubleValue())
                            .average()
                            .orElse(0.0));
                        return stats;
                    }
                )
            ));

        analysis.forEach((category, stats) -> {
            System.out.println("  " + category + ":");
            System.out.println("    - Cantidad: " + stats.get("count"));
            System.out.println("    - Precio total: $" + String.format("%.2f", (Double)stats.get("totalPrice")));
            System.out.println("    - Precio promedio: $" + String.format("%.2f", (Double)stats.get("avgPrice")));
        });
        System.out.println();

        // ========================================
        // 8. RESUMEN
        // ========================================
        System.out.println("=== RESUMEN ===");
        System.out.println("• ❌ EVITAR: forEach con colecciones compartidas en streams paralelos");
        System.out.println("• ✅ USAR: collect() con Collectors thread-safe");
        System.out.println("• ✅ USAR: toConcurrentMap() para mapas en streams paralelos");
        System.out.println("• ✅ USAR: groupingByConcurrent() para agrupación paralela");
        System.out.println("• ⚠️  CONSIDERAR: El overhead de paralelización para datasets pequeños");
        System.out.println("• 🔧 RECORDAR: Los streams paralelos pueden cambiar el orden de procesamiento");
    }
} 