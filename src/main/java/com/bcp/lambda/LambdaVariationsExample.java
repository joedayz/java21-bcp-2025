package com.bcp.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaVariationsExample {
    
    public static void main(String[] args) {
        // Inicializar lista de strings
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("remove me");
        list.add("cherry");
        list.add("remove me");
        list.add("date");
        
        System.out.println("=== LISTA ORIGINAL ===");
        list.forEach(System.out::println);
        
        // Ejemplo 1: Comparator con lambda expression
        System.out.println("\n=== COMPARATOR CON LAMBDA ===");
        Comparator<String> sortText = (s1, s2) -> s1.compareTo(s2);
        System.out.println("Comparator creado: " + sortText);
        
        // Ejemplo 2: removeIf con lambda y parámetro final explícito
        System.out.println("\n=== REMOVEIF CON LAMBDA (FINAL STRING) ===");
        List<String> list1 = new ArrayList<>(list);
        list1.removeIf((final String s) -> s.equals("remove me"));
        list1.forEach(System.out::println);
        
        // Ejemplo 3: removeIf con lambda usando var (Java 11+)
        System.out.println("\n=== REMOVEIF CON LAMBDA (FINAL VAR) ===");
        List<String> list2 = new ArrayList<>(list);
        list2.removeIf((final var s) -> s.equals("remove me"));
        list2.forEach(System.out::println);
        
        // Ejemplo 4: sort con lambda usando bloque de código
        System.out.println("\n=== SORT CON LAMBDA Y BLOQUE DE CÓDIGO ===");
        List<String> list3 = new ArrayList<>(list);
        list3.sort((s1, s2) -> { 
            return s1.compareTo(s2); 
        });
        list3.forEach(System.out::println);
        
        // Ejemplo 5: Collections.sort usando el comparator predefinido
        System.out.println("\n=== COLLECTIONS.SORT CON COMPARATOR PREDEFINIDO ===");
        List<String> list4 = new ArrayList<>(list);
        Collections.sort(list4, sortText);
        list4.forEach(System.out::println);
        
        // Ejemplo 6: Comparación de sintaxis de lambda
        System.out.println("\n=== COMPARACIÓN DE SINTAXIS LAMBDA ===");
        List<String> list5 = new ArrayList<>(list);
        
        // Sintaxis 1: Lambda simple
        list5.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Lambda simple: " + list5);
        
        // Sintaxis 2: Lambda con bloque
        List<String> list6 = new ArrayList<>(list);
        list6.sort((s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println("Lambda con bloque: " + list6);
        
        // Sintaxis 3: Lambda con parámetros tipados
        List<String> list7 = new ArrayList<>(list);
        list7.sort((String s1, String s2) -> s1.compareTo(s2));
        System.out.println("Lambda con tipos explícitos: " + list7);
        
        // Sintaxis 4: Lambda con var (Java 11+)
        List<String> list8 = new ArrayList<>(list);
        list8.sort((var s1, var s2) -> s1.compareTo(s2));
        System.out.println("Lambda con var: " + list8);
        
        // Ejemplo 7: removeIf con diferentes sintaxis
        System.out.println("\n=== REMOVEIF CON DIFERENTES SINTAXIS ===");
        List<String> list9 = new ArrayList<>(list);
        
        // Sintaxis simple
        list9.removeIf(s -> s.equals("remove me"));
        System.out.println("removeIf simple: " + list9);
        
        // Sintaxis con bloque
        List<String> list10 = new ArrayList<>(list);
        list10.removeIf(s -> {
            return s.equals("remove me");
        });
        System.out.println("removeIf con bloque: " + list10);
        
        // Sintaxis con parámetro tipado
        List<String> list11 = new ArrayList<>(list);
        list11.removeIf((String s) -> s.equals("remove me"));
        System.out.println("removeIf con tipo explícito: " + list11);
    }
} 