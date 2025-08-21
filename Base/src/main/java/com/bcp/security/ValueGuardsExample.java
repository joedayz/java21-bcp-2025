package com.bcp.security;

import java.util.Optional;
import java.util.Random;

/**
 * Ejemplo de Erroneous Value Guards
 * 
 * Demuestra cómo usar las protecciones de Java contra valores problemáticos:
 * 1. Desbordamiento numérico
 * 2. Valores de punto flotante incorrectos
 * 3. Referencias nulas
 */
public class ValueGuardsExample {
    
    /**
     * Clase que demuestra cálculos seguros con validaciones
     */
    public static class SecureCalculator {
        
        /**
         * Suma segura que detecta desbordamiento
         */
        public static int safeAdd(int a, int b) {
            try {
                return Math.addExact(a, b);
            } catch (ArithmeticException e) {
                System.err.println("⚠️  Desbordamiento detectado: " + a + " + " + b);
                throw new SecurityException("Operación aritmética insegura", e);
            }
        }
        
        /**
         * Multiplicación segura que detecta desbordamiento
         */
        public static int safeMultiply(int a, int b) {
            try {
                return Math.multiplyExact(a, b);
            } catch (ArithmeticException e) {
                System.err.println("⚠️  Desbordamiento detectado: " + a + " * " + b);
                throw new SecurityException("Operación aritmética insegura", e);
            }
        }
        
        /**
         * División segura que valida resultados de punto flotante
         */
        public static double safeDivide(double a, double b) {
            if (b == 0.0) {
                throw new ArithmeticException("División por cero");
            }
            
            double result = a / b;
            
            // Validar resultados especiales
            if (Double.isInfinite(result)) {
                System.err.println("⚠️  Resultado infinito: " + a + " / " + b);
                throw new SecurityException("Resultado infinito no permitido");
            }
            
            if (Double.isNaN(result)) {
                System.err.println("⚠️  Resultado NaN: " + a + " / " + b);
                throw new SecurityException("Resultado NaN no permitido");
            }
            
            return result;
        }
    }
    
    /**
     * Clase que demuestra manejo seguro de valores opcionales
     */
    public static class SecureDataProcessor {
        
        /**
         * Procesa un nombre de forma segura usando Optional
         */
        public static void processNameSafely(Optional<String> nameOpt) {
            nameOpt.ifPresentOrElse(
                name -> {
                    System.out.println("Procesando nombre: " + name);
                    System.out.println("Longitud: " + name.length());
                },
                () -> System.out.println("No hay nombre para procesar")
            );
        }
        
        /**
         * Obtiene la longitud de un nombre de forma segura
         */
        public static Optional<Integer> getSafeNameLength(Optional<String> nameOpt) {
            return nameOpt.map(String::length);
        }
        
        /**
         * Valida un nombre antes de procesarlo
         */
        public static Optional<String> validateName(String name) {
            if (name == null || name.trim().isEmpty()) {
                return Optional.empty();
            }
            
            // Validaciones adicionales
            if (name.length() > 100) {
                System.err.println("⚠️  Nombre demasiado largo: " + name.length() + " caracteres");
                return Optional.empty();
            }
            
            if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.err.println("⚠️  Nombre contiene caracteres inválidos: " + name);
                return Optional.empty();
            }
            
