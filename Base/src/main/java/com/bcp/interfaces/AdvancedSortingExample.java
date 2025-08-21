package com.bcp.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvancedSortingExample {
    
    public static void main(String[] args) {
        // Inicializar y poblar la lista de productos
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake", 1.99));
        menu.add(new Food("Cookie", 2.99));
        menu.add(new Drink("Tea", 2.99));
        menu.add(new Drink("Coffee", 2.99));
        
        System.out.println("=== LISTA ORIGINAL ===");
        menu.forEach(System.out::println);
        
        // Definir comparadores usando lambda expressions
        System.out.println("\n=== DEFINICIÓN DE COMPARADORES CON LAMBDA ===");
        Comparator<Product> sortNames = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Product> sortPrices = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
        
        System.out.println("Comparator para nombres: " + sortNames);
        System.out.println("Comparator para precios: " + sortPrices);
        
        // Ejemplo 1: Ordenamiento encadenado con reversión
        System.out.println("\n=== ORDENAMIENTO ENCADENADO CON REVERSIÓN ===");
        System.out.println("Ordenamiento: primero por nombre (ascendente), luego por precio (ascendente), todo revertido");
        
        List<Product> menu1 = new ArrayList<>(menu);
        Collections.sort(menu1, sortNames.thenComparing(sortPrices).reversed());
        
        System.out.println("Resultado:");
        menu1.forEach(System.out::println);
        
        // Explicación del ordenamiento encadenado
        System.out.println("\n--- EXPLICACIÓN DEL ORDENAMIENTO ---");
        System.out.println("1. sortNames: ordena por nombre (ascendente)");
        System.out.println("2. thenComparing(sortPrices): si los nombres son iguales, ordena por precio (ascendente)");
        System.out.println("3. reversed(): invierte todo el orden (descendente)");
        System.out.println("Resultado final: orden descendente por nombre, y para nombres iguales, orden descendente por precio");
        
        // Ejemplo 2: Agregar elemento nulo y ordenar con manejo de nulos
        System.out.println("\n=== ORDENAMIENTO CON MANEJO DE VALORES NULOS ===");
        List<Product> menu2 = new ArrayList<>(menu);
        menu2.add(null); // Agregar elemento nulo
        
        System.out.println("Lista con elemento nulo:");
        menu2.forEach(item -> System.out.println(item != null ? item : "null"));
        
        // Ordenar con nullsFirst
        Collections.sort(menu2, Comparator.nullsFirst(sortNames));
        
        System.out.println("\nDespués de ordenar con nullsFirst:");
        menu2.forEach(item -> System.out.println(item != null ? item : "null"));
        
        // Ejemplo 3: Diferentes estrategias de manejo de nulos
        System.out.println("\n=== DIFERENTES ESTRATEGIAS DE MANEJO DE NULOS ===");
        List<Product> menu3 = new ArrayList<>(menu);
        menu3.add(null);
        
        System.out.println("Lista original con nulo:");
        menu3.forEach(item -> System.out.println(item != null ? item : "null"));
        
        // nullsFirst - nulos al principio
        List<Product> menuNullsFirst = new ArrayList<>(menu3);
        Collections.sort(menuNullsFirst, Comparator.nullsFirst(sortNames));
        System.out.println("\nnullsFirst (nulos al principio):");
        menuNullsFirst.forEach(item -> System.out.println(item != null ? item : "null"));
        
        // nullsLast - nulos al final
        List<Product> menuNullsLast = new ArrayList<>(menu3);
        Collections.sort(menuNullsLast, Comparator.nullsLast(sortNames));
        System.out.println("\nnullsLast (nulos al final):");
        menuNullsLast.forEach(item -> System.out.println(item != null ? item : "null"));
        
        // Ejemplo 4: Ordenamiento múltiple más complejo
        System.out.println("\n=== ORDENAMIENTO MÚLTIPLE COMPLEJO ===");
        List<Product> menu4 = new ArrayList<>(menu);
        menu4.add(new Food("Cake", 3.99)); // Cake con precio diferente
        menu4.add(new Drink("Coffee", 1.99)); // Coffee con precio diferente
        
        System.out.println("Lista con productos duplicados:");
        menu4.forEach(System.out::println);
        
        // Ordenamiento: primero por nombre (ascendente), luego por precio (descendente)
        Collections.sort(menu4, sortNames.thenComparing(sortPrices.reversed()));
        
        System.out.println("\nOrdenado por nombre (ascendente) y precio (descendente):");
        menu4.forEach(System.out::println);
        
        // Ejemplo 5: Comparadores con method references
        System.out.println("\n=== COMPARADORES CON METHOD REFERENCES ===");
        Comparator<Product> sortNamesMR = Comparator.comparing(Product::getName);
        Comparator<Product> sortPricesMR = Comparator.comparing(Product::getPrice);
        
        List<Product> menu5 = new ArrayList<>(menu);
        Collections.sort(menu5, sortNamesMR.thenComparing(sortPricesMR));
        
        System.out.println("Ordenado con method references:");
        menu5.forEach(System.out::println);
    }
} 