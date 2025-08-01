package com.bcp.staticinnerclass;

/**
 * Ejemplo de clase Order con clase anidada estática ShippingMode
 * Demuestra cómo encapsular funcionalidad relacionada usando clases anidadas estáticas
 */
public class Order {
    
    private static int orderCounter = 0;
    private int orderId;
    private String customerName;
    
    public Order() {
        this.orderId = ++orderCounter;
        this.customerName = "Cliente " + orderId;
    }
    
    public Order(String customerName) {
        this.orderId = ++orderCounter;
        this.customerName = customerName;
    }
    
    /**
     * Método estático público para crear instancias de ShippingMode
     * Esta es la única forma de acceder a la clase privada anidada desde fuera
     */
    public static void createShippingMode(String description) {
        new ShippingMode(description);
        System.out.println("Modo de envío creado: " + description);
    }
    
    /**
     * Método estático que retorna una instancia de ShippingMode
     */
    public static ShippingMode getShippingMode(String description) {
        return new ShippingMode(description);
    }
    
    /**
     * Clase anidada estática privada para representar modos de envío
     * Solo puede ser instanciada desde dentro de la clase Order
     */
    private static class ShippingMode {
        private String description;
        private double cost;
        
        /**
         * Constructor público de ShippingMode
         */
        public ShippingMode(String description) {
            this.description = description;
            // Asignar costo basado en la descripción
            switch (description.toLowerCase()) {
                case "fast":
                    this.cost = 15.99;
                    break;
                case "normal":
                    this.cost = 8.99;
                    break;
                case "economy":
                    this.cost = 4.99;
                    break;
                default:
                    this.cost = 10.00;
            }
            System.out.println("ShippingMode creado: " + description + " - Costo: $" + cost);
        }
        
        public String getDescription() {
            return description;
        }
        
        public double getCost() {
            return cost;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public void setCost(double cost) {
            this.cost = cost;
        }
        
        @Override
        public String toString() {
            return "ShippingMode{description='" + description + "', cost=$" + cost + "}";
        }
        
        // otros métodos y variables de la clase ShippingMode
    }
    
    // Getters y setters para Order
    public int getOrderId() {
        return orderId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", customerName='" + customerName + "'}";
    }
    
    /**
     * Método main para demostrar el uso de la clase anidada estática
     */
    public static void main(String[] args) {
        System.out.println("=== Ejemplo Order con ShippingMode (Clase Anidada Estática) ===\n");
        
        // Ejemplo 1: Crear modos de envío usando el método estático
        System.out.println("1. Creando modos de envío:");
        System.out.println("   Order.createShippingMode(\"Fast\");");
        Order.createShippingMode("Fast");
        
        System.out.println("   Order.createShippingMode(\"Normal\");");
        Order.createShippingMode("Normal");
        
        System.out.println("   Order.createShippingMode(\"Economy\");");
        Order.createShippingMode("Economy");
        System.out.println();
        
        // Ejemplo 2: Obtener instancias de ShippingMode
        System.out.println("2. Obteniendo instancias de ShippingMode:");
        ShippingMode fastShipping = getShippingMode("Fast");
        ShippingMode normalShipping = getShippingMode("Normal");
        
        System.out.println("   Fast shipping: " + fastShipping);
        System.out.println("   Normal shipping: " + normalShipping);
        System.out.println();
        
        // Ejemplo 3: Crear instancias de Order
        System.out.println("3. Creando instancias de Order:");
        System.out.println("   Order order1 = new Order();");
        Order order1 = new Order();
        System.out.println("   Order order2 = new Order(\"Juan Pérez\");");
        Order order2 = new Order("Juan Pérez");
        
        System.out.println("   order1: " + order1);
        System.out.println("   order2: " + order2);
        System.out.println();
        
        // Ejemplo 4: Demostrar que no se puede acceder directamente a ShippingMode
        System.out.println("4. Restricciones de acceso:");
        System.out.println("   ❌ NO se puede hacer: new Order.ShippingMode(\"Fast\");");
        System.out.println("   ❌ ShippingMode es privada, solo accesible desde dentro de Order");
        System.out.println("   ✅ SÍ se puede hacer: Order.createShippingMode(\"Fast\");");
        System.out.println("   ✅ SÍ se puede hacer: Order.getShippingMode(\"Fast\");");
        System.out.println();
        
        System.out.println("=== Demo Order con ShippingMode Completado ===");
    }
} 