            return Optional.of(name.trim());
        }
    }
    
    /**
     * Clase que demuestra todos los conceptos
     */
    public static class ValueGuardsDemo {
        
        public static void demonstrateOverflowProtection() {
            System.out.println("=== PROTECCIÓN CONTRA DESBORDAMIENTO ===\n");
            
            // Caso normal
            try {
                int result = SecureCalculator.safeAdd(100, 200);
                System.out.println("✅ Suma normal: 100 + 200 = " + result);
            } catch (Exception e) {
                System.err.println("❌ Error inesperado: " + e.getMessage());
            }
            
            // Caso de desbordamiento
            try {
                int result = SecureCalculator.safeAdd(Integer.MAX_VALUE, 1);
                System.out.println("❌ Esto no debería ejecutarse: " + result);
            } catch (SecurityException e) {
                System.out.println("✅ Desbordamiento capturado: " + e.getMessage());
            }
            
            // Multiplicación que desborda
            try {
                int result = SecureCalculator.safeMultiply(1000000, 1000000);
                System.out.println("❌ Esto no debería ejecutarse: " + result);
            } catch (SecurityException e) {
                System.out.println("✅ Desbordamiento en multiplicación capturado: " + e.getMessage());
            }
        }
        
        public static void demonstrateFloatingPointProtection() {
            System.out.println("\n=== PROTECCIÓN CONTRA VALORES DE PUNTO FLOTANTE ===\n");
            
            // Caso normal
            try {
                double result = SecureCalculator.safeDivide(10.0, 2.0);
                System.out.println("✅ División normal: 10.0 / 2.0 = " + result);
            } catch (Exception e) {
                System.err.println("❌ Error inesperado: " + e.getMessage());
            }
            
            // División por cero
            try {
                double result = SecureCalculator.safeDivide(10.0, 0.0);
                System.out.println("❌ Esto no debería ejecutarse: " + result);
            } catch (ArithmeticException e) {
                System.out.println("✅ División por cero capturada: " + e.getMessage());
            }
            
            // Caso que produce infinito
            try {
                double result = SecureCalculator.safeDivide(1.0, Double.MIN_VALUE);
                System.out.println("❌ Esto no debería ejecutarse: " + result);
            } catch (SecurityException e) {
                System.out.println("✅ Infinito capturado: " + e.getMessage());
            }
            
            // Caso que produce NaN
            try {
                double result = SecureCalculator.safeDivide(0.0, 0.0);
                System.out.println("❌ Esto no debería ejecutarse: " + result);
            } catch (SecurityException e) {
                System.out.println("✅ NaN capturado: " + e.getMessage());
            }
        }
        
        public static void demonstrateNullProtection() {
            System.out.println("\n=== PROTECCIÓN CONTRA REFERENCIAS NULAS ===\n");
            
            // Caso normal
            Optional<String> validName = SecureDataProcessor.validateName("Juan Pérez");
            SecureDataProcessor.processNameSafely(validName);
            
            // Nombre nulo
            Optional<String> nullName = SecureDataProcessor.validateName(null);
            SecureDataProcessor.processNameSafely(nullName);
            
            // Nombre vacío
            Optional<String> emptyName = SecureDataProcessor.validateName("");
            SecureDataProcessor.processNameSafely(emptyName);
            
            // Nombre con caracteres inválidos
            Optional<String> invalidName = SecureDataProcessor.validateName("Juan123");
            SecureDataProcessor.processNameSafely(invalidName);
            
            // Nombre demasiado largo
            String longName = "A".repeat(150);
            Optional<String> tooLongName = SecureDataProcessor.validateName(longName);
            SecureDataProcessor.processNameSafely(tooLongName);
            
            // Obtener longitud de forma segura
            System.out.println("\n--- Obtener longitud de forma segura ---");
            Optional<Integer> length1 = SecureDataProcessor.getSafeNameLength(validName);
            length1.ifPresent(l -> System.out.println("Longitud del nombre válido: " + l));
            
            Optional<Integer> length2 = SecureDataProcessor.getSafeNameLength(nullName);
            length2.ifPresent(l -> System.out.println("Longitud del nombre nulo: " + l));
            if (length2.isEmpty()) {
                System.out.println("No hay longitud para nombre nulo");
            }
        }
        
        public static void demonstrateRealWorldScenario() {
            System.out.println("\n=== ESCENARIO DEL MUNDO REAL ===\n");
            
            // Simular procesamiento de datos de usuario
            String[] userInputs = {
                "Juan Pérez",           // Válido
                null,                   // Nulo
                "",                     // Vacío
                "María123",             // Caracteres inválidos
                "A".repeat(200),        // Demasiado largo
                "Ana García"            // Válido
            };
            
            System.out.println("Procesando datos de usuario:");
            for (String input : userInputs) {
                Optional<String> validatedName = SecureDataProcessor.validateName(input);
                System.out.print("Input: '" + input + "' -> ");
                
                if (validatedName.isPresent()) {
                    System.out.println("✅ Válido");
                    SecureDataProcessor.processNameSafely(validatedName);
                } else {
                    System.out.println("❌ Inválido");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🛡️  DEMO: GUARDAS DE VALORES ERRÓNEOS");
        System.out.println("=====================================\n");
        
        // Demostrar todas las protecciones
        ValueGuardsDemo.demonstrateOverflowProtection();
        ValueGuardsDemo.demonstrateFloatingPointProtection();
        ValueGuardsDemo.demonstrateNullProtection();
        ValueGuardsDemo.demonstrateRealWorldScenario();
        
        System.out.println("\n🎯 BENEFICIOS DE LAS GUARDAS:");
        System.out.println("✅ Previenen errores en tiempo de ejecución");
        System.out.println("✅ Detectan ataques de desbordamiento");
        System.out.println("✅ Manejan valores especiales de punto flotante");
        System.out.println("✅ Eliminan NullPointerException");
        System.out.println("✅ Hacen el código más robusto y seguro");
    }
}
