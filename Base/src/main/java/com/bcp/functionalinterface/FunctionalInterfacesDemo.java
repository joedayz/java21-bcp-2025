package com.bcp.functionalinterface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra diferentes interfaces funcionales del JDK
 * y cómo usarlas con clases anónimas
 */
public class FunctionalInterfacesDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Interfaces Funcionales del JDK ===\n");
        
        // Demo 1: Consumer<T> - Consume un valor, no retorna nada
        System.out.println("1. Consumer<T> - Consume un valor:");
        System.out.println("   Consumer<String> printer = new Consumer<String>() {");
        System.out.println("       @Override");
        System.out.println("       public void accept(String message) {");
        System.out.println("           System.out.println(\"Mensaje: \" + message);");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        Consumer<String> printer = new Consumer<String>() {
            @Override
            public void accept(String message) {
                System.out.println("   Mensaje: " + message);
            }
        };
        
        printer.accept("Hola desde Consumer");
        System.out.println();
        
        // Demo 2: Supplier<T> - Proporciona un valor
        System.out.println("2. Supplier<T> - Proporciona un valor:");
        System.out.println("   Supplier<Double> randomSupplier = new Supplier<Double>() {");
        System.out.println("       @Override");
        System.out.println("       public Double get() {");
        System.out.println("           return Math.random() * 100;");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        Supplier<Double> randomSupplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random() * 100;
            }
        };
        
        System.out.println("   Número aleatorio: " + randomSupplier.get());
        System.out.println();
        
        // Demo 3: Predicate<T> - Evalúa una condición
        System.out.println("3. Predicate<T> - Evalúa una condición:");
        System.out.println("   Predicate<Integer> isEven = new Predicate<Integer>() {");
        System.out.println("       @Override");
        System.out.println("       public boolean test(Integer number) {");
        System.out.println("           return number % 2 == 0;");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        };
        
        System.out.println("   ¿10 es par? " + isEven.test(10));
        System.out.println("   ¿7 es par? " + isEven.test(7));
        System.out.println();
        
        // Demo 4: Function<T, R> - Transforma un valor
        System.out.println("4. Function<T, R> - Transforma un valor:");
        System.out.println("   Function<String, Integer> lengthFunction = new Function<String, Integer>() {");
        System.out.println("       @Override");
        System.out.println("       public Integer apply(String text) {");
        System.out.println("           return text.length();");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String text) {
                return text.length();
            }
        };
        
        System.out.println("   Longitud de 'Hola': " + lengthFunction.apply("Hola"));
        System.out.println();
        
        // Demo 5: Uso práctico con listas
        System.out.println("5. Uso Práctico con Listas:");
        System.out.println();
        
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 79.99));
        
        // Filtrar productos caros (> $100)
        System.out.println("   Filtrar productos caros (> $100):");
        Predicate<Product> isExpensive = new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getPrice().compareTo(java.math.BigDecimal.valueOf(100)) > 0;
            }
        };
        
        for (Product product : products) {
            if (isExpensive.test(product)) {
                System.out.println("   " + product.getName() + " - $" + product.getPrice());
            }
        }
        System.out.println();
        
        // Transformar productos a strings con formato
        System.out.println("   Transformar productos a strings con formato:");
        Function<Product, String> productFormatter = new Function<Product, String>() {
            @Override
            public String apply(Product product) {
                return "Producto: " + product.getName() + " | Precio: $" + product.getPrice();
            }
        };
        
        for (Product product : products) {
            System.out.println("   " + productFormatter.apply(product));
        }
        System.out.println();
        
        // Demo 6: Comparación con lambdas
        System.out.println("6. Comparación con Lambdas:");
        System.out.println();
        
        System.out.println("   Consumer con lambda:");
        System.out.println("   Consumer<String> lambdaPrinter = message -> System.out.println(\"Lambda: \" + message);");
        Consumer<String> lambdaPrinter = message -> System.out.println("   Lambda: " + message);
        lambdaPrinter.accept("Hola desde lambda");
        System.out.println();
        
        System.out.println("   Predicate con lambda:");
        System.out.println("   Predicate<Integer> lambdaIsEven = number -> number % 2 == 0;");
        Predicate<Integer> lambdaIsEven = number -> number % 2 == 0;
        System.out.println("   ¿8 es par? " + lambdaIsEven.test(8));
        System.out.println();
        
        System.out.println("   Function con lambda:");
        System.out.println("   Function<String, String> lambdaUpper = text -> text.toUpperCase();");
        Function<String, String> lambdaUpper = text -> text.toUpperCase();
        System.out.println("   'hola' en mayúsculas: " + lambdaUpper.apply("hola"));
        System.out.println();
        
        // Demo 7: Características de las interfaces funcionales
        System.out.println("7. Características de las Interfaces Funcionales:");
        System.out.println();
        System.out.println("   📊 Todas tienen un solo método abstracto:");
        System.out.println("      - Consumer<T>: void accept(T t)");
        System.out.println("      - Supplier<T>: T get()");
        System.out.println("      - Predicate<T>: boolean test(T t)");
        System.out.println("      - Function<T,R>: R apply(T t)");
        System.out.println("      - Comparator<T>: int compare(T o1, T o2)");
        System.out.println();
        System.out.println("   📊 Se pueden usar con:");
        System.out.println("      - Clases anónimas (Java 1.1+)");
        System.out.println("      - Lambdas (Java 8+)");
        System.out.println("      - Referencias de método (Java 8+)");
        System.out.println();
        
        System.out.println("=== Demo Completado ===");
    }
} 