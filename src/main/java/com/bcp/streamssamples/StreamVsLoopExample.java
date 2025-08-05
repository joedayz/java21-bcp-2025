package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StreamVsLoopExample {
    
    public static void main(String[] args) {
        System.out.println("=== COMPARACIÓN: STREAMS vs BUCLES TRADICIONALES ===");
        System.out.println("Los streams pueden representar tanto flujos finitos como infinitos de elementos.\n");
        
        // Inicialización de la lista
        List<Product> list = new ArrayList<>();
        list.add(new Product("Laptop", 999.99));
        list.add(new Product("Mouse", 25.50));
        list.add(new Product("Keyboard", 89.99));
        list.add(new Product("Monitor", 299.99));
        list.add(new Product("Headphones", 15.99));
        list.add(new Product("Tablet", 599.99));
        
        System.out.println("=== LISTA ORIGINAL ===");
        list.forEach(System.out::println);
        
        // Ejemplo 1: Bucle tradicional (imperativo)
        System.out.println("\n=== ENFOQUE IMPERATIVO: BUCLE TRADICIONAL ===");
        System.out.println("Código:");
        System.out.println("for (Product p: list) {");
        System.out.println("    if (p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0) {");
        System.out.println("        p.setDiscount(0.2);");
        System.out.println("    }");
        System.out.println("}");
        
        List<Product> listForLoop = new ArrayList<>(list);
        for (Product p: listForLoop) {
            if (p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0) {
                p.setDiscount(0.2);
            }
        }
        
        System.out.println("\nResultado del bucle tradicional:");
        listForLoop.forEach(System.out::println);
        
        // Ejemplo 2: Stream (funcional/declarativo)
        System.out.println("\n=== ENFOQUE FUNCIONAL: JAVA STREAMS ===");
        System.out.println("Código:");
        System.out.println("list.stream().parallel()");
        System.out.println("    .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)");
        System.out.println("    .forEach(p -> p.setDiscount(0.2));");
        
        List<Product> listStream = new ArrayList<>(list);
        listStream.stream().parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)
            .forEach(p -> p.setDiscount(0.2));
        
        System.out.println("\nResultado del stream:");
        listStream.forEach(System.out::println);
        
        // Comparación de resultados
        System.out.println("\n=== COMPARACIÓN DE RESULTADOS ===");
        System.out.println("¿Los resultados son iguales? " + 
            (listForLoop.toString().equals(listStream.toString()) ? "SÍ" : "NO"));
        
        // Ejemplo 3: Stream secuencial vs paralelo
        System.out.println("\n=== STREAM SECUENCIAL vs PARALELO ===");
        
        List<Product> listSequential = new ArrayList<>(list);
        List<Product> listParallel = new ArrayList<>(list);
        
        // Stream secuencial
        long startTime = System.nanoTime();
        listSequential.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)
            .forEach(p -> p.setDiscount(0.2));
        long sequentialTime = System.nanoTime() - startTime;
        
        // Stream paralelo
        startTime = System.nanoTime();
        listParallel.stream().parallel()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)
            .forEach(p -> p.setDiscount(0.2));
        long parallelTime = System.nanoTime() - startTime;
        
        System.out.println("Tiempo stream secuencial: " + sequentialTime + " nanosegundos");
        System.out.println("Tiempo stream paralelo: " + parallelTime + " nanosegundos");
        System.out.println("Mejora: " + String.format("%.2f", (double)sequentialTime/parallelTime) + "x");
        
        // Ejemplo 4: Ventajas de los streams
        System.out.println("\n=== VENTAJAS DE LOS STREAMS ===");
        System.out.println("1. Código más declarativo y legible");
        System.out.println("2. Procesamiento paralelo automático");
        System.out.println("3. Operaciones encadenables (pipeline)");
        System.out.println("4. Lazy evaluation (evaluación perezosa)");
        System.out.println("5. Menos propenso a errores");
        
        // Ejemplo 5: Pipeline de operaciones con streams
        System.out.println("\n=== PIPELINE DE OPERACIONES CON STREAMS ===");
        List<Product> listPipeline = new ArrayList<>(list);
        
        System.out.println("Pipeline: filter -> map -> forEach");
        listPipeline.stream()
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0)  // Solo productos > $50
            .map(p -> {  // Transformar: agregar descuento y mostrar info
                p.setDiscount(0.15);
                return p;
            })
            .forEach(p -> System.out.println("Producto con descuento: " + p.getName() + 
                                           " - Precio: $" + p.getPrice() + 
                                           " - Descuento: " + (p.getDiscount() * 100) + "%"));
    }
} 