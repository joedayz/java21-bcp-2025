package com.bcp.testlogging;

import java.util.logging.*;

public class Test {
    private static Logger logger = Logger.getLogger(com.bcp.testlogging.Test.class.getName());

    public static void main(String[] args) {
        System.out.println("=== JAVA LOGGING API - EJEMPLO COMPLETO ===");
        System.out.println("Este ejemplo demuestra el uso de la API de Logging de Java");
        System.out.println();

        // ========================================
        // 1. CONFIGURACIÓN BÁSICA DEL LOGGER
        // ========================================
        System.out.println("=== 1. CONFIGURACIÓN BÁSICA DEL LOGGER ===");
        System.out.println("Logger creado con el nombre de la clase: " + logger.getName());
        System.out.println("Nivel actual del logger: " + logger.getLevel());
        System.out.println();

        // ========================================
        // 2. EJEMPLO DE LOGGING CON EXCEPCIONES
        // ========================================
        System.out.println("=== 2. LOGGING CON EXCEPCIONES ===");
        System.out.println("Demostrando logger.log(Level.SEVERE, \"Your error message\", e)");
        
        try {
            // Simular una operación que puede lanzar una excepción
            System.out.println("Intentando una operación que puede fallar...");
            int result = 10 / 0; // Esto lanzará una ArithmeticException
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en operación matemática", e);
            System.out.println("✅ Excepción capturada y loggeada correctamente");
        }
        System.out.println();

        // ========================================
        // 3. LOGGING DE INFORMACIÓN
        // ========================================
        System.out.println("=== 3. LOGGING DE INFORMACIÓN ===");
        System.out.println("Demostrando logger.log(Level.INFO, \"Your message\")");
        
        logger.log(Level.INFO, "Aplicación iniciada correctamente");
        System.out.println("✅ Mensaje de INFO loggeado");
        System.out.println();

        // ========================================
        // 4. MÉTODO CONVENIENCIA info()
        // ========================================
        System.out.println("=== 4. MÉTODO CONVENIENCIA info() ===");
        System.out.println("Demostrando logger.info(\"Your message\")");
        
        logger.info("Procesamiento completado exitosamente");
        System.out.println("✅ Mensaje loggeado con método conveniencia");
        System.out.println();

        // ========================================
        // 5. DEMOSTRACIÓN DE TODOS LOS NIVELES
        // ========================================
        System.out.println("=== 5. DEMOSTRACIÓN DE TODOS LOS NIVELES ===");
        System.out.println("Hay siete niveles de logging que permiten registrar diferentes niveles de severidad:");
        System.out.println();

        // SEVERE (más crítico)
        logger.severe("🚨 ERROR CRÍTICO: La aplicación no puede continuar");
        
        // WARNING
        logger.warning("⚠️ ADVERTENCIA: Recursos del sistema bajos");
        
        // INFO
        logger.info("ℹ️ INFORMACIÓN: Usuario autenticado correctamente");
        
        // CONFIG
        logger.config("⚙️ CONFIGURACIÓN: Configuración de base de datos cargada");
        
        // FINE
        logger.fine("🔍 FINE: Detalle de depuración - Método X ejecutado");
        
        // FINER
        logger.finer("🔬 FINER: Detalle más específico - Variable Y = 42");
        
        // FINEST (menos crítico)
        logger.finest("🔬 FINEST: Detalle más granular - Iteración 15 completada");
        
        System.out.println("✅ Todos los niveles de logging demostrados");
        System.out.println();

        // ========================================
        // 6. EJEMPLOS PRÁCTICOS
        // ========================================
        System.out.println("=== 6. EJEMPLOS PRÁCTICOS ===");

        // Ejemplo: Logging de operaciones de base de datos
        System.out.println("Ejemplo: Logging de operaciones de base de datos");
        try {
            logger.info("Iniciando conexión a base de datos...");
            // Simular operación de BD
            Thread.sleep(100);
            logger.info("Conexión a base de datos establecida");
            
            logger.fine("Ejecutando consulta SELECT * FROM users");
            // Simular consulta
            Thread.sleep(50);
            logger.info("Consulta ejecutada exitosamente - 150 registros encontrados");
            
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error en operación de base de datos", e);
        }
        System.out.println();

        // Ejemplo: Logging de autenticación
        System.out.println("Ejemplo: Logging de autenticación");
        String username = "usuario123";
        logger.info("Intento de autenticación para usuario: " + username);
        
        // Simular autenticación
        boolean authSuccess = true; // Simular éxito
        if (authSuccess) {
            logger.info("Autenticación exitosa para usuario: " + username);
        } else {
            logger.warning("Autenticación fallida para usuario: " + username);
        }
        System.out.println();

        // Ejemplo: Logging de rendimiento
        System.out.println("Ejemplo: Logging de rendimiento");
        long startTime = System.currentTimeMillis();
        
        // Simular operación costosa
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Operación interrumpida", e);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        if (duration > 100) {
            logger.warning("Operación lenta detectada: " + duration + "ms");
        } else {
            logger.fine("Operación completada en tiempo normal: " + duration + "ms");
        }
        System.out.println();

        // ========================================
        // 7. CONFIGURACIÓN DE NIVELES
        // ========================================
        System.out.println("=== 7. CONFIGURACIÓN DE NIVELES ===");
        
        // Mostrar configuración actual
        System.out.println("Configuración actual del logger:");
        System.out.println("  - Nivel del logger: " + logger.getLevel());
        System.out.println("  - Nivel de los handlers: " + getHandlersLevel(logger));
        System.out.println();

        // Cambiar nivel del logger para ver más mensajes
        System.out.println("Cambiando nivel del logger a FINE para ver más detalles...");
        logger.setLevel(Level.FINE);
        
        // Ahora los mensajes FINE serán visibles
        logger.fine("Este mensaje FINE ahora será visible");
        logger.finer("Este mensaje FINER también será visible");
        System.out.println("✅ Nivel cambiado y mensajes de depuración visibles");
        System.out.println();

        // ========================================
        // 8. RESUMEN
        // ========================================
        System.out.println("=== RESUMEN ===");
        System.out.println("✅ Logger configurado correctamente con nombre de clase");
        System.out.println("✅ Logging de excepciones demostrado");
        System.out.println("✅ Logging de información demostrado");
        System.out.println("✅ Método conveniencia info() demostrado");
        System.out.println("✅ Todos los siete niveles de logging demostrados");
        System.out.println("✅ Ejemplos prácticos implementados");
        System.out.println("✅ Configuración de niveles demostrada");
        System.out.println();
        System.out.println("🎯 La API de Logging de Java proporciona un sistema robusto");
        System.out.println("   para registrar eventos y depurar aplicaciones.");
    }
    
    // Método auxiliar para obtener el nivel de los handlers
    private static String getHandlersLevel(Logger logger) {
        Handler[] handlers = logger.getHandlers();
        if (handlers.length == 0) {
            return "Sin handlers configurados (usa configuración del logger padre)";
        }
        StringBuilder levels = new StringBuilder();
        for (Handler handler : handlers) {
            levels.append(handler.getLevel()).append(", ");
        }
        return levels.substring(0, levels.length() - 2);
    }
} 