package com.bcp.modules.client;

import com.bcp.modules.core.Product;
import com.bcp.modules.core.ProductService;

import java.lang.reflect.Method;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Demo que muestra el uso de reflection con el sistema de módulos.
 * Demuestra cómo los módulos controlan el acceso a reflection.
 */
public class ReflectionDemo {
    
    private static final Logger LOGGER = Logger.getLogger(ReflectionDemo.class.getName());
    
    public static void main(String[] args) {
        LOGGER.info("=== Demo de Reflection con Módulos ===");
        
        try {
            // Obtener un servicio usando ServiceLoader
            ServiceLoader<ProductService> serviceLoader = ServiceLoader.load(ProductService.class);
            ProductService service = serviceLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró ningún proveedor de servicios"));
            
            LOGGER.info("Clase del servicio: " + service.getClass().getName());
            
            // Usar reflection para obtener información de la clase
            Class<?> serviceClass = service.getClass();
            
            LOGGER.info("Métodos públicos de la clase:");
            Method[] methods = serviceClass.getMethods();
            for (Method method : methods) {
                LOGGER.info("  - " + method.getName() + "()");
            }
            
            // Intentar crear una instancia usando reflection
            LOGGER.info("Intentando crear instancia usando reflection...");
            try {
                Object newInstance = serviceClass.getDeclaredConstructor().newInstance();
                LOGGER.info("Instancia creada exitosamente: " + newInstance);
            } catch (Exception e) {
                LOGGER.warning("No se pudo crear instancia: " + e.getMessage());
            }
            
            // Demostrar acceso a campos privados (si está permitido)
            LOGGER.info("Intentando acceder a campos privados...");
            try {
                java.lang.reflect.Field[] fields = serviceClass.getDeclaredFields();
                for (java.lang.reflect.Field field : fields) {
                    field.setAccessible(true);
                    LOGGER.info("  - Campo: " + field.getName() + " = " + field.get(service));
                }
            } catch (Exception e) {
                LOGGER.warning("No se pudo acceder a campos privados: " + e.getMessage());
            }
            
            // Crear un producto y usar reflection para acceder a sus propiedades
            Product product = new Product("TEST123", "Producto Test", 100.0);
            LOGGER.info("Producto creado: " + product);
            
            Class<?> productClass = product.getClass();
            LOGGER.info("Métodos del producto:");
            for (Method method : productClass.getMethods()) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    try {
                        Object value = method.invoke(product);
                        LOGGER.info("  - " + method.getName() + "() = " + value);
                    } catch (Exception e) {
                        LOGGER.warning("Error al invocar " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
            
        } catch (Exception e) {
            LOGGER.severe("Error en demo de reflection: " + e.getMessage());
            e.printStackTrace();
        }
        
        LOGGER.info("=== Demo de Reflection completado ===");
    }
}
