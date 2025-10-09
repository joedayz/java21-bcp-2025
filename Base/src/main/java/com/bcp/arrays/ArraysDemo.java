package com.bcp.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysDemo {

    public static void main(String[] args) {

        String[] values = new String[5];
        Arrays.fill(values, 2, 4, "aaa"); // llena posiciones 2 y 3
        System.out.println("=== 1. Array después de fill ===");
        System.out.println(Arrays.toString(values));


        int pos = Arrays.binarySearch(values, "aaa");
        System.out.println("\n=== 2. Resultado de binarySearch ===");
        System.out.println("Posición encontrada de 'aaa': " + pos);

        String[] names1 = {"Mary", "Ann", "Jane", "Tom"};
        String[] names2 = {"Mary", "Ann", "John", "Tom"};

        boolean isTheSame = Arrays.equals(names1, names2);
        System.out.println("\n=== 3. Comparación de arrays ===");
        System.out.println("¿names1 y names2 son iguales?: " + isTheSame);

        Arrays.sort(names2);
        System.out.println("\n=== 4.1 Orden natural (alfabético) ===");
        System.out.println(Arrays.toString(names2));

        // Orden personalizado (por longitud de la cadena, usa Comparator)
        Arrays.sort(names2, new LengthCompare());
        System.out.println("\n=== 4.2 Orden por longitud ===");
        System.out.println(Arrays.toString(names2));
    }
}
class LengthCompare implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return 1;   // s1 es "mayor" → se mueve después
        }
        if (s1.length() < s2.length()) {
            return -1;  // s1 es "menor" → se mueve antes
        }
        return 0;       // misma longitud → quedan igual
    }
}