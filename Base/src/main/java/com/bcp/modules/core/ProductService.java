package com.bcp.modules.core;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para productos.
 * Esta interfaz será implementada por diferentes proveedores de servicios.
 */
public interface ProductService {
    
    /**
     * Guarda un producto en el almacenamiento.
     * @param product el producto a guardar
     * @return el producto guardado
     */
    Product save(Product product);
    
    /**
     * Busca un producto por su ID.
     * @param id el ID del producto
     * @return Optional que contiene el producto si se encuentra
     */
    Optional<Product> findById(String id);
    
    /**
     * Obtiene todos los productos.
     * @return lista de todos los productos
     */
    List<Product> findAll();
    
    /**
     * Elimina un producto por su ID.
     * @param id el ID del producto a eliminar
     * @return true si se eliminó, false si no existía
     */
    boolean deleteById(String id);
    
    /**
     * Obtiene el nombre del proveedor de servicio.
     * @return nombre del proveedor
     */
    String getProviderName();
}
