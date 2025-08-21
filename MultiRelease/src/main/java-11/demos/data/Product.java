package demos.data;

import java.util.Objects;
import java.util.List;

/**
 * Clase Product - Versión específica para Java 11
 * Esta versión aprovecha las características de Java 11 como String methods
 */
public class Product {
    private final String id;
    private final String name;
    private final double price;
    
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f}", id, name, price);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0 &&
               Objects.equals(id, product.id) &&
               Objects.equals(name, product.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
    
    /**
     * Método que demuestra la versión de Java en uso
     * En Java 11, este método usa String methods (característica de Java 11)
     */
    public String getVersionInfo() {
        var features = List.of("String methods", "HTTP Client", "Local Variable Syntax for Lambda Parameters");
        var javaVersion = System.getProperty("java.version");
        var isBlank = "   ".isBlank(); // Java 11 feature
        var lines = "line1\nline2\nline3".lines().count(); // Java 11 feature
        
        return "Java 11 version - Using: " + String.join(", ", features) + 
               " (Runtime: " + javaVersion + ", isBlank: " + isBlank + ", lines: " + lines + ")";
    }
}
