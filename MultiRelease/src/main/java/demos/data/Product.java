package demos.data;

import java.util.Objects;

/**
 * Clase Product - Versión por defecto (Java 8 compatible)
 * Esta es la versión base que funcionará en Java 8 y versiones anteriores
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
     * En Java 8, este método mostrará información básica
     */
    public String getVersionInfo() {
        return "Java 8 compatible version - Basic implementation";
    }
}
