package com.bcp.colecciones;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DequeSamples {

    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Drink("Cookie");
//        List<Product> list = new ArrayList<>();
//        list.add(p1);
//        list.add(p2);
//
//        Deque<Product> productDeque1 = new ArrayDeque<>();
//        Deque<Product> productDeque2 = new ArrayDeque<>(20); // capacidad inicial
//        Deque<Product> productDeque3 = new ArrayDeque<>(list); // inicializa con elementos
//
//        // Mostrar los elementos del deque
//        System.out.println("Deque 3: " + productDeque3);


        Deque<Product> menu = new ArrayDeque<>();

        Product nullProduct = menu.pollFirst(); // Devuelve null, ya que está vacío

        menu.offerFirst(p1);
        menu.offerFirst(p2);

        Product tea = menu.pollFirst();         // Remueve y devuelve el primero (p2)
        Product cake1 = menu.peekFirst();       // Mira el primero sin remover (p1)

        menu.offerLast(p3);
        menu.offerLast(p1);

        Product cake2 = menu.pollLast();        // Remueve y devuelve el último (p1)
        Product cookie = menu.peekLast();       // Mira el último sin remover (p3)

        // menu.offerLast(null); // ❌ Lanza NullPointerException

        // Mostrar resultados
        System.out.println("nullProduct: " + nullProduct);
        System.out.println("tea: " + tea);
        System.out.println("cake1: " + cake1);
        System.out.println("cake2: " + cake2);
        System.out.println("cookie: " + cookie);

    }
}
