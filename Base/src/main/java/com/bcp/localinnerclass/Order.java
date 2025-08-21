package com.bcp.localinnerclass;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Ejemplo de clase Order con clase anidada local OrderTaxManager
 * Demuestra cómo las clases anidadas locales pueden acceder a variables finales o efectivamente finales
 */
public class Order {
    
    private Map<Integer, Item> items = new HashMap<>();
    private static int itemCounter = 0;
    
    /**
     * Método para agregar items a la orden
     */
    public void addItem(Product product, int quantity) {
        items.put(++itemCounter, new Item(product, quantity));
        System.out.println("Item agregado: " + product.getName() + " x" + quantity);
    }
    
    /**
     * Método que contiene la clase anidada local OrderTaxManager
     * El parámetro saleLocation debe ser final o efectivamente final
     */
    public void manageTax(final String saleLocation) {
        System.out.println("Gestionando impuestos para ubicación: " + saleLocation);
        
        // Clase anidada local (local inner class)
        // Definida dentro del método manageTax
        class OrderTaxManager {
            
            /**
             * Método para encontrar la tasa de impuesto
             * Puede acceder a saleLocation porque es final
             */
            private BigDecimal findRate(Product product) {
                // use saleLocation and product to find the tax rate
                System.out.println("Buscando tasa de impuesto para " + product.getName() + 
                                 " en ubicación: " + saleLocation);
                
                // Simular diferentes tasas de impuesto basadas en la ubicación
                BigDecimal rate;
                switch (saleLocation.toLowerCase()) {
                    case "california":
                        rate = new BigDecimal("0.0825"); // 8.25%
                        break;
                    case "new york":
                        rate = new BigDecimal("0.0875"); // 8.75%
                        break;
                    case "texas":
                        rate = new BigDecimal("0.0625"); // 6.25%
                        break;
                    default:
                        rate = new BigDecimal("0.0600"); // 6.00%
                }
                
                // Ajustar tasa según categoría del producto
                if ("Food".equals(product.getCategory())) {
                    rate = rate.multiply(new BigDecimal("0.5")); // Mitad de impuesto para comida
                } else if ("Luxury".equals(product.getCategory())) {
                    rate = rate.multiply(new BigDecimal("1.5")); // 50% más para lujos
                }
                
                System.out.println("Tasa de impuesto encontrada: " + rate.multiply(new BigDecimal("100")) + "%");
                return rate;
            }
            
            /**
             * Método para calcular el impuesto total
             */
            public BigDecimal calculateTax() {
                BigDecimal totalTax = BigDecimal.ZERO;
                
                System.out.println("Calculando impuestos para todos los productos...");
                
                for (Item item : items.values()) {
                    // find tax rate in a given sale location for each product
                    BigDecimal rate = findRate(item.getProduct());
                    
                    // calculate tax value
                    BigDecimal itemSubtotal = new BigDecimal(item.getSubtotal());
                    BigDecimal itemTax = itemSubtotal.multiply(rate);
                    totalTax = totalTax.add(itemTax);
                    
                    System.out.println("  " + item.getProduct().getName() + " x" + item.getQuantity() + 
                                     " - Impuesto: $" + itemTax.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                
                return totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
        
        // Crear instancia de la clase anidada local
        OrderTaxManager taxManager = new OrderTaxManager();
        
        // Calcular el impuesto total
        BigDecimal taxTotal = taxManager.calculateTax();
        
        System.out.println("Impuesto total para " + saleLocation + ": $" + taxTotal);
        System.out.println();
    }
    
    /**
     * Método para mostrar el contenido de la orden
     */
    public void displayOrder() {
        System.out.println("=== Contenido de la Orden ===");
        if (items.isEmpty()) {
            System.out.println("La orden está vacía");
        } else {
            BigDecimal subtotal = BigDecimal.ZERO;
            for (Item item : items.values()) {
                System.out.println(item);
                subtotal = subtotal.add(new BigDecimal(item.getSubtotal()));
            }
            System.out.println("Subtotal: $" + subtotal.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        System.out.println();
    }
    
    /**
     * Método main para demostrar el uso de la clase anidada local
     */
    public static void main(String[] args) {
        System.out.println("=== Ejemplo Order con OrderTaxManager (Clase Anidada Local) ===\n");
        
        // Crear una orden
        Order order = new Order();
        
        // Agregar algunos productos
        System.out.println("1. Agregando productos a la orden:");
        order.addItem(new Product("Laptop", 999.99, "Electronics"), 1);
        order.addItem(new Product("Coffee", 4.50, "Food"), 2);
        order.addItem(new Product("Wine", 45.00, "Luxury"), 1);
        System.out.println();
        
        // Mostrar la orden
        System.out.println("2. Contenido de la orden:");
        order.displayOrder();
        
        // Gestionar impuestos para diferentes ubicaciones
        System.out.println("3. Gestionando impuestos:");
        System.out.println("   order.manageTax(\"California\");");
        order.manageTax("California");
        
        System.out.println("   order.manageTax(\"New York\");");
        order.manageTax("New York");
        
        System.out.println("   order.manageTax(\"Texas\");");
        order.manageTax("Texas");
        
        // Demostrar características de las clases anidadas locales
        System.out.println("4. Características de las clases anidadas locales:");
        System.out.println("   ✅ OrderTaxManager está definida dentro del método manageTax");
        System.out.println("   ✅ Puede acceder a saleLocation porque es final");
        System.out.println("   ✅ Puede acceder a los miembros de la instancia de Order");
        System.out.println("   ✅ Solo existe dentro del método manageTax");
        System.out.println("   ✅ No puede ser accedida desde fuera del método");
        System.out.println();
        
        // Demostrar la regla de variables finales o efectivamente finales
        System.out.println("5. Regla de variables finales o efectivamente finales:");
        System.out.println("   ✅ saleLocation es final, por eso OrderTaxManager puede accederla");
        System.out.println("   ❌ Si saleLocation no fuera final, causaría error de compilación");
        System.out.println("   ✅ Las variables locales deben ser final o efectivamente final");
        System.out.println();
        
        System.out.println("=== Demo Order con OrderTaxManager Completado ===");
    }
} 