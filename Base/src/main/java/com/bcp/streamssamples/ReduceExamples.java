package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceExamples {
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

        System.out.println("=== EJEMPLO 1: Simple Reduction (Optional<T>) ===");
        // Reducción simple - devuelve Optional<String>
        Optional<String> x1 = list.stream()
            .map(p -> p.getName())
            .reduce((s1, s2) -> s1 + " " + s2);
        /* simple reduction */
        
        System.out.println("Reducción simple (concatenación de nombres):");
        if (x1.isPresent()) {
            System.out.println("  Resultado: " + x1.get());
        } else {
            System.out.println("  Stream vacío - no hay resultado");
        }
        System.out.println();

        System.out.println("=== EJEMPLO 2: Reduction with Initial Value (T identity) ===");
        // Reducción con valor inicial - devuelve String directamente
        String x2 = list.stream()
            .map(p -> p.getName())
            .reduce("", (s1, s2) -> s1 + " " + s2);
        /* reduction with initial(default) value */

        System.out.println("Reducción con valor inicial (concatenación de nombres):");
        System.out.println("  Resultado: " + x2);
        System.out.println();

        System.out.println("=== EJEMPLO 3: Reduction with Initial Value and Parallel Combiner ===");
        // Reducción con valor inicial y combinador paralelo
        String x3 = list.stream()
            .parallel()
            .reduce("", (s, p) -> p.getName() + " " + s, (s1, s2) -> s1 + s2);
        /* reduction with initial(default) value and a parallel combiner */

        System.out.println("Reducción paralela (concatenación de nombres):");
        System.out.println("  Resultado: " + x3);
        System.out.println();

        System.out.println("=== COMPARACIÓN DE RESULTADOS ===");
        System.out.println("Simple reduction:     " + x1.orElse("N/A"));
        System.out.println("With initial value:   " + x2);
        System.out.println("Parallel reduction:   " + x3);
        System.out.println();

        System.out.println("=== EJEMPLOS ADICIONALES DE REDUCE ===");

        // Ejemplo 4: Reducción para calcular la suma de precios
        System.out.println("=== EJEMPLO 4: Suma de Precios ===");
        BigDecimal totalPrice = list.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, (sum, price) -> sum.add(price));

        System.out.println("Suma total de precios: $" + totalPrice);
        System.out.println();

        // Ejemplo 5: Reducción para encontrar el producto más caro
        System.out.println("=== EJEMPLO 5: Producto Más Caro ===");
        Optional<Product> mostExpensive = list.stream()
            .reduce((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()) > 0 ? p1 : p2);

        mostExpensive.ifPresent(p ->
            System.out.println("Producto más caro: " + p.getName() + " - $" + p.getPrice()));
        System.out.println();

        // Ejemplo 6: Reducción para contar productos por tipo
        System.out.println("=== EJEMPLO 6: Conteo de Productos ===");
        int totalProducts = list.stream()
            .map(p -> 1)
            .reduce(0, (count, one) -> count + one);

        System.out.println("Total de productos: " + totalProducts);
        System.out.println();

        // Ejemplo 7: Reducción para crear una lista de nombres en mayúsculas
        System.out.println("=== EJEMPLO 7: Lista de Nombres en Mayúsculas ===");
        String upperCaseNames = list.stream()
            .map(p -> p.getName().toUpperCase())
            .reduce("", (result, name) -> result + (result.isEmpty() ? "" : ", ") + name);

        System.out.println("Nombres en mayúsculas: " + upperCaseNames);
        System.out.println();

        // Ejemplo 8: Reducción paralela para suma de precios
        System.out.println("=== EJEMPLO 8: Suma Paralela de Precios ===");
        BigDecimal parallelTotal = list.stream()
            .parallel()
            .reduce(BigDecimal.ZERO,
                (sum, product) -> sum.add(product.getPrice()),
                BigDecimal::add);

        System.out.println("Suma paralela de precios: $" + parallelTotal);
        System.out.println();

        // Ejemplo 9: Reducción con filtrado
        System.out.println("=== EJEMPLO 9: Suma de Productos Caros ===");
        BigDecimal expensiveTotal = list.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Suma de productos caros (>$100): $" + expensiveTotal);
        System.out.println();

        // Ejemplo 10: Reducción para crear un resumen
        System.out.println("=== EJEMPLO 10: Resumen de Productos ===");
        String summary = list.stream()
            .reduce("",
                (result, product) -> result +
                    (result.isEmpty() ? "" : "\n") +
                    "- " + product.getName() + ": $" + product.getPrice(),
                (s1, s2) -> s1 + "\n" + s2);

        System.out.println("Resumen de productos:");
        System.out.println(summary);
        System.out.println();

        System.out.println("=== EXPLICACIÓN DE LAS TRES VARIANTES DE REDUCE ===");
        System.out.println("1. reduce(BinaryOperator<T> accumulator):");
        System.out.println("   - Devuelve Optional<T>");
        System.out.println("   - No tiene valor inicial");
        System.out.println("   - Útil cuando el stream puede estar vacío");
        System.out.println();

        System.out.println("2. reduce(T identity, BinaryOperator<T> accumulator):");
        System.out.println("   - Devuelve T directamente");
        System.out.println("   - Tiene valor inicial (identity)");
        System.out.println("   - Más simple para operaciones con valor por defecto");
        System.out.println();

        System.out.println("3. reduce(U identity, BiFunction<U,T,U> accumulator, BinaryOperator<U> combiner):");
        System.out.println("   - Devuelve U (puede ser diferente de T)");
        System.out.println("   - Diseñado para streams paralelos");
        System.out.println("   - El combiner combina resultados de procesamiento paralelo");
        System.out.println();

        System.out.println("=== CASOS DE USO COMUNES ===");
        System.out.println("• Suma de valores: reduce(0, (sum, value) -> sum + value)");
        System.out.println("• Concatenación: reduce(\"\", (result, item) -> result + item)");
        System.out.println("• Máximo/Mínimo: reduce((a, b) -> a > b ? a : b)");
        System.out.println("• Conteo: reduce(0, (count, item) -> count + 1)");
        System.out.println("• Agregación compleja: reduce(initial, accumulator, combiner)");
    }
} 