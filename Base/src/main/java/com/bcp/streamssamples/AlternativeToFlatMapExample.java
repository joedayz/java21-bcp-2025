package com.bcp.streamssamples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlternativeToFlatMapExample {

    public static void main(String[] args) {
        System.out.println("=== ALTERNATIVAS A FLATMAP ===");
        System.out.println("Demostrando diferentes formas de procesar estructuras anidadas sin usar flatMap\n");

        // Crear órdenes con productos (estructura anidada)
        List<Order> orders = createSampleOrders();

        System.out.println("--- ESTRUCTURA DE DATOS ---");
        System.out.println("Órdenes creadas:");
        orders.forEach(order -> {
            System.out.println("  " + order.getName() + ":");
            order.getItems().forEach(item -> 
                System.out.println("    - " + item.getName() + " $" + item.getPrice()));
        });
        System.out.println();

        // Objetivo: Calcular la suma total de todos los productos "Tea" en todas las órdenes

        // Método 1: BUCLE TRADICIONAL (sin streams)
        System.out.println("=== MÉTODO 1: BUCLE TRADICIONAL ===");
        double totalTeaTraditional = calculateTeaTotalTraditional(orders);
        System.out.println("Total de Tea (bucle tradicional): $" + totalTeaTraditional);

        // Método 2: STREAMS CON MAP Y COLLECT
        System.out.println("\n=== MÉTODO 2: STREAMS CON MAP Y COLLECT ===");
        double totalTeaWithMap = calculateTeaTotalWithMap(orders);
        System.out.println("Total de Tea (map + collect): $" + totalTeaWithMap);

        // Método 3: STREAMS CON REDUCE
        System.out.println("\n=== MÉTODO 3: STREAMS CON REDUCE ===");
        double totalTeaWithReduce = calculateTeaTotalWithReduce(orders);
        System.out.println("Total de Tea (reduce): $" + totalTeaWithReduce);

        // Método 4: STREAMS CON FOREACH
        System.out.println("\n=== MÉTODO 4: STREAMS CON FOREACH ===");
        double totalTeaWithForEach = calculateTeaTotalWithForEach(orders);
        System.out.println("Total de Tea (forEach): $" + totalTeaWithForEach);

        // Método 5: FLATMAP (para comparación)
        System.out.println("\n=== MÉTODO 5: FLATMAP (COMPARACIÓN) ===");
        double totalTeaWithFlatMap = calculateTeaTotalWithFlatMap(orders);
        System.out.println("Total de Tea (flatMap): $" + totalTeaWithFlatMap);

        // Comparación de resultados
        System.out.println("\n=== COMPARACIÓN DE RESULTADOS ===");
        System.out.println("¿Todos los métodos dan el mismo resultado?");
        System.out.println("Bucle tradicional == Map+Collect: " + (totalTeaTraditional == totalTeaWithMap));
        System.out.println("Map+Collect == Reduce: " + (totalTeaWithMap == totalTeaWithReduce));
        System.out.println("Reduce == ForEach: " + (totalTeaWithReduce == totalTeaWithForEach));
        System.out.println("ForEach == FlatMap: " + (totalTeaWithForEach == totalTeaWithFlatMap));

        // Ejemplo adicional: Obtener todos los productos únicos
        System.out.println("\n=== EJEMPLO ADICIONAL: PRODUCTOS ÚNICOS ===");

        // Sin flatMap - usando bucles
        System.out.println("Productos únicos (bucle tradicional):");
        List<String> uniqueProductsTraditional = getUniqueProductsTraditional(orders);
        uniqueProductsTraditional.forEach(System.out::println);

        // Sin flatMap - usando streams
        System.out.println("\nProductos únicos (streams sin flatMap):");
        List<String> uniqueProductsStreams = getUniqueProductsStreams(orders);
        uniqueProductsStreams.forEach(System.out::println);
    }

    // Método 1: Bucle tradicional
    private static double calculateTeaTotalTraditional(List<Order> orders) {
        double total = 0.0;
        for (Order order : orders) {
            for (Product item : order.getItems()) {
                if ("Tea".equals(item.getName())) {
                    total += item.getPrice().doubleValue();
                }
            }
        }
        return total;
    }

    // Método 2: Streams con map y collect
    private static double calculateTeaTotalWithMap(List<Order> orders) {
        List<Double> teaPrices = new ArrayList<>();
        
        orders.stream()
            .map(Order::getItems)  // Stream<List<Product>>
            .forEach(productList -> 
                productList.stream()
                    .filter(item -> "Tea".equals(item.getName()))
                    .mapToDouble(item -> item.getPrice().doubleValue())
                    .forEach(teaPrices::add)
            );
        
        return teaPrices.stream().mapToDouble(Double::doubleValue).sum();
    }

    // Método 3: Streams con reduce
    private static double calculateTeaTotalWithReduce(List<Order> orders) {
        return orders.stream()
            .map(Order::getItems)
            .reduce(0.0, (total, productList) -> {
                double orderTotal = productList.stream()
                    .filter(item -> "Tea".equals(item.getName()))
                    .mapToDouble(item -> item.getPrice().doubleValue())
                    .sum();
                return total + orderTotal;
            }, Double::sum);
    }

    // Método 4: Streams con forEach
    private static double calculateTeaTotalWithForEach(List<Order> orders) {
        final double[] total = {0.0};
        
        orders.stream()
            .map(Order::getItems)
            .forEach(productList -> 
                productList.stream()
                    .filter(item -> "Tea".equals(item.getName()))
                    .forEach(item -> total[0] += item.getPrice().doubleValue())
            );
        
        return total[0];
    }

    // Método 5: FlatMap (para comparación)
    private static double calculateTeaTotalWithFlatMap(List<Order> orders) {
        return orders.stream()
            .flatMap(order -> order.getItems().stream())
            .filter(item -> "Tea".equals(item.getName()))
            .mapToDouble(item -> item.getPrice().doubleValue())
            .sum();
    }

    // Ejemplo adicional: Obtener productos únicos sin flatMap
    private static List<String> getUniqueProductsTraditional(List<Order> orders) {
        List<String> uniqueProducts = new ArrayList<>();
        for (Order order : orders) {
            for (Product item : order.getItems()) {
                if (!uniqueProducts.contains(item.getName())) {
                    uniqueProducts.add(item.getName());
                }
            }
        }
        return uniqueProducts;
    }

    private static List<String> getUniqueProductsStreams(List<Order> orders) {
        List<String> allProducts = new ArrayList<>();
        
        orders.stream()
            .map(Order::getItems)
            .forEach(productList -> 
                productList.stream()
                    .map(Product::getName)
                    .forEach(allProducts::add)
            );
        
        return allProducts.stream()
            .distinct()
            .collect(Collectors.toList());
    }

    // Método auxiliar para crear datos de ejemplo
    private static List<Order> createSampleOrders() {
        List<Order> orders = new ArrayList<>();
        
        // Order 1
        Order order1 = new Order("Order 1");
        order1.addItem(new Product("Tea", 1.99));
        order1.addItem(new Product("Cookie", 1.99));
        order1.addItem(new Product("Cake", 2.99));
        orders.add(order1);
        
        // Order 2
        Order order2 = new Order("Order 2");
        order2.addItem(new Product("Coffee", 1.99));
        order2.addItem(new Product("Tea", 1.99));
        order2.addItem(new Product("Muffin", 2.49));
        orders.add(order2);
        
        // Order 3
        Order order3 = new Order("Order 3");
        order3.addItem(new Product("Tea", 1.99));
        order3.addItem(new Product("Sandwich", 4.99));
        order3.addItem(new Product("Juice", 2.99));
        orders.add(order3);
        
        return orders;
    }

    // Clase Order
    static class Order {
        private String name;
        private List<Product> items;

        public Order(String name) {
            this.name = name;
            this.items = new ArrayList<>();
        }

        public void addItem(Product item) {
            items.add(item);
        }

        public String getName() {
            return name;
        }

        public List<Product> getItems() {
            return items;
        }
    }
} 