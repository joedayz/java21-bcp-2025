package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SingleArgumentStreamExample {

    public static void main(String[] args) {
        System.out.println("=== EJEMPLOS DE STREAMS CON LAMBDAS DE UN ARGUMENTO ===");
        System.out.println("Demostrando Consumer, Predicate y Function (sin interfaces Bi...)\n");

        // Crear lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product("Headphones", 15.99));
        products.add(new Product("Tablet", 599.99));

        System.out.println("--- LISTA ORIGINAL ---");
        products.forEach(System.out::println);

        // Ejemplo 1: CONSUMER - Para realizar acciones en cada elemento
        System.out.println("\n=== CONSUMER ===");
        System.out.println("Consumer<T> - Toma un argumento, no devuelve nada (void)");
        
        // Consumer explícito
        Consumer<Product> printProductName = p -> System.out.println("Producto: " + p.getName());
        System.out.println("Imprimiendo solo nombres:");
        products.forEach(printProductName);
        
        // Consumer inline
        System.out.println("\nAplicando descuento del 10% a todos los productos:");
        Consumer<Product> applyDiscount = p -> {
            p.setDiscount(0.10);
            System.out.println("Descuento aplicado a: " + p.getName() + " - Precio: $" + p.getPrice() + 
                             " - Descuento: " + (p.getDiscount() * 100) + "%");
        };
        products.forEach(applyDiscount);

        // Ejemplo 2: PREDICATE - Para filtrar elementos
        System.out.println("\n=== PREDICATE ===");
        System.out.println("Predicate<T> - Toma un argumento, devuelve boolean");
        
        // Predicate explícito
        Predicate<Product> isExpensive = p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0;
        Predicate<Product> isCheap = p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) < 0;
        
        System.out.println("Productos caros (> $100):");
        products.stream()
                .filter(isExpensive)
                .forEach(System.out::println);
        
        System.out.println("\nProductos baratos (< $50):");
        products.stream()
                .filter(isCheap)
                .forEach(System.out::println);
        
        // Predicate combinado
        System.out.println("\nProductos con precio entre $50 y $200:");
        Predicate<Product> isMediumPrice = p -> {
            BigDecimal price = p.getPrice();
            return price.compareTo(BigDecimal.valueOf(50)) >= 0 && 
                   price.compareTo(BigDecimal.valueOf(200)) <= 0;
        };
        products.stream()
                .filter(isMediumPrice)
                .forEach(System.out::println);

        // Ejemplo 3: FUNCTION - Para transformar elementos
        System.out.println("\n=== FUNCTION ===");
        System.out.println("Function<T,R> - Toma un argumento de tipo T, devuelve un resultado de tipo R");
        
        // Function explícito
        Function<Product, String> getUpperCaseName = p -> p.getName().toUpperCase();
        Function<Product, BigDecimal> getPriceWithTax = p -> p.getPrice().multiply(BigDecimal.valueOf(0.18)); // 18% IGV
        
        System.out.println("Nombres en mayúsculas:");
        List<String> upperCaseNames = products.stream()
                .map(getUpperCaseName)
                .collect(Collectors.toList());
        upperCaseNames.forEach(System.out::println);
        
        System.out.println("\nPrecios con IGV (18%):");
        List<BigDecimal> pricesWithTax = products.stream()
                .map(getPriceWithTax)
                .collect(Collectors.toList());
        pricesWithTax.forEach(price -> System.out.println("$" + price));

        // Ejemplo 4: COMBINANDO INTERFACES FUNCIONALES
        System.out.println("\n=== COMBINANDO INTERFACES FUNCIONALES ===");
        
        // Pipeline: filter (Predicate) -> map (Function) -> forEach (Consumer)
        System.out.println("Productos caros con descuento especial:");
        
        Predicate<Product> expensiveForDiscount = p -> p.getPrice().compareTo(BigDecimal.valueOf(200)) > 0;
        Function<Product, String> formatExpensiveProduct = p -> {
            BigDecimal discountedPrice = p.getPrice().multiply(BigDecimal.valueOf(0.85)); // 15% descuento
            return p.getName() + " - Precio original: $" + p.getPrice() + 
                   " - Precio con descuento: $" + discountedPrice;
        };
        Consumer<String> printFormatted = System.out::println;
        
        products.stream()
                .filter(expensiveForDiscount)    // Predicate
                .map(formatExpensiveProduct)     // Function
                .forEach(printFormatted);        // Consumer

        // Ejemplo 5: INTERFACES FUNCIONALES PRIMITIVAS
        System.out.println("\n=== INTERFACES FUNCIONALES PRIMITIVAS ===");
        System.out.println("Evitando auto-boxing/unboxing para mejor rendimiento");
        
        // IntPredicate - más eficiente que Predicate<Integer>
        System.out.println("Suma de precios de productos caros (> $100):");
        double totalExpensive = products.stream()
                .mapToDouble(p -> p.getPrice().doubleValue())  // ToDoubleFunction<Product>
                .filter(price -> price > 100)                  // DoublePredicate
                .sum();                                        // Operación terminal
        
        System.out.println("Total: $" + totalExpensive);
        
        // IntConsumer - más eficiente que Consumer<Integer>
        System.out.println("\nLongitudes de nombres de productos:");
        products.stream()
                .mapToInt(p -> p.getName().length())  // ToIntFunction<Product>
                .forEach(length -> System.out.println("Longitud: " + length)); // IntConsumer

        // Ejemplo 6: METHOD REFERENCES
        System.out.println("\n=== METHOD REFERENCES ===");
        System.out.println("Forma más concisa de escribir lambdas");
        
        // Method reference en lugar de lambda
        System.out.println("Nombres de productos (usando method reference):");
        products.stream()
                .map(Product::getName)           // Equivalente a: p -> p.getName()
                .forEach(System.out::println);   // Equivalente a: name -> System.out.println(name)
        
        System.out.println("\nPrecios de productos (usando method reference):");
        products.stream()
                .map(Product::getPrice)          // Equivalente a: p -> p.getPrice()
                .forEach(price -> System.out.println("$" + price));
    }
} 