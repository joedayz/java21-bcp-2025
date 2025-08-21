package com.bcp.modules.client;

import com.bcp.modules.core.Product;
import com.bcp.modules.core.ProductService;
import com.bcp.modules.utils.ProductUtils;

import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Demo que muestra cómo trabajar con módulos en un proyecto Maven.
 * Este demo es similar al ModuleDemo pero está diseñado para ejecutarse
 * en un entorno Maven.
 */
public class MavenModulesDemo {
    
    private static final Logger LOGGER = Logger.getLogger(MavenModulesDemo.class.getName());
    
    public static void main(String[] args) {
        LOGGER.info("=== Demo de Módulos con Maven ===");
        
        // Verificar que estamos ejecutando con módulos
        Module currentModule = MavenModulesDemo.class.getModule();
        LOGGER.info("Módulo actual: " + currentModule.getName());
        LOGGER.info("¿Es un módulo nombrado? " + currentModule.isNamed());
        
        // Mostrar información del módulo
        LOGGER.info("Paquetes del módulo:");
        currentModule.getPackages().forEach(pkg -> {
            LOGGER.info("  - " + pkg);
        });
        
        // Mostrar dependencias del módulo
        LOGGER.info("Dependencias del módulo:");
        currentModule.getDescriptor().requires().forEach(req -> {
            LOGGER.info("  - " + req.name());
        });
        
        // Cargar servicios
        ServiceLoader<ProductService> serviceLoader = ServiceLoader.load(ProductService.class);
        
        LOGGER.info("Proveedores de servicios disponibles:");
        serviceLoader.forEach(service -> {
            LOGGER.info("  - " + service.getProviderName());
        });
        
        // Usar el primer servicio
        ProductService productService = serviceLoader.findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró ningún proveedor de servicios"));
        
        // Crear productos de ejemplo
        Product[] sampleProducts = {
            new Product(ProductUtils.generateId(), "Laptop Gaming", 1299.99),
            new Product(ProductUtils.generateId(), "Monitor 4K", 599.99),
            new Product(ProductUtils.generateId(), "Auriculares", 199.99),
            new Product(ProductUtils.generateId(), "Webcam HD", 89.99)
        };
        
        // Guardar productos
        LOGGER.info("Guardando productos de ejemplo...");
        for (Product product : sampleProducts) {
            productService.save(product);
            LOGGER.info("Guardado: " + product.getName() + " (ID: " + product.getId() + ")");
        }
        
        // Mostrar estadísticas
        List<Product> allProducts = productService.findAll();
        LOGGER.info("Total de productos: " + allProducts.size());
        
        double totalValue = allProducts.stream()
            .mapToDouble(Product::getPrice)
            .sum();
        LOGGER.info("Valor total del inventario: S/" + String.format("%.2f", totalValue));
        
        // Mostrar productos ordenados por precio
        LOGGER.info("Productos ordenados por precio:");
        allProducts.stream()
            .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .forEach(product -> {
                LOGGER.info(String.format("  - %s: S/%.2f", product.getName(), product.getPrice()));
            });
        
        LOGGER.info("=== Demo de Maven completado ===");
    }
}
