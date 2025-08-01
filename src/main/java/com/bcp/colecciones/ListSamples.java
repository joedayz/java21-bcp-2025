package com.bcp.colecciones;

import java.util.*;

public class ListSamples {
    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");

//        Set<Product> set1 = new HashSet<>();
//        set1.add(p1);
//        set1.add(p2);
//
//        List<Product> list1 = new ArrayList<>();
//        List<Product> list2 = new ArrayList<>(20);
//        List<Product> list3 = new ArrayList<>(set1);
//        List<Product> list4 = Arrays.asList(p1, p2);
//        List<Product> list5 = List.of(p1, p2);

        List<Product> menu = new ArrayList<>();

        menu.add(p1);                // insert first element
        menu.add(p2);                // insert next element
        menu.add(2, null);           // insert null
        menu.add(3, p1);             // insert element
        menu.add(2, p1);             // insert element
        menu.set(2, p2);             // update element
        menu.remove(0);              // remove element by index
        menu.remove(p2);             // remove element by value

        boolean hasTea = menu.contains(p2);        // check existence
        int index = menu.indexOf(p1);              // find index
        menu.get(index).setName("Cookie");         // update element content

        // This line throws IndexOutOfBoundsException if list size <= 4
        menu.add(4, p2);            // throws exception if index 4 is out of bounds

    }
}


class Product {
    String name;

    Product(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
    // Para que HashSet funcione correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Food extends Product {
    Food(String name) {
        super(name);
    }
}

class Drink extends Product {
    Drink(String name) {
        super(name);
    }
}