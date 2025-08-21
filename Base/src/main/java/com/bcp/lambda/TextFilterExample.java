package com.bcp.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFilterExample {

    public static void main(String[] args) {
        // Inicializar instancia de TextFilter
        TextFilter filter = new TextFilter();
        
        // Inicializar lista de strings para ejemplos de removeIf
        List<String> removeList = new ArrayList<>();
        removeList.add("apple");
        removeList.add("remove A");
        removeList.add("banana");
        removeList.add("remove A");
        removeList.add("cherry");
        
        System.out.println("=== EJEMPLOS DE REMOVEIF ===");
        System.out.println("Lista original para removeIf: " + removeList);
        
        // Ejemplo 1: removeIf con lambda expression para método estático
        List<String> list1 = new ArrayList<>(removeList); // Crear una copia para modificar
        list1.removeIf(s -> TextFilter.removeA(s));
        System.out.println("Después de removeIf (lambda para método estático): " + list1);
        
        // Ejemplo 2: removeIf con method reference para método estático
        List<String> list2 = new ArrayList<>(removeList); // Crear otra copia
        list2.removeIf(TextFilter::removeA); // mismo que la línea anterior
        System.out.println("Después de removeIf (method reference para método estático): " + list2);
        
        // Inicializar lista de strings para ejemplos de sort
        List<String> sortList = new ArrayList<>();
        sortList.add("Banana");
        sortList.add("apple");
        sortList.add("Date");
        sortList.add("cherry");
        
        System.out.println("\n=== EJEMPLOS DE COLLECTIONS.SORT ===");
        System.out.println("Lista original para sort: " + sortList);
        
        // Ejemplo 3: sort con lambda expression para método de instancia
        List<String> list3 = new ArrayList<>(sortList); // Crear una copia
        Collections.sort(list3, (s1, s2) -> filter.sortText(s1, s2));
        System.out.println("Después de sort (lambda para método de instancia): " + list3);
        
        // Ejemplo 4: sort con method reference para método de instancia
        List<String> list4 = new ArrayList<>(sortList); // Crear otra copia
        Collections.sort(list4, filter::sortText); // mismo que la línea anterior
        System.out.println("Después de sort (method reference para método de instancia): " + list4);
        
        // Ejemplo 5: sort con lambda expression para String.compareToIgnoreCase
        List<String> list5 = new ArrayList<>(sortList); // Crear otra copia
        Collections.sort(list5, (s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("Después de sort (lambda para compareToIgnoreCase): " + list5);
        
        // Ejemplo 6: sort con method reference para String.compareToIgnoreCase
        List<String> list6 = new ArrayList<>(sortList); // Crear otra copia
        Collections.sort(list6, String::compareToIgnoreCase); // mismo que la línea anterior
        System.out.println("Después de sort (method reference para compareToIgnoreCase): " + list6);
        
        // Nota: La clase String tiene un método de instancia compareToIgnoreCase 
        // que implementa ordenamiento de texto sin distinguir mayúsculas/minúsculas
        System.out.println("\n=== COMPARACIÓN DE RESULTADOS ===");
        System.out.println("Ordenamiento normal (case-sensitive): " + list4);
        System.out.println("Ordenamiento ignore case: " + list6);
    }
} 