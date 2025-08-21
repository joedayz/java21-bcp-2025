package com.bcp.modules.service;

import com.bcp.modules.core.Product;
import com.bcp.modules.core.ProductService;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implementación basada en archivos del servicio de productos.
 * Esta implementación simula almacenamiento en archivo (en realidad usa memoria
 * pero simula operaciones de archivo).
 */
public class FileBasedProductService implements ProductService {
    
    private static final Logger LOGGER = Logger.getLogger(FileBasedProductService.class.getName());
    private final Map<String, Product> products = new HashMap<>();
    private final String filename = "products.dat";
    
    public FileBasedProductService() {
        LOGGER.info("Inicializando FileBasedProductService");
        loadFromFile();
    }
    
    @Override
    public Product save(Product product) {
        LOGGER.info("Guardando producto en archivo: " + product.getName());
        products.put(product.getId(), product);
        saveToFile();
        return product;
    }
    
    @Override
    public Optional<Product> findById(String id) {
        LOGGER.info("Buscando producto por ID en archivo: " + id);
        return Optional.ofNullable(products.get(id));
    }
    
    @Override
    public List<Product> findAll() {
        LOGGER.info("Obteniendo todos los productos del archivo");
        return new ArrayList<>(products.values());
    }
    
    @Override
    public boolean deleteById(String id) {
        LOGGER.info("Eliminando producto por ID del archivo: " + id);
        boolean removed = products.remove(id) != null;
        if (removed) {
            saveToFile();
        }
        return removed;
    }
    
    @Override
    public String getProviderName() {
        return "FileBasedProductService";
    }
    
    private void saveToFile() {
        LOGGER.info("Simulando guardado en archivo: " + filename);
        // En una implementación real, aquí se escribiría al archivo
    }
    
    private void loadFromFile() {
        LOGGER.info("Simulando carga desde archivo: " + filename);
        // En una implementación real, aquí se leería del archivo
    }
}
