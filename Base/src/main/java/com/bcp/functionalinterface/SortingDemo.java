package com.bcp.functionalinterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Demo que muestra el uso de clases anónimas para ordenamiento
 * Como se muestra en la imagen: "anonymous inner class to override a few methods (just one in case of a functional interface)"
 */
public class SortingDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Ordenamiento con Clases Anónimas ===\n");
        System.out.println("anonymous inner class to override a few methods (just one in case of a functional interface)");
        System.out.println();
        
        // Inicializar la lista de productos
        System.out.println("1. Inicialización de la lista:");
        System.out.println("   List<Product> products = ...");
        System.out.println();
        
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 79.99));
        
        System.out.println("   Lista original:");
        displayProducts(products);
        System.out.println();
        
        // Demo 1: Ordenamiento por nombre usando clase anónima
        System.out.println("2. Ordenamiento por Nombre (por defecto):");
        System.out.println("   Collections.sort(products, new Comparator<Product>() {");
        System.out.println("       public int compare(Product p1, Product p2) {");
        System.out.println("           return p1.getName().compareTo(p2.getName());");
        System.out.println("       }");
        System.out.println("   });");
        System.out.println();
        
        // Implementación exacta como en la imagen
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        
        System.out.println("   Lista ordenada por nombre:");
        displayProducts(products);
        System.out.println();
        
        // Demo 2: Ordenamiento por precio usando clase anónima
        System.out.println("3. Ordenamiento por Precio:");
        System.out.println("   Collections.sort(products, new Comparator<Product>() {");
        System.out.println("       public int compare(Product p1, Product p2) {");
        System.out.println("           return p1.getPrice().compareTo(p2.getPrice());");
        System.out.println("       }");
        System.out.println("   });");
        System.out.println();
        
        // Implementación exacta como en la imagen
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
        
        System.out.println("   Lista ordenada por precio:");
        displayProducts(products);
        System.out.println();
        
        // Demo 3: Comparación con lambdas
        System.out.println("4. Comparación con Lambdas (Java 8+):");
        System.out.println();
        
        // Recrear la lista original
        products.clear();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 79.99));
        
        System.out.println("   Ordenamiento por nombre con lambda:");
        System.out.println("   Collections.sort(products, (p1, p2) -> p1.getName().compareTo(p2.getName()));");
        Collections.sort(products, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        displayProducts(products);
        System.out.println();
        
        System.out.println("   Ordenamiento por precio con lambda:");
        System.out.println("   Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));");
        Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        displayProducts(products);
        System.out.println();
        
        // Demo 4: Características de las clases anónimas
        System.out.println("5. Características de las Clases Anónimas:");
        System.out.println();
        System.out.println("   📊 Clase Anónima con Comparator:");
        System.out.println("      ✅ Implementa la interfaz Comparator<Product>");
        System.out.println("      ✅ Sobrescribe solo el método compare()");
        System.out.println("      ✅ No tiene nombre explícito");
        System.out.println("      ✅ Se crea inline durante la llamada a sort()");
        System.out.println("      ✅ No puede ser reutilizada");
        System.out.println();
        System.out.println("   📊 Comparator es una Interfaz Funcional:");
        System.out.println("      ✅ Tiene un solo método abstracto: compare()");
        System.out.println("      ✅ Por eso se puede usar con lambdas");
        System.out.println("      ✅ @FunctionalInterface (implícito)");
        System.out.println();
        
        // Demo 5: Ejemplos adicionales de ordenamiento
        System.out.println("6. Ejemplos Adicionales de Ordenamiento:");
        System.out.println();
        
        // Recrear la lista original
        products.clear();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 79.99));
        
        // Ordenamiento descendente por precio
        System.out.println("   Ordenamiento descendente por precio:");
        System.out.println("   Collections.sort(products, new Comparator<Product>() {");
        System.out.println("       public int compare(Product p1, Product p2) {");
        System.out.println("           return p2.getPrice().compareTo(p1.getPrice()); // Descendente");
        System.out.println("       }");
        System.out.println("   });");
        System.out.println();
        
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p2.getPrice().compareTo(p1.getPrice()); // Descendente
            }
        });
        
        displayProducts(products);
        System.out.println();
        
        // Ordenamiento por longitud del nombre
        System.out.println("   Ordenamiento por longitud del nombre:");
        System.out.println("   Collections.sort(products, new Comparator<Product>() {");
        System.out.println("       public int compare(Product p1, Product p2) {");
        System.out.println("           return Integer.compare(p1.getName().length(), p2.getName().length());");
        System.out.println("       }");
        System.out.println("   });");
        System.out.println();
        
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getName().length(), p2.getName().length());
            }
        });
        
        displayProducts(products);
        System.out.println();
        
        System.out.println("=== Demo Completado ===");
    }
    
    /**
     * Método para mostrar la lista de productos
     */
    private static void displayProducts(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + products.get(i));
        }
    }
} 