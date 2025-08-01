package com.bcp.anonymousinnerclass;

import java.math.BigDecimal;

/**
 * Clase base Order que define el método getDiscount
 * Como se muestra en la imagen
 */
public class Order {
    
    /**
     * Método que retorna el descuento por defecto
     * Retorna BigDecimal.ZERO como se muestra en la imagen
     */
    public BigDecimal getDiscount() {
        return BigDecimal.ZERO;
    }
    
    /**
     * Método para mostrar información de la orden
     */
    public void displayOrderInfo() {
        System.out.println("Orden con descuento: " + getDiscount());
    }
} 