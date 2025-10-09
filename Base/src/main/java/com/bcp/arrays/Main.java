package com.bcp.arrays;

public class Main {

    public static void main(String[] args) {
        // Crear un array de productos
        Product[] products = new Product[4];

        // Agregar alimentos
        products[0] = new Food("Manzana", 1.5, "2025-12-31");
        products[1] = new Food("Pan", 2.0, "2025-10-15");

        // Agregar bebidas
        products[2] = new Drink("Agua", 1.0, false);
        products[3] = new Drink("Cerveza", 3.5, true);

        // Mostrar la información de todos los productos
        System.out.println("=== Lista de Productos ===");
        for (Product product : products) {
            product.showInfo();  // Polimorfismo en acción
        }

    }
}
