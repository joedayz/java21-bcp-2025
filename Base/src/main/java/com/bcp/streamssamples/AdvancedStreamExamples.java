package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvancedStreamExamples {
    
    public static void main(String[] args) {
        System.out.println("=== EJEMPLOS AVANZADOS DE STREAMS ===");
        System.out.println("Basado en el código de la imagen\n");
        
        // Ejemplo 1: Stream infinito con takeWhile y sum
        System.out.println("=== STREAM INFINITO CON TAKEWHILE ===");
        System.out.println("Código: IntStream.generate(() -> (int) (Math.random() * 10)).takeWhile(n -> n != 3).sum()");
        
        int result = IntStream.generate(() -> (int) (Math.random() * 10))
            .takeWhile(n -> n != 3)
            .sum();
        
        System.out.println("Resultado: " + result);
        System.out.println("Explicación: Genera números aleatorios del 0-9 hasta encontrar un 3, luego suma todos los números generados");
        
        // Ejemplo 2: Stream de objetos con forEach
        System.out.println("\n=== STREAM DE OBJETOS CON FOREACH ===");
        System.out.println("Código: Stream.of(new Food(), new Drink()).forEach(p -> p.setPrice(1))");
        
        Food food1 = new Food("Pizza", 10.0);
        Drink drink1 = new Drink("Coffee", 5.0);
        
        Stream.of(food1, drink1).forEach(p -> p.setPrice(BigDecimal.valueOf(1.0)));
        
        System.out.println("Después de setPrice(1):");
        System.out.println("Food: " + food1);
        System.out.println("Drink: " + drink1);
        
        // Ejemplo 3: Creación de listas y arrays
        System.out.println("\n=== CREACIÓN DE LISTAS Y ARRAYS ===");
        System.out.println("Código: List<Product> list = new ArrayList()");
        System.out.println("Código: Product[] array = {new Drink(), new Food()}");
        
        List<Product> list = new ArrayList<>();
        list.add(new Food("Burger", 8.0));
        list.add(new Drink("Tea", 3.0));
        list.add(new Food("Salad", 6.0));
        
        Product[] array = {new Drink("Juice", 4.0), new Food("Sandwich", 7.0)};
        
        System.out.println("Lista creada: " + list);
        System.out.println("Array creado: " + Arrays.toString(array));
        
        // Ejemplo 4: Stream paralelo con mapToDouble y sum
        System.out.println("\n=== STREAM PARALELO CON MAPTODOUBLE ===");
        System.out.println("Código: list.stream().parallel().mapToDouble(p -> p.getPrice()).sum()");
        
        double totalPrice = list.stream()
            .parallel()
            .mapToDouble(p -> p.getPrice().doubleValue())
            .sum();
        
        System.out.println("Precio total (paralelo): $" + totalPrice);
        
        // Comparación con stream secuencial
        double totalPriceSequential = list.stream()
            .mapToDouble(p -> p.getPrice().doubleValue())
            .sum();
        
        System.out.println("Precio total (secuencial): $" + totalPriceSequential);
        
        // Ejemplo 5: Arrays.stream con filter y forEach
        System.out.println("\n=== ARRAYS.STREAM CON FILTER ===");
        System.out.println("Código: Arrays.stream(array).filter(p -> p.getPrice() > 2).forEach(p -> p.setDiscount(0.1))");
        
        System.out.println("Array antes del filtro:");
        Arrays.stream(array).forEach(System.out::println);
        
        Arrays.stream(array)
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) > 0)
            .forEach(p -> p.setDiscount(0.1));
        
        System.out.println("Array después del filtro y descuento:");
        Arrays.stream(array).forEach(System.out::println);
        
        // Ejemplo 6: Diferentes formas de crear streams
        System.out.println("\n=== DIFERENTES FORMAS DE CREAR STREAMS ===");
        
        // Stream desde colección
        System.out.println("1. Stream desde List:");
        list.stream().forEach(p -> System.out.println("  " + p.getName()));
        
        // Stream desde array
        System.out.println("2. Stream desde Array:");
        Arrays.stream(array).forEach(p -> System.out.println("  " + p.getName()));
        
        // Stream de elementos específicos
        System.out.println("3. Stream.of():");
        Stream.of("Apple", "Banana", "Orange").forEach(System.out::println);
        
        // Stream infinito
        System.out.println("4. Stream infinito (primeros 5 elementos):");
        IntStream.iterate(1, n -> n + 1)
            .limit(5)
            .forEach(System.out::println);
        
        // Stream con generate
        System.out.println("5. Stream.generate() (primeros 3 elementos):");
        Stream.generate(() -> "Elemento generado")
            .limit(3)
            .forEach(System.out::println);
        
        // Ejemplo 7: Operaciones de short-circuiting
        System.out.println("\n=== OPERACIONES DE SHORT-CIRCUITING ===");
        
        // takeWhile - toma elementos mientras se cumple la condición
        System.out.println("takeWhile (números < 5):");
        IntStream.range(1, 10)
            .takeWhile(n -> n < 5)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // dropWhile - descarta elementos mientras se cumple la condición
        System.out.println("dropWhile (números < 5):");
        IntStream.range(1, 10)
            .dropWhile(n -> n < 5)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // findFirst - encuentra el primer elemento
        System.out.println("findFirst (> 3):");
        int first = IntStream.range(1, 10)
            .filter(n -> n > 3)
            .findFirst()
            .orElse(-1);
        System.out.println("Primer número > 3: " + first);
        
        // Ejemplo 8: Streams especializados
        System.out.println("\n=== STREAMS ESPECIALIZADOS ===");
        
        // IntStream
        System.out.println("IntStream operations:");
        int sum = IntStream.range(1, 6).sum();
        System.out.println("Suma del 1 al 5: " + sum);
        
        double avg = IntStream.range(1, 6).average().orElse(0.0);
        System.out.println("Promedio del 1 al 5: " + avg);
        
        // Ejemplo 9: Pipeline complejo con streams
        System.out.println("\n=== PIPELINE COMPLEJO ===");
        System.out.println("Procesamiento: generar números -> filtrar pares -> mapear al cuadrado -> sumar");
        
        int complexResult = IntStream.generate(() -> (int) (Math.random() * 20))
            .limit(10)
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .sum();
        
        System.out.println("Resultado del pipeline complejo: " + complexResult);
    }
} 