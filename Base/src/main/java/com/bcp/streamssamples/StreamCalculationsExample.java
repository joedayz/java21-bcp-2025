package com.bcp.streamssamples;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class StreamCalculationsExample {

    public static void main(String[] args) {
        System.out.println("=== CÁLCULOS CON STREAMS ===");
        System.out.println("Demostrando filter, count, mapToInt, sum, average, max y min\n");

        // Array de colores como en la imagen
        String[] values = {"RED", "GREEN", "BLUE"};
        
        System.out.println("Array de colores: " + Arrays.toString(values));
        System.out.println("Longitudes: RED(3), GREEN(5), BLUE(4)");
        System.out.println();

        // 1. v1 - Filter y Count
        System.out.println("=== 1. FILTER Y COUNT ===");
        long v1 = Arrays.stream(values).filter(s -> s.indexOf('R') != -1).count();
        System.out.println("v1 = Arrays.stream(values).filter(s->s.indexOf('R') != -1).count();");
        System.out.println("Elementos que contienen 'R': " + v1 + " // 2");
        System.out.println("Explicación: 'RED' y 'GREEN' contienen 'R'");
        System.out.println();

        // 2. v2 - MapToInt y Sum
        System.out.println("=== 2. MAPTOINT Y SUM ===");
        int v2 = Arrays.stream(values).mapToInt(v -> v.length()).sum();
        System.out.println("v2 = Arrays.stream(values).mapToInt(v->v.length()).sum();");
        System.out.println("Suma de longitudes: " + v2 + " // 12");
        System.out.println("Explicación: 3 + 5 + 4 = 12");
        System.out.println();

        // 3. v3 y avgValue - MapToInt y Average
        System.out.println("=== 3. MAPTOINT Y AVERAGE ===");
        OptionalDouble v3 = Arrays.stream(values).mapToInt(v -> v.length()).average();
        double avgValue = v3.isPresent() ? v3.getAsDouble() : 0;
        System.out.println("v3 = Arrays.stream(values).mapToInt(v->v.length()).average();");
        System.out.println("avgValue = v3.isPresent() ? v3.getAsDouble() : 0;");
        System.out.println("Promedio de longitudes: " + avgValue + " // 4");
        System.out.println("Explicación: (3 + 5 + 4) / 3 = 4");
        System.out.println();

        // 4. v4 y maxValue - Max con Comparator
        System.out.println("=== 4. MAX CON COMPARATOR ===");
        Optional<String> v4 = Arrays.stream(values).max((s1, s2) -> s1.compareTo(s2));
        String maxValue = (v4.isPresent()) ? v4.get() : "no data";
        System.out.println("v4 = Arrays.stream(values).max((s1,s2) -> s1.compareTo(s2));");
        System.out.println("maxValue = (v4.isPresent()) ? v4.get() : \"no data\";");
        System.out.println("Valor máximo (lexicográfico): " + maxValue + " // RED");
        System.out.println("Explicación: 'RED' es lexicográficamente mayor que 'GREEN' y 'BLUE'");
        System.out.println();

        // 5. v5 y minValue - Min con Comparator
        System.out.println("=== 5. MIN CON COMPARATOR ===");
        Optional<String> v5 = Arrays.stream(values).min((s1, s2) -> s1.compareTo(s2));
        String minValue = (v5.isPresent()) ? v5.get() : "no data";
        System.out.println("v5 = Arrays.stream(values).min((s1,s2) -> s1.compareTo(s2));");
        System.out.println("minValue = (v5.isPresent()) ? v5.get() : \"no data\";");
        System.out.println("Valor mínimo (lexicográfico): " + minValue + " // BLUE");
        System.out.println("Explicación: 'BLUE' es lexicográficamente menor que 'GREEN' y 'RED'");
        System.out.println();

        // Resumen de resultados
        System.out.println("=== RESUMEN DE RESULTADOS ===");
        System.out.println("v1 (count con filter): " + v1);
        System.out.println("v2 (sum de longitudes): " + v2);
        System.out.println("avgValue (promedio): " + avgValue);
        System.out.println("maxValue (máximo): " + maxValue);
        System.out.println("minValue (mínimo): " + minValue);
        System.out.println();

        // Ejemplos adicionales
        System.out.println("=== EJEMPLOS ADICIONALES ===");
        
        // Contar elementos que empiezan con 'B'
        long countStartingWithB = Arrays.stream(values).filter(s -> s.startsWith("B")).count();
        System.out.println("Elementos que empiezan con 'B': " + countStartingWithB);
        
        // Suma de longitudes de elementos que contienen 'E'
        int sumWithE = Arrays.stream(values)
            .filter(s -> s.contains("E"))
            .mapToInt(String::length)
            .sum();
        System.out.println("Suma de longitudes de elementos con 'E': " + sumWithE);
        
        // Promedio de longitudes de elementos que contienen 'E'
        OptionalDouble avgWithE = Arrays.stream(values)
            .filter(s -> s.contains("E"))
            .mapToInt(String::length)
            .average();
        System.out.println("Promedio de longitudes de elementos con 'E': " + 
            (avgWithE.isPresent() ? avgWithE.getAsDouble() : "N/A"));
        
        // Elemento más largo
        Optional<String> longest = Arrays.stream(values)
            .max((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Elemento más largo: " + longest.orElse("N/A"));
        
        // Elemento más corto
        Optional<String> shortest = Arrays.stream(values)
            .min((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Elemento más corto: " + shortest.orElse("N/A"));
    }
} 