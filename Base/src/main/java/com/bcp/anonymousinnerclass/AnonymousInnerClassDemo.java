package com.bcp.anonymousinnerclass;

import java.math.BigDecimal;

/**
 * Demo que muestra la comparación entre implementación de clase separada
 * y implementación de clase anónima interna
 * Como se muestra en la imagen
 */
public class AnonymousInnerClassDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Comparación: Clase Separada vs Clase Anónima Interna ===\n");
        
        // Demo 1: Clase base Order
        System.out.println("1. Clase Base Order:");
        System.out.println("   public class Order {");
        System.out.println("       public BigDecimal getDiscount() {");
        System.out.println("           return BigDecimal.ZERO;");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println();
        
        Order baseOrder = new Order();
        System.out.println("   Resultado: " + baseOrder.getDiscount());
        System.out.println();
        
        // Demo 2: Implementación de clase separada
        System.out.println("2. Separate Class Implementation:");
        System.out.println("   public class OnlineOrder extends Order {");
        System.out.println("       @Override");
        System.out.println("       public BigDecimal getDiscount() {");
        System.out.println("           return BigDecimal.valueOf(0.1);");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println();
        
        OnlineOrder onlineOrder = new OnlineOrder();
        System.out.println("   Resultado: " + onlineOrder.getDiscount());
        onlineOrder.processOnlineOrder();
        System.out.println();
        
        // Demo 3: Implementación de clase anónima interna
        System.out.println("3. Anonymous Inner Class Implementation:");
        System.out.println("   Order order = new Order() {");
        System.out.println("       @Override");
        System.out.println("       public BigDecimal getDiscount() {");
        System.out.println("           return BigDecimal.valueOf(0.1);");
        System.out.println("       }");
        System.out.println("   };");
        System.out.println();
        
        // Implementación exacta como en la imagen
        Order order = new Order() {
            @Override
            public BigDecimal getDiscount() {
                return BigDecimal.valueOf(0.1);
            }
        };
        
        System.out.println("   Resultado: " + order.getDiscount());
        System.out.println();
        
        // Demo 4: Comparación de características
        System.out.println("4. Comparación de Características:");
        System.out.println();
        System.out.println("   📊 Separate Class Implementation:");
        System.out.println("      ✅ Tiene un nombre (OnlineOrder)");
        System.out.println("      ✅ Puede ser reutilizada");
        System.out.println("      ✅ Puede tener métodos adicionales");
        System.out.println("      ✅ Puede ser extendida");
        System.out.println("      ✅ Archivo separado");
        System.out.println();
        System.out.println("   📊 Anonymous Inner Class Implementation:");
        System.out.println("      ❌ No tiene nombre");
        System.out.println("      ❌ No puede ser reutilizada");
        System.out.println("      ❌ Solo puede sobrescribir métodos");
        System.out.println("      ❌ No puede ser extendida");
        System.out.println("      ✅ Definida inline");
        System.out.println();
        
        // Demo 5: Casos de uso
        System.out.println("5. Casos de Uso:");
        System.out.println();
        System.out.println("   🔧 Separate Class: Cuando necesitas:");
        System.out.println("      - Reutilizar la implementación");
        System.out.println("      - Agregar métodos específicos");
        System.out.println("      - Crear una jerarquía de clases");
        System.out.println("      - Testing unitario");
        System.out.println();
        System.out.println("   🔧 Anonymous Inner Class: Cuando necesitas:");
        System.out.println("      - Implementación única y temporal");
        System.out.println("      - Callbacks o listeners");
        System.out.println("      - Sobrescribir métodos específicos");
        System.out.println("      - Código más conciso");
        System.out.println();
        
        // Demo 6: Ejemplos prácticos adicionales
        System.out.println("6. Ejemplos Prácticos Adicionales:");
        System.out.println();
        
        // Ejemplo con diferentes descuentos usando clases anónimas
        System.out.println("   Diferentes descuentos usando clases anónimas:");
        
        Order vipOrder = new Order() {
            @Override
            public BigDecimal getDiscount() {
                return BigDecimal.valueOf(0.25); // 25% descuento VIP
            }
        };
        
        Order bulkOrder = new Order() {
            @Override
            public BigDecimal getDiscount() {
                return BigDecimal.valueOf(0.15); // 15% descuento por volumen
            }
        };
        
        System.out.println("   VIP Order descuento: " + vipOrder.getDiscount());
        System.out.println("   Bulk Order descuento: " + bulkOrder.getDiscount());
        System.out.println();
        
        System.out.println("=== Demo Completado ===");
    }
} 