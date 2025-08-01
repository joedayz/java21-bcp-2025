package com.bcp.memberinnerclass;

import java.util.HashSet;
import java.util.Set;

/**
 * Ejemplo de clase Order con clase anidada de miembro Item
 * Demuestra cómo las clases anidadas de miembro están asociadas a instancias de la clase externa
 */
public class Order {
    
    private Set<Item> items = new HashSet<>();
    
    /**
     * Método público para agregar items a la orden
     */
    public void addItem(Product product, int quantity) {
        items.add(new Item(product, quantity));
        System.out.println("Item agregado: " + product.getName() + " x" + quantity);
    }
    
    /**
     * Método para obtener todos los items
     */
    public Set<Item> getItems() {
        return new HashSet<>(items); // Retorna una copia para evitar modificación externa
    }
    
    /**
     * Método para calcular el total de la orden
     */
    public double getTotal() {
        return items.stream()
                   .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                   .sum();
    }
    
    /**
     * Método para mostrar el contenido de la orden
     */
    public void displayOrder() {
        System.out.println("=== Contenido de la Orden ===");
        if (items.isEmpty()) {
            System.out.println("La orden está vacía");
        } else {
            items.forEach(item -> {
                System.out.println(item.getProduct().getName() + " x" + item.getQuantity() + 
                                 " - $" + (item.getProduct().getPrice() * item.getQuantity()));
            });
            System.out.println("Total: $" + String.format("%.2f", getTotal()));
        }
        System.out.println();
    }
    
    /**
     * Clase anidada de miembro (member inner class)
     * Está asociada a una instancia específica de Order
     */
    class Item {
        private Product product;
        private int quantity;
        
        /**
         * Constructor privado de Item
         * Solo puede ser llamado desde dentro de la clase Order
         */
        private Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        
        public Product getProduct() {
            return product;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
        public double getSubtotal() {
            return product.getPrice() * quantity;
        }
        
        @Override
        public String toString() {
            return "Item{product=" + product.getName() + ", quantity=" + quantity + 
                   ", subtotal=$" + String.format("%.2f", getSubtotal()) + "}";
        }
        
        // otros métodos de la clase Item
    }
    
    /**
     * Método main para demostrar el uso de la clase anidada de miembro
     */
    public static void main(String[] args) {
        System.out.println("=== Ejemplo Order con Item (Clase Anidada de Miembro) ===\n");
        
        // Crear instancias de Order (como en la imagen)
        System.out.println("1. Creando instancias de Order:");
        System.out.println("   Order order1 = new Order();");
        Order order1 = new Order();
        System.out.println("   Order order2 = new Order();");
        Order order2 = new Order();
        System.out.println();
        
        // Agregar items a order1 (como en la imagen)
        System.out.println("2. Agregando items a order1:");
        System.out.println("   order1.addItem(new Drink(\"Tea\"), 2);");
        order1.addItem(new Drink("Tea"), 2);
        System.out.println("   order1.addItem(new Food(\"Cake\"), 1);");
        order1.addItem(new Food("Cake"), 1);
        System.out.println();
        
        // Agregar items a order2 (como en la imagen)
        System.out.println("3. Agregando items a order2:");
        System.out.println("   order2.addItem(new Drink(\"Tea\"), 1);");
        order2.addItem(new Drink("Tea"), 1);
        System.out.println();
        
        // Mostrar el contenido de las órdenes
        System.out.println("4. Contenido de las órdenes:");
        System.out.println("   order1:");
        order1.displayOrder();
        System.out.println("   order2:");
        order2.displayOrder();
        
        // Demostrar características de las clases anidadas de miembro
        System.out.println("5. Características de las clases anidadas de miembro:");
        System.out.println("   ✅ Cada Item está asociado a una instancia específica de Order");
        System.out.println("   ✅ Item puede acceder a los miembros de la instancia de Order");
        System.out.println("   ✅ Item no puede existir sin una instancia de Order");
        System.out.println("   ✅ new Item(product, quantity) solo funciona desde dentro de Order");
        System.out.println();
        
        // Demostrar que Item está asociado a instancias específicas
        System.out.println("6. Asociación con instancias específicas:");
        System.out.println("   order1 tiene " + order1.getItems().size() + " items");
        System.out.println("   order2 tiene " + order2.getItems().size() + " items");
        System.out.println("   Cada Item pertenece a su Order específico");
        System.out.println();
        
        System.out.println("=== Demo Order con Item Completado ===");
    }
} 