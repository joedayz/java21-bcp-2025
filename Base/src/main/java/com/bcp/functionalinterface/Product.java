package com.bcp.functionalinterface;

import java.math.BigDecimal;

/**
 * Clase Product para el ejemplo de ordenamiento con clases anónimas
 * Como se muestra en la imagen
 */
public class Product {
    private String name;
    private BigDecimal price;
    
    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    
    public Product(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=$" + price + "}";
    }
} 