package com.bcp.staticinnerclass;

/**
 * Demo que muestra el patrón conceptual de la imagen
 * Demuestra la relación entre instancias de Order y ShippingMode
 */
public class OrderPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Patrón Conceptual: Order con ShippingMode ===\n");
        
        // Simular el diagrama conceptual de la imagen
        System.out.println("📊 DIAGRAMA CONCEPTUAL:");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                Order Class Scope               │");
        System.out.println("│  ┌─────────────────┐  ┌─────────────────┐     │");
        System.out.println("│  │  ShippingMode   │  │  ShippingMode   │     │");
        System.out.println("│  │    \"Fast\"       │  │   \"Normal\"      │     │");
        System.out.println("│  │   Cost: $15.99  │  │   Cost: $8.99   │     │");
        System.out.println("│  └─────────────────┘  └─────────────────┘     │");
        System.out.println("│           ✅ (Acceso permitido)                │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("           ❌ (Acceso restringido)");
        System.out.println("┌─────────────────┐  ┌─────────────────┐");
        System.out.println("│     order1      │  │     order2      │");
        System.out.println("│  Order Instance │  │  Order Instance │");
        System.out.println("└─────────────────┘  └─────────────────┘");
        System.out.println();
        
        // Demostrar el patrón en acción
        System.out.println("🚀 IMPLEMENTACIÓN PRÁCTICA:");
        System.out.println();
        
        // Crear modos de envío (como en la imagen)
        System.out.println("1. Creando modos de envío (ShippingMode instances):");
        Order.createShippingMode("Fast");
        Order.createShippingMode("Normal");
        System.out.println();
        
        // Crear órdenes (como en la imagen)
        System.out.println("2. Creando órdenes (Order instances):");
        Order order1 = new Order();
        Order order2 = new Order("Cliente VIP");
        System.out.println("   order1: " + order1);
        System.out.println("   order2: " + order2);
        System.out.println();
        
        // Demostrar las restricciones de acceso
        System.out.println("3. Restricciones de acceso:");
        System.out.println("   ✅ Order.createShippingMode(\"Fast\") - PERMITIDO");
        System.out.println("   ❌ new Order.ShippingMode(\"Fast\") - DENEGADO");
        System.out.println();
        
        // Mostrar el patrón de encapsulación
        System.out.println("4. Patrón de encapsulación:");
        System.out.println("   - ShippingMode está encapsulado dentro de Order");
        System.out.println("   - Solo se puede acceder a través de métodos públicos de Order");
        System.out.println("   - Las instancias de Order no 'contienen' ShippingMode");
        System.out.println("   - ShippingMode es independiente de las instancias de Order");
        System.out.println();
        
        // Demostrar uso práctico usando solo métodos públicos
        System.out.println("5. Uso práctico (solo métodos públicos):");
        System.out.println("   Order.createShippingMode(\"Fast\");");
        System.out.println("   Order.createShippingMode(\"Normal\");");
        System.out.println("   Order.createShippingMode(\"Economy\");");
        System.out.println();
        
        System.out.println("=== Patrón Conceptual Completado ===");
    }
} 