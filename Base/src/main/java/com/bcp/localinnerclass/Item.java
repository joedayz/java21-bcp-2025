package com.bcp.localinnerclass;

/**
 * Clase Item para el ejemplo de clases anidadas locales
 */
public class Item {
    private Product product;
    private int quantity;
    
    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
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
} 