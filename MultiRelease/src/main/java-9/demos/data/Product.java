package demos.data;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase Product - Versión específica para Java 9
 * Esta versión aprovecha las características de Java 9 como List.of()
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
     * En Java 9, este método usa List.of() (característica de Java 9)
     */
    public String getVersionInfo() {
        List<String> features = List.of("List.of()", "Module System", "JShell");
        return "Java 9 version - Using: " + String.join(", ", features);
    }
}
