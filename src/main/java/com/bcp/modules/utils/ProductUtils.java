package com.bcp.modules.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Clase de utilidades para productos.
 * Proporciona métodos estáticos para operaciones comunes con productos.
 */
public class ProductUtils {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Genera un ID único para un producto.
     * @return ID único generado
     */
    public static String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    /**
     * Formatea una fecha y hora para mostrar.
     * @param dateTime la fecha y hora a formatear
     * @return string formateado
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
    
    /**
     * Calcula el precio total de una lista de productos.
     * @param products lista de productos
     * @return precio total
     */
    public static double calculateTotalPrice(List<? extends Object> products) {
        // Nota: En una implementación real, esto requeriría acceso a Product
        // Aquí solo simulamos el cálculo
        return products.size() * 10.0; // Precio simulado
    }
    
    /**
     * Valida si un ID de producto es válido.
     * @param id el ID a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty() && id.length() >= 3;
    }
}
