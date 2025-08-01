package com.bcp.records;

import java.time.LocalDateTime;

record Delivery (Product product, LocalDateTime time){}

//NOTA: Puede tener métodos e implementar interfaces
public record Product(String name, double price) {

//    public Product(String name, double price){
//        name = name.toUpperCase(); //transformacion
//        this.name = name; //asignacion explicita
//        this.price = price; //asignacion explicita
//    }

    //constructor compacto
    public Product{
        name = name.toUpperCase();

    }
}



class Main{
    public static void main(String[] args) {
        Product product = new Product("Pizza", 10.0);
        //accessors
        System.out.println(product.name());
        System.out.println(product.price());

        Product p1 = new Product("Pizza", 10.0);
        Product p2 = new Product("Pizza", 10.0);
        System.out.println(p1.equals(p2));
        System.out.println(p1.toString());

    }
}