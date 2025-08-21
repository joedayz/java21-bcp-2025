package com.bcp.memberinnerclass;

/**
 * Demo que muestra el patrón conceptual de clases anidadas de miembro
 * Demuestra la relación entre instancias de Order y Item
 */
public class MemberInnerClassDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Patrón Conceptual: Member Inner Classes ===\n");
        
        // Simular el diagrama conceptual de la imagen
        System.out.println("📊 DIAGRAMA CONCEPTUAL:");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│           Outer Class Static Context           │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("                        │");
        System.out.println("                        ▼");
        System.out.println("┌─────────────────┐  ┌─────────────────┐");
        System.out.println("│     order1      │  │     order2      │");
        System.out.println("│  Order Instance │  │  Order Instance │");
        System.out.println("│  ┌─────────────┐│  │  ┌─────────────┐│");
        System.out.println("│  │   Item      ││  │  │   Item      ││");
        System.out.println("│  │   Item      ││  │  │   Item      ││");
        System.out.println("│  │   Item      ││  │  │   Item      ││");
        System.out.println("│  └─────────────┘│  │  └─────────────┘│");
        System.out.println("└─────────────────┘  └─────────────────┘");
        System.out.println("   ✅ Cada Item pertenece a su Order específico");
        System.out.println();
        
        // Demostrar el patrón en acción
        System.out.println("🚀 IMPLEMENTACIÓN PRÁCTICA:");
        System.out.println();
        
        // Crear instancias de Order (como en la imagen)
        System.out.println("1. Creando instancias de Order:");
        Order order1 = new Order();
        Order order2 = new Order();
        System.out.println("   order1: " + order1);
        System.out.println("   order2: " + order2);
        System.out.println();
        
        // Agregar items a order1 (como en la imagen)
        System.out.println("2. Agregando items a order1:");
        order1.addItem(new Drink("Tea"), 2);
        order1.addItem(new Food("Cake"), 1);
        System.out.println();
        
        // Agregar items a order2 (como en la imagen)
        System.out.println("3. Agregando items a order2:");
        order2.addItem(new Drink("Tea"), 1);
        System.out.println();
        
        // Mostrar el contenido de las órdenes
        System.out.println("4. Contenido de las órdenes:");
        System.out.println("   order1:");
        order1.displayOrder();
        System.out.println("   order2:");
        order2.displayOrder();
        
        // Demostrar las características de las clases anidadas de miembro
        System.out.println("5. Características de Member Inner Classes:");
        System.out.println("   ✅ Item está asociado a una instancia específica de Order");
        System.out.println("   ✅ Item puede acceder a los miembros de la instancia de Order");
        System.out.println("   ✅ Item no puede existir sin una instancia de Order");
        System.out.println("   ✅ new Item(product, quantity) solo funciona desde dentro de Order");
        System.out.println();
        
        // Demostrar la asociación con instancias específicas
        System.out.println("6. Asociación con instancias específicas:");
        System.out.println("   order1 tiene " + order1.getItems().size() + " items");
        System.out.println("   order2 tiene " + order2.getItems().size() + " items");
        System.out.println("   Cada Item pertenece exclusivamente a su Order");
        System.out.println();
        
        // Demostrar diferencias con static nested classes
        System.out.println("7. Diferencias con Static Nested Classes:");
        System.out.println("   ❌ Member Inner Classes NO son estáticas");
        System.out.println("   ✅ Member Inner Classes están asociadas a instancias");
        System.out.println("   ✅ Member Inner Classes pueden acceder a miembros de instancia");
        System.out.println("   ❌ Member Inner Classes NO pueden tener métodos estáticos");
        System.out.println();
        
        System.out.println("=== Patrón Conceptual Completado ===");
    }
} 