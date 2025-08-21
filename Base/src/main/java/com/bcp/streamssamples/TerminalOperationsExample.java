package com.bcp.streamssamples;

import java.util.Arrays;
import java.util.Optional;

public class TerminalOperationsExample {

    public static void main(String[] args) {
        System.out.println("=== OPERACIONES TERMINALES DE STREAMS ===");
        System.out.println("Demostrando allMatch, anyMatch, noneMatch, findAny y findFirst\n");

        // Array de colores como en la imagen
        String[] values = {"RED", "GREEN", "BLUE"};
        
        System.out.println("Array de colores: " + Arrays.toString(values));
        System.out.println();

        // 1. allMatch - verifica si TODOS los elementos cumplen la condición
        System.out.println("=== 1. ALLMATCH ===");
        boolean allGreen = Arrays.stream(values).allMatch(s -> s.equals("GREEN"));
        System.out.println("¿Todos los elementos son 'GREEN'? " + allGreen);
        
        // Verificar si todos son colores (contienen letras)
        boolean allAreColors = Arrays.stream(values).allMatch(s -> s.matches("[A-Z]+"));
        System.out.println("¿Todos los elementos son colores (solo letras)? " + allAreColors);
        System.out.println();

        // 2. anyMatch - verifica si AL MENOS UNO cumple la condición
        System.out.println("=== 2. ANYMATCH ===");
        boolean anyGreen = Arrays.stream(values).anyMatch(s -> s.equals("GREEN"));
        System.out.println("¿Al menos un elemento es 'GREEN'? " + anyGreen);
        
        // Verificar si hay algún color que empiece con 'B'
        boolean anyStartsWithB = Arrays.stream(values).anyMatch(s -> s.startsWith("B"));
        System.out.println("¿Algún elemento empieza con 'B'? " + anyStartsWithB);
        System.out.println();

        // 3. noneMatch - verifica si NINGUNO cumple la condición
        System.out.println("=== 3. NONEMATCH ===");
        boolean noneGreen = Arrays.stream(values).noneMatch(s -> s.equals("GREEN"));
        System.out.println("¿Ningún elemento es 'GREEN'? " + noneGreen);
        
        // Verificar si no hay colores que empiecen con 'X'
        boolean noneStartsWithX = Arrays.stream(values).noneMatch(s -> s.startsWith("X"));
        System.out.println("¿Ningún elemento empieza con 'X'? " + noneStartsWithX);
        System.out.println();

        // 4. findAny - encuentra CUALQUIER elemento (no garantiza cuál)
        System.out.println("=== 4. FINDANY ===");
        Optional<String> anyColour = Arrays.stream(values).findAny();
        System.out.println("Cualquier color encontrado: " + anyColour.orElse("No encontrado"));
        
        // findAny en stream vacío
        Optional<String> emptyResult = Arrays.stream(new String[0]).findAny();
        System.out.println("findAny en stream vacío: " + emptyResult.orElse("Stream vacío"));
        System.out.println();

        // 5. findFirst - encuentra el PRIMER elemento en orden
        System.out.println("=== 5. FINDFIRST ===");
        Optional<String> firstColour = Arrays.stream(values).findFirst();
        System.out.println("Primer color encontrado: " + firstColour.orElse("No encontrado"));
        
        // findFirst con filtro
        Optional<String> firstStartingWithR = Arrays.stream(values)
            .filter(s -> s.startsWith("R"))
            .findFirst();
        System.out.println("Primer color que empieza con 'R': " + firstStartingWithR.orElse("No encontrado"));
        System.out.println();

        // Comparación entre findAny y findFirst
        System.out.println("=== COMPARACIÓN FINDANY vs FINDFIRST ===");
        System.out.println("En streams secuenciales, findAny y findFirst suelen devolver el mismo elemento:");
        System.out.println("findAny(): " + anyColour.get());
        System.out.println("findFirst(): " + firstColour.get());
        System.out.println("¿Son iguales? " + anyColour.equals(firstColour));
        System.out.println();

        // Ejemplos adicionales con diferentes condiciones
        System.out.println("=== EJEMPLOS ADICIONALES ===");
        
        // Verificar si todos los colores tienen 3 letras
        boolean allHaveThreeLetters = Arrays.stream(values).allMatch(s -> s.length() == 3);
        System.out.println("¿Todos los colores tienen 3 letras? " + allHaveThreeLetters);
        
        // Verificar si algún color tiene más de 4 letras
        boolean anyHasMoreThanFourLetters = Arrays.stream(values).anyMatch(s -> s.length() > 4);
        System.out.println("¿Algún color tiene más de 4 letras? " + anyHasMoreThanFourLetters);
        
        // Verificar si ningún color es "YELLOW"
        boolean noneIsYellow = Arrays.stream(values).noneMatch(s -> s.equals("YELLOW"));
        System.out.println("¿Ningún color es 'YELLOW'? " + noneIsYellow);
        
        // Encontrar el primer color que contenga la letra 'E'
        Optional<String> firstWithE = Arrays.stream(values)
            .filter(s -> s.contains("E"))
            .findFirst();
        System.out.println("Primer color que contiene 'E': " + firstWithE.orElse("No encontrado"));
    }
} 