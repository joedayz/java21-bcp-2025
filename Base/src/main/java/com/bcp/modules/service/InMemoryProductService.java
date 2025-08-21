package com.bcp.modules.service;

import com.bcp.modules.core.Product;
import com.bcp.modules.core.ProductService;

import java.util.*;
import java.util.logging.Logger;

/**
 * Implementación en memoria del servicio de productos.
 * Esta implementación almacena los productos en un Map en memoria.
 */
public class InMemoryProductService implements ProductService {
    
    private static final Logger LOGGER = Logger.getLogger(InMemoryProductService.class.getName());
    private final Map<String, Product> products = new HashMap<>();
    
    @Override
    public Product save(Product product) {
        LOGGER.info("Guardando producto en memoria: " + product.getName());
        products.put(product.getId(), product);
        return product;
    }
    
    @Override
    public Optional<Product> findById(String id) {
        LOGGER.info("Buscando producto por ID: " + id);
        return Optional.ofNullable(products.get(id));
    }
    
    @Override
    public List<Product> findAll() {
        LOGGER.info("Obteniendo todos los productos de memoria");
        return new ArrayList<>(products.values());
    }
    
    @Override
    public boolean deleteById(String id) {
        LOGGER.info("Eliminando producto por ID: " + id);
        return products.remove(id) != null;
    }
    
    @Override
    public String getProviderName() {
        return "InMemoryProductService";
    }
}
