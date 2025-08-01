package com.bcp.anonymousinnerclass;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Ejemplo que compara clases anónimas con expresiones lambda
 */
public class LambdaComparison {
    
    /**
     * Interfaz funcional simple
     */
    @FunctionalInterface
    public interface DiscountCalculator {
        double calculateDiscount(double amount);
    }
    
    /**
     * Interfaz funcional con múltiples métodos (no funcional)
     */
    public interface OrderProcessor {
        void processOrder();
        String getStatus();
    }
    
    /**
     * Método que demuestra la comparación entre clases anónimas y lambdas
     */
    public static void demonstrateComparison() {
        System.out.println("=== Comparación: Clases Anónimas vs Lambdas ===\n");
        
        // Demo 1: Interfaz funcional con clase anónima
        System.out.println("1. Interfaz Funcional - Clase Anónima:");
        System.out.println("   DiscountCalculator anonymousCalculator = new DiscountCalculator() {");
        System.out.println("       @Override");
        System.out.println("       public double calculateDiscount(double amount) {");
        System.out.println("           return amount * 0.1; // 10% descuento");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        DiscountCalculator anonymousCalculator = new DiscountCalculator() {
            @Override
            public double calculateDiscount(double amount) {
                return amount * 0.1; // 10% descuento
            }
        };
        
        System.out.println("   Descuento calculado: $" + anonymousCalculator.calculateDiscount(100.0));
        System.out.println();
        
        // Demo 2: Interfaz funcional con lambda
        System.out.println("2. Interfaz Funcional - Lambda:");
        System.out.println("   DiscountCalculator lambdaCalculator = amount -> amount * 0.15; // 15% descuento");
        System.out.println();
        
        DiscountCalculator lambdaCalculator = amount -> amount * 0.15; // 15% descuento
        
        System.out.println("   Descuento calculado: $" + lambdaCalculator.calculateDiscount(100.0));
        System.out.println();
        
        // Demo 3: Interfaz no funcional (solo clase anónima)
        System.out.println("3. Interfaz No Funcional (solo clase anónima):");
        System.out.println("   OrderProcessor processor = new OrderProcessor() {");
        System.out.println("       @Override");
        System.out.println("       public void processOrder() {");
        System.out.println("           System.out.println(\"Procesando orden...\");");
        System.out.println("       }");
        System.out.println("       @Override");
        System.out.println("       public String getStatus() {");
        System.out.println("           return \"Completado\";");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        OrderProcessor processor = new OrderProcessor() {
            @Override
            public void processOrder() {
                System.out.println("Procesando orden...");
            }
            
            @Override
            public String getStatus() {
                return "Completado";
            }
        };
        
        processor.processOrder();
        System.out.println("   Estado: " + processor.getStatus());
        System.out.println();
        
        // Demo 4: Interfaces funcionales del JDK
        System.out.println("4. Interfaces Funcionales del JDK:");
        System.out.println();
        
        // Consumer con clase anónima
        System.out.println("   Consumer con clase anónima:");
        Consumer<String> anonymousConsumer = new Consumer<String>() {
            @Override
            public void accept(String message) {
                System.out.println("Mensaje recibido: " + message);
            }
        };
        anonymousConsumer.accept("Hola desde clase anónima");
        
        // Consumer con lambda
        System.out.println("   Consumer con lambda:");
        Consumer<String> lambdaConsumer = message -> System.out.println("Mensaje recibido: " + message);
        lambdaConsumer.accept("Hola desde lambda");
        System.out.println();
        
        // Predicate con clase anónima
        System.out.println("   Predicate con clase anónima:");
        Predicate<Integer> anonymousPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number > 10;
            }
        };
        System.out.println("   ¿15 > 10? " + anonymousPredicate.test(15));
        
        // Predicate con lambda
        System.out.println("   Predicate con lambda:");
        Predicate<Integer> lambdaPredicate = number -> number > 10;
        System.out.println("   ¿5 > 10? " + lambdaPredicate.test(5));
        System.out.println();
        
        // Demo 5: Ventajas y desventajas
        System.out.println("5. Ventajas y Desventajas:");
        System.out.println();
        System.out.println("   📊 Clases Anónimas:");
        System.out.println("      ✅ Pueden implementar interfaces con múltiples métodos");
        System.out.println("      ✅ Pueden extender clases");
        System.out.println("      ✅ Pueden tener estado (campos)");
        System.out.println("      ✅ Sintaxis más verbosa");
        System.out.println("      ✅ Más flexibles");
        System.out.println();
        System.out.println("   📊 Lambdas:");
        System.out.println("      ✅ Sintaxis más concisa");
        System.out.println("      ✅ Solo para interfaces funcionales");
        System.out.println("      ✅ No pueden tener estado");
        System.out.println("      ✅ Mejor rendimiento");
        System.out.println("      ✅ Más legibles para operaciones simples");
        System.out.println();
        
        // Demo 6: Casos de uso
        System.out.println("6. Casos de Uso:");
        System.out.println();
        System.out.println("   🔧 Usar Clases Anónimas cuando:");
        System.out.println("      - Necesitas implementar múltiples métodos");
        System.out.println("      - Necesitas extender una clase");
        System.out.println("      - Necesitas mantener estado");
        System.out.println("      - Necesitas lógica compleja");
        System.out.println();
        System.out.println("   🔧 Usar Lambdas cuando:");
        System.out.println("      - La interfaz es funcional");
        System.out.println("      - La lógica es simple");
        System.out.println("      - No necesitas estado");
        System.out.println("      - Quieres código más legible");
        System.out.println();
        
        System.out.println("=== Comparación Completada ===");
    }
    
    /**
     * Método main para ejecutar el ejemplo
     */
    public static void main(String[] args) {
        demonstrateComparison();
    }
} 