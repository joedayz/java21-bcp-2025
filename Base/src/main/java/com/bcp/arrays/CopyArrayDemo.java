package com.bcp.arrays;

import java.util.Arrays;

public class CopyArrayDemo {

    public static void main(String[] args) {
        char[] a1 = {'a', 'c', 'm', 'e'};
        char[] a2 = {'t', 'o', ' ', ' ', ' '};

        // Copia desde a1 (desde índice 2) hacia a2 (desde índice 3), copiando 2 elementos
        System.arraycopy(a1, 2, a2, 3, 2);

        System.out.println("=== Ejemplo 1: System.arraycopy ===");
        System.out.println("a1: " + Arrays.toString(a1));
        System.out.println("a2: " + Arrays.toString(a2));

        char[] b1 = {'a', 'c', 'm', 'e'};
        char[] b2 = Arrays.copyOf(b1, 5);

        System.out.println("\n=== Ejemplo 2: Arrays.copyOf ===");
        System.out.println("b1: " + Arrays.toString(b1));
        System.out.println("b2: " + Arrays.toString(b2));



    }
}
