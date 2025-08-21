package com.bcp.inicializacion;

public class Shop {

    static {
        System.out.println("Static block of Shop");
    }


    public static void main(String[] args) {
        //1ero cargar las clases - ejecutar los bloque de inicializacion static
        //2do cargar la instancia de la clase
        Product p1 = new Food();
        Product p2 = new Food();
    }
}
