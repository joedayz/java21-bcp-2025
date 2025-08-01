package com.bcp.anonymousinnerclass;

import java.math.BigDecimal;

/**
 * Implementación de clase separada que extiende Order
 * Como se muestra en la imagen en "Separate Class Implementation"
 */
public class OnlineOrder extends Order {
    
    /**
     * Sobrescribe el método getDiscount para retornar 10% de descuento
     * Exactamente como se muestra en la imagen
     */
    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.1);
    }
    
    /**
     * Método específico para órdenes online
     */
    public void processOnlineOrder() {
        System.out.println("Procesando orden online con descuento del 10%");
    }
} 