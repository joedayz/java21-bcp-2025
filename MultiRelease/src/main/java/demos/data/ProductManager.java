package demos.data;

/**
 * ProductManager - Clase principal para demostrar Multi-Release JAR
 * Esta clase mostrará qué versión de Java está siendo utilizada
 */
public class ProductManager {
    
    public static void main(String[] args) {
        System.out.println("=== Multi-Release JAR Demo ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println();
        
        Product product = new Product("P001", "Laptop", 999.99);
        System.out.println("Product: " + product);
        System.out.println("Version Info: " + product.getVersionInfo());
        
        System.out.println();
        System.out.println("=== End Demo ===");
    }
}
