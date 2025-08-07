package com.bcp.spliteratorexamples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorExamples {
    public static void main(String[] args) {
        System.out.println("=== SPLITERATOR: CÓMO FUNCIONAN LOS MÉTODOS ===");
        System.out.println("Spliterator es un iterador especializado para procesamiento paralelo.");
        System.out.println();

        // ========================================
        // 1. EJEMPLO BÁSICO: Crear un Spliterator
        // ========================================
        System.out.println("=== 1. CREAR UN SPLITERATOR ===");
        
        // Crear una lista de productos
        List<Product> products = Arrays.asList(
            new Product("Laptop", BigDecimal.valueOf(1200.0), "Electrónica"),
            new Product("Mouse", BigDecimal.valueOf(25.0), "Electrónica"),
            new Product("Keyboard", BigDecimal.valueOf(80.0), "Electrónica"),
            new Product("Monitor", BigDecimal.valueOf(300.0), "Electrónica"),
            new Product("Headphones", BigDecimal.valueOf(150.0), "Electrónica"),
            new Product("Coffee", BigDecimal.valueOf(5.0), "Bebida"),
            new Product("Tea", BigDecimal.valueOf(3.0), "Bebida"),
            new Product("Pizza", BigDecimal.valueOf(12.0), "Comida")
        );

        System.out.println("Lista original:");
        products.forEach(p -> System.out.println("  - " + p.getName() + ": $" + p.getPrice()));
        System.out.println();

        // Crear un Spliterator de la lista
        Spliterator<Product> spliterator = products.spliterator();
        System.out.println("Spliterator creado con " + spliterator.estimateSize() + " elementos");
        System.out.println();

        // ========================================
        // 2. MÉTODO tryAdvance() - PASO A PASO
        // ========================================
        System.out.println("=== 2. MÉTODO tryAdvance() - PASO A PASO ===");
        System.out.println("tryAdvance() es una alternativa a hasNext() + next() del Iterator");
        System.out.println("Devuelve true si hay un elemento, false si no hay más elementos");
        System.out.println();

        // Crear un nuevo Spliterator para el ejemplo
        Spliterator<Product> s1 = products.spliterator();

        System.out.println("Estado inicial del Spliterator:");
        System.out.println("  - Elementos restantes: " + s1.estimateSize());
        System.out.println();

        // Usar tryAdvance para procesar elementos uno por uno
        System.out.println("Procesando elementos con tryAdvance():");

        int count = 0;
        while (s1.tryAdvance(product -> {
            System.out.println("  Procesando: " + product.getName() + " (Precio: $" + product.getPrice() + ")");
        })) {
            count++;
            System.out.println("  ✅ tryAdvance() devolvió true - Elemento procesado");
            System.out.println("  - Elementos restantes: " + s1.estimateSize());
            System.out.println();
        }

        System.out.println("❌ tryAdvance() devolvió false - No hay más elementos");
        System.out.println("Total de elementos procesados: " + count);
        System.out.println();

        // ========================================
        // 3. MÉTODO trySplit() - DIVIDIR EL SPLITERATOR
        // ========================================
        System.out.println("=== 3. MÉTODO trySplit() - DIVIDIR EL SPLITERATOR ===");
        System.out.println("trySplit() divide el Spliterator en dos partes para procesamiento paralelo");
        System.out.println();

        // Crear un nuevo Spliterator para dividir
        Spliterator<Product> originalSpliterator = products.spliterator();

        System.out.println("Spliterator original:");
        System.out.println("  - Elementos totales: " + originalSpliterator.estimateSize());
        System.out.println();

        // Procesar el primer elemento
        System.out.println("1. Procesando el primer elemento:");
        originalSpliterator.tryAdvance(product ->
            System.out.println("  Procesado: " + product.getName()));
        System.out.println("  - Elementos restantes: " + originalSpliterator.estimateSize());
        System.out.println();

        // Intentar dividir el Spliterator
        System.out.println("2. Intentando dividir el Spliterator:");
        Spliterator<Product> s2 = originalSpliterator.trySplit();

        if (s2 == null) {
            System.out.println("  ❌ No se pudo dividir (s2 es null)");
            System.out.println("  - Elementos restantes en original: " + originalSpliterator.estimateSize());
        } else {
            System.out.println("  ✅ División exitosa");
            System.out.println("  - Elementos en s2 (nuevo): " + s2.estimateSize());
            System.out.println("  - Elementos en original: " + originalSpliterator.estimateSize());
            System.out.println();

            // Procesar elementos restantes de ambos Spliterators
            System.out.println("3. Procesando elementos restantes:");

            System.out.println("  Elementos del Spliterator original:");
            originalSpliterator.forEachRemaining(product ->
                System.out.println("    - " + product.getName() + ": $" + product.getPrice()));

            System.out.println("  Elementos del Spliterator dividido (s2):");
            s2.forEachRemaining(product ->
                System.out.println("    - " + product.getName() + ": $" + product.getPrice()));
        }
        System.out.println();

        // ========================================
        // 4. MÉTODO forEachRemaining() - PROCESAR TODO LO RESTANTE
        // ========================================
        System.out.println("=== 4. MÉTODO forEachRemaining() - PROCESAR TODO LO RESTANTE ===");
        System.out.println("forEachRemaining() es una alternativa a todo el bucle Iterator");
        System.out.println("Procesa todos los elementos restantes de una vez");
        System.out.println();

        // Crear un nuevo Spliterator
        Spliterator<Product> s3 = products.spliterator();

        System.out.println("Spliterator con " + s3.estimateSize() + " elementos");

        // Procesar algunos elementos con tryAdvance
        System.out.println("Procesando los primeros 3 elementos con tryAdvance():");
        for (int i = 0; i < 3; i++) {
            s3.tryAdvance(product ->
                System.out.println("  Procesado: " + product.getName()));
        }
        System.out.println("  - Elementos restantes: " + s3.estimateSize());
        System.out.println();

        // Procesar todos los elementos restantes con forEachRemaining
        System.out.println("Procesando todos los elementos restantes con forEachRemaining():");
        s3.forEachRemaining(product ->
            System.out.println("  Procesado: " + product.getName() + " (Categoría: " + product.getCategory() + ")"));

        System.out.println("  - Elementos restantes: " + s3.estimateSize());
        System.out.println();

        // ========================================
        // 5. EJEMPLO COMPLETO: COMO EN LA IMAGEN
        // ========================================
        System.out.println("=== 5. EJEMPLO COMPLETO: COMO EN LA IMAGEN ===");
        System.out.println("Este es el ejemplo exacto que viste en la imagen:");
        System.out.println();

        // Crear un Spliterator de números aleatorios (como en la imagen)
        Spliterator<Integer> s1_example = new java.util.Random().ints(10, 0, 10).spliterator();

        System.out.println("Código de la imagen:");
        System.out.println("Spliterator<Integer> s1 = new Random().ints(10,0,10).spliterator();");
        System.out.println("s1.tryAdvance(v->System.out.print(v));");
        System.out.println("Spliterator s2 = s1.trySplit();");
        System.out.println("if (s2 == null) {");
        System.out.println("    System.out.println(\"Did not split\");");
        System.out.println("} else {");
        System.out.println("    s1.forEachRemaining(v->System.out.print(v));");
        System.out.println("    s2.forEachRemaining(v->System.out.print(v));");
        System.out.println("}");
        System.out.println();

        System.out.println("Ejecutando el ejemplo:");

        // Paso 1: Procesar el primer elemento
        System.out.print("1. tryAdvance(): ");
        s1_example.tryAdvance(v -> System.out.print(v));
        System.out.println();

        // Paso 2: Intentar dividir
        System.out.print("2. trySplit(): ");
        Spliterator<Integer> s2_example = s1_example.trySplit();

        if (s2_example == null) {
            System.out.println("Did not split");
        } else {
            System.out.println("Split successful");
            System.out.print("3. s1.forEachRemaining(): ");
            s1_example.forEachRemaining(v -> System.out.print(v + " "));
            System.out.println();
            System.out.print("4. s2.forEachRemaining(): ");
            s2_example.forEachRemaining(v -> System.out.print(v + " "));
            System.out.println();
        }
        System.out.println();

        // ========================================
        // 6. EXPLICACIÓN DETALLADA DE LOS MÉTODOS
        // ========================================
        System.out.println("=== 6. EXPLICACIÓN DETALLADA DE LOS MÉTODOS ===");

        System.out.println("🔍 MÉTODO tryAdvance(Consumer<T> action):");
        System.out.println("  • ¿Qué hace? Intenta procesar el siguiente elemento disponible");
        System.out.println("  • ¿Cuándo devuelve true? Si hay un elemento y lo procesa");
        System.out.println("  • ¿Cuándo devuelve false? Si no hay más elementos");
        System.out.println("  • ¿Es destructivo? SÍ - consume el elemento procesado");
        System.out.println("  • ¿Alternativa a? hasNext() + next() del Iterator");
        System.out.println();

        System.out.println("🔍 MÉTODO trySplit():");
        System.out.println("  • ¿Qué hace? Intenta dividir el Spliterator en dos partes");
        System.out.println("  • ¿Cuándo devuelve Spliterator? Si la división es exitosa");
        System.out.println("  • ¿Cuándo devuelve null? Si no se puede dividir");
        System.out.println("  • ¿Es destructivo? SÍ - modifica el Spliterator original");
        System.out.println("  • ¿Para qué sirve? Procesamiento paralelo");
        System.out.println();

        System.out.println("🔍 MÉTODO forEachRemaining(Consumer<T> action):");
        System.out.println("  • ¿Qué hace? Procesa todos los elementos restantes");
        System.out.println("  • ¿Cuándo termina? Cuando no hay más elementos");
        System.out.println("  • ¿Es destructivo? SÍ - consume todos los elementos");
        System.out.println("  • ¿Alternativa a? Todo el bucle while(iterator.hasNext())");
        System.out.println();

        // ========================================
        // 7. EJEMPLOS PRÁCTICOS
        // ========================================
        System.out.println("=== 7. EJEMPLOS PRÁCTICOS ===");

        // Ejemplo: Procesar productos por categoría
        System.out.println("Ejemplo: Procesar productos por categoría");
        Spliterator<Product> categorySpliterator = products.spliterator();

        System.out.println("Procesando productos uno por uno:");
        while (categorySpliterator.tryAdvance(product -> {
            if (product.getCategory().equals("Electrónica")) {
                System.out.println("  🔌 Electrónica: " + product.getName());
            } else if (product.getCategory().equals("Bebida")) {
                System.out.println("  🥤 Bebida: " + product.getName());
            } else {
                System.out.println("  🍕 Comida: " + product.getName());
            }
        })) {
            // El bucle continúa mientras tryAdvance devuelva true
        }
        System.out.println();

        // Ejemplo: Dividir y procesar en paralelo (simulado)
        System.out.println("Ejemplo: Dividir y procesar en paralelo (simulado)");
        Spliterator<Product> parallelSpliterator = products.spliterator();

        // Procesar algunos elementos
        parallelSpliterator.tryAdvance(p -> System.out.println("Procesado: " + p.getName()));

        // Dividir
        Spliterator<Product> parallelSpliterator2 = parallelSpliterator.trySplit();

        if (parallelSpliterator2 != null) {
            System.out.println("Procesando en 'hilo 1':");
            parallelSpliterator.forEachRemaining(p ->
                System.out.println("  Hilo 1: " + p.getName()));

            System.out.println("Procesando en 'hilo 2':");
            parallelSpliterator2.forEachRemaining(p ->
                System.out.println("  Hilo 2: " + p.getName()));
        }
        System.out.println();

        // ========================================
        // 8. RESUMEN
        // ========================================
        System.out.println("=== RESUMEN ===");
        System.out.println("• tryAdvance(): Procesa un elemento y avanza");
        System.out.println("• trySplit(): Divide el Spliterator para procesamiento paralelo");
        System.out.println("• forEachRemaining(): Procesa todos los elementos restantes");
        System.out.println("• Los Spliterators son la base de los Streams de Java");
        System.out.println("• Permiten procesamiento eficiente y paralelo de datos");
        System.out.println("• Son más eficientes que los Iterators tradicionales");
    }
} 