package com.bcp.anonymousinnerclass;

/**
 * Ejemplo que demuestra el uso de clases anónimas con interfaces
 */
public class InterfaceExample {
    
    /**
     * Interfaz para procesadores de pago
     */
    public interface PaymentProcessor {
        void processPayment(double amount);
        String getProcessorName();
    }
    
    /**
     * Interfaz para validadores de orden
     */
    public interface OrderValidator {
        boolean validateOrder();
        String getValidationMessage();
    }
    
    /**
     * Método que demuestra el uso de clases anónimas con interfaces
     */
    public static void demonstrateInterfaceAnonymousClasses() {
        System.out.println("=== Ejemplo: Clases Anónimas con Interfaces ===\n");
        
        // Demo 1: Clase anónima implementando PaymentProcessor
        System.out.println("1. Clase Anónima con PaymentProcessor:");
        System.out.println("   PaymentProcessor creditCardProcessor = new PaymentProcessor() {");
        System.out.println("       @Override");
        System.out.println("       public void processPayment(double amount) {");
        System.out.println("           System.out.println(\"Procesando pago con tarjeta: $\" + amount);");
        System.out.println("       }");
        System.out.println("       @Override");
        System.out.println("       public String getProcessorName() {");
        System.out.println("           return \"Credit Card Processor\";");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        PaymentProcessor creditCardProcessor = new PaymentProcessor() {
            @Override
            public void processPayment(double amount) {
                System.out.println("Procesando pago con tarjeta: $" + amount);
            }
            
            @Override
            public String getProcessorName() {
                return "Credit Card Processor";
            }
        };
        
        System.out.println("   Usando: " + creditCardProcessor.getProcessorName());
        creditCardProcessor.processPayment(99.99);
        System.out.println();
        
        // Demo 2: Clase anónima implementando OrderValidator
        System.out.println("2. Clase Anónima con OrderValidator:");
        System.out.println("   OrderValidator basicValidator = new OrderValidator() {");
        System.out.println("       @Override");
        System.out.println("       public boolean validateOrder() {");
        System.out.println("           return true; // Validación básica");
        System.out.println("       }");
        System.out.println("       @Override");
        System.out.println("       public String getValidationMessage() {");
        System.out.println("           return \"Orden válida\";");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        OrderValidator basicValidator = new OrderValidator() {
            @Override
            public boolean validateOrder() {
                return true; // Validación básica
            }
            
            @Override
            public String getValidationMessage() {
                return "Orden válida";
            }
        };
        
        System.out.println("   Validación: " + basicValidator.getValidationMessage());
        System.out.println("   Resultado: " + basicValidator.validateOrder());
        System.out.println();
        
        // Demo 3: Múltiples implementaciones anónimas
        System.out.println("3. Múltiples Implementaciones Anónimas:");
        
        PaymentProcessor paypalProcessor = new PaymentProcessor() {
            @Override
            public void processPayment(double amount) {
                System.out.println("Procesando pago con PayPal: $" + amount);
            }
            
            @Override
            public String getProcessorName() {
                return "PayPal Processor";
            }
        };
        
        OrderValidator strictValidator = new OrderValidator() {
            @Override
            public boolean validateOrder() {
                // Simular validación estricta
                return Math.random() > 0.3; // 70% de probabilidad de éxito
            }
            
            @Override
            public String getValidationMessage() {
                return "Validación estricta completada";
            }
        };
        
        System.out.println("   PayPal: " + paypalProcessor.getProcessorName());
        paypalProcessor.processPayment(150.00);
        System.out.println("   Validación estricta: " + strictValidator.getValidationMessage());
        System.out.println("   Resultado: " + strictValidator.validateOrder());
        System.out.println();
        
        // Demo 4: Uso en métodos
        System.out.println("4. Uso en Métodos:");
        processOrderWithValidator(basicValidator, 75.50);
        processOrderWithValidator(strictValidator, 200.00);
        System.out.println();
        
        System.out.println("=== Ejemplo Completado ===");
    }
    
    /**
     * Método que usa un validador de orden
     */
    private static void processOrderWithValidator(OrderValidator validator, double amount) {
        System.out.println("   Procesando orden de $" + amount);
        if (validator.validateOrder()) {
            System.out.println("   ✅ " + validator.getValidationMessage());
        } else {
            System.out.println("   ❌ Orden rechazada");
        }
    }
    
    /**
     * Método main para ejecutar el ejemplo
     */
    public static void main(String[] args) {
        demonstrateInterfaceAnonymousClasses();
    }
} 