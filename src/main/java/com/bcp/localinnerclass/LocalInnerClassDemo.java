package com.bcp.localinnerclass;

/**
 * Demo que muestra el patrón conceptual de clases anidadas locales
 * Demuestra la relación entre contextos de clase externa y clases anidadas locales
 */
public class LocalInnerClassDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Patrón Conceptual: Local Inner Classes ===\n");
        
        // Simular el diagrama conceptual de la imagen
        System.out.println("📊 DIAGRAMA CONCEPTUAL:");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│           Outer Class Static Context           │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("                        │");
        System.out.println("                        ▼");
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│          Outer Class Instance Context          │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("                        │");
        System.out.println("                        ▼");
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│              Local Inner Class                 │");
        System.out.println("│  ┌─────────────────────────────────────────┐   │");
        System.out.println("│  │         OrderTaxManager                 │   │");
        System.out.println("│  │  ✅ Accede a variables finales          │   │");
        System.out.println("│  │  ✅ Accede a miembros de instancia      │   │");
        System.out.println("│  │  ✅ Solo existe en el método            │   │");
        System.out.println("│  └─────────────────────────────────────────┘   │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
        
        // Demostrar el patrón en acción
        System.out.println("🚀 IMPLEMENTACIÓN PRÁCTICA:");
        System.out.println();
        
        // Crear una orden
        Order order = new Order();
        
        // Agregar productos
        System.out.println("1. Creando orden con productos:");
        order.addItem(new Product("Laptop", 999.99, "Electronics"), 1);
        order.addItem(new Product("Coffee", 4.50, "Food"), 2);
        order.addItem(new Product("Wine", 45.00, "Luxury"), 1);
        System.out.println();
        
        // Mostrar la orden
        System.out.println("2. Contenido de la orden:");
        order.displayOrder();
        
        // Gestionar impuestos (esto crea la clase anidada local)
        System.out.println("3. Gestionando impuestos (creando OrderTaxManager localmente):");
        System.out.println("   order.manageTax(\"California\");");
        order.manageTax("California");
        
        System.out.println("   order.manageTax(\"New York\");");
        order.manageTax("New York");
        System.out.println();
        
        // Demostrar las características de las clases anidadas locales
        System.out.println("4. Características de Local Inner Classes:");
        System.out.println("   ✅ OrderTaxManager está definida dentro del método manageTax");
        System.out.println("   ✅ Puede acceder a saleLocation porque es final");
        System.out.println("   ✅ Puede acceder a los miembros de la instancia de Order");
        System.out.println("   ✅ Solo existe dentro del método manageTax");
        System.out.println("   ✅ No puede ser accedida desde fuera del método");
        System.out.println();
        
        // Demostrar la regla de variables finales
        System.out.println("5. Regla de Variables Finales o Efectivamente Finales:");
        System.out.println("   🔴 Outer method local variables and parameters can only be accessed");
        System.out.println("      if they are final or effectively final.");
        System.out.println();
        System.out.println("   ✅ saleLocation es final → OrderTaxManager puede accederla");
        System.out.println("   ❌ Si saleLocation no fuera final → Error de compilación");
        System.out.println("   ✅ Variables locales deben ser final o efectivamente final");
        System.out.println();
        
        // Demostrar el alcance de la clase anidada local
        System.out.println("6. Alcance de la Clase Anidada Local:");
        System.out.println("   📍 OrderTaxManager solo existe dentro de manageTax()");
        System.out.println("   📍 Se crea cada vez que se llama manageTax()");
        System.out.println("   📍 Se destruye cuando manageTax() termina");
        System.out.println("   📍 No puede ser reutilizada fuera del método");
        System.out.println();
        
        System.out.println("=== Patrón Conceptual Completado ===");
    }
} 