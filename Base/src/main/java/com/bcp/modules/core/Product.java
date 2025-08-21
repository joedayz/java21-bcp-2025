package com.bcp.modules.core;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Clase Product que representa un producto en el sistema.
 * Esta clase es parte del módulo core y puede ser utilizada por otros módulos.
 */
public class Product {
    private static final Logger LOGGER = Logger.getLogger(Product.class.getName());
    
    private final String id;
    private final String name;
    private final double price;
    private final LocalDateTime createdAt;
    
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        
        LOGGER.info("Producto creado: " + name + " con precio: " + price);
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, createdAt=%s}", 
                           id, name, price, createdAt);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id.equals(product.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
