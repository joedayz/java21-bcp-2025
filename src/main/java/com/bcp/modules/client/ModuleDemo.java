package com.bcp.modules.client;

import com.bcp.modules.core.Product;
import com.bcp.modules.core.ProductService;
import com.bcp.modules.utils.ProductUtils;

import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Clase principal que demuestra el uso del sistema de módulos de Java.
 * Esta clase muestra cómo:
 * 1. Cargar servicios usando ServiceLoader
 * 2. Usar clases de diferentes módulos
 * 3. Trabajar con el patrón de servicios
 */
public class ModuleDemo {
    
    private static final Logger LOGGER = Logger.getLogger(ModuleDemo.class.getName());
    
    public static void main(String[] args) {
        LOGGER.info("=== Demo del Sistema de Módulos de Java ===");
        
        // Cargar todos los proveedores de servicios disponibles
        ServiceLoader<ProductService> serviceLoader = ServiceLoader.load(ProductService.class);
        
        LOGGER.info("Proveedores de servicios encontrados:");
        serviceLoader.forEach(service -> {
            LOGGER.info("- " + service.getProviderName());
        });
        
        // Usar el primer servicio disponible
        ProductService productService = serviceLoader.findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró ningún proveedor de servicios"));
        
        LOGGER.info("Usando proveedor: " + productService.getProviderName());
        
        // Crear algunos productos usando utilidades
        Product product1 = new Product(ProductUtils.generateId(), "Laptop", 999.99);
        Product product2 = new Product(ProductUtils.generateId(), "Mouse", 29.99);
        Product product3 = new Product(ProductUtils.generateId(), "Teclado", 59.99);
        
        // Guardar productos
        LOGGER.info("Guardando productos...");
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        
        // Listar todos los productos
        LOGGER.info("Listando todos los productos:");
        List<Product> allProducts = productService.findAll();
        allProducts.forEach(product -> {
            LOGGER.info(String.format("  - %s: %s (S/%.2f) - Creado: %s", 
                product.getId(), 
                product.getName(), 
                product.getPrice(),
                ProductUtils.formatDateTime(product.getCreatedAt())
            ));
        });
        
        // Buscar un producto específico
        String searchId = product1.getId();
        LOGGER.info("Buscando producto con ID: " + searchId);
        productService.findById(searchId).ifPresent(product -> {
            LOGGER.info("Producto encontrado: " + product);
        });
        
        // Eliminar un producto
        LOGGER.info("Eliminando producto con ID: " + searchId);
        boolean deleted = productService.deleteById(searchId);
        LOGGER.info("Producto eliminado: " + deleted);
        
        // Verificar que fue eliminado
        LOGGER.info("Verificando eliminación...");
        productService.findById(searchId).ifPresentOrElse(
            product -> LOGGER.info("ERROR: El producto aún existe"),
            () -> LOGGER.info("OK: El producto fue eliminado correctamente")
        );
        
        // Listar productos restantes
        LOGGER.info("Productos restantes:");
        productService.findAll().forEach(product -> {
            LOGGER.info("  - " + product.getName());
        });
        
        LOGGER.info("=== Demo completado ===");
    }
}
