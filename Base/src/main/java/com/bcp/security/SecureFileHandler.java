package com.bcp.security;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase de ejemplo que demuestra las mejores prácticas de seguridad
 * para manejo de archivos y operaciones I/O
 */
public class SecureFileHandler {
    
    // Límites de seguridad
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_OPERATIONS = 1000;
    private static final int TIMEOUT_SECONDS = 30;
    
    // Contador de operaciones para detectar DoS
    private static final AtomicInteger operationCount = new AtomicInteger(0);
    
    /**
     * Lee un archivo de forma segura, protegiendo contra directory traversal
     */
    public String readFileSecurely(String userProvidedPath, Path baseDirectory) throws IOException {
        // 1. Proteger contra directory traversal
        Path userPath = Paths.get(userProvidedPath);
        Path normalizedPath = userPath.normalize();
        Path realPath = normalizedPath.toRealPath();
        
        // Verificar que está dentro del directorio base
        if (!realPath.startsWith(baseDirectory)) {
            throw new SecurityException("Acceso denegado: ruta fuera del directorio permitido");
        }
        
        // 2. Verificar tamaño del archivo (protección DoS)
        if (Files.size(realPath) > MAX_FILE_SIZE) {
            throw new SecurityException("Archivo demasiado grande: " + Files.size(realPath) + " bytes");
        }
        
        // 3. Monitorear operaciones (protección DoS)
        if (operationCount.incrementAndGet() > MAX_OPERATIONS) {
            throw new SecurityException("Demasiadas operaciones de archivo");
        }
        
        // 4. Usar timeout para operaciones largas
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = null;
        try {
            future = executor.submit(() -> {
                return new String(Files.readAllBytes(realPath));
            });
            
            return future.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            
        } catch (TimeoutException e) {
            if (future != null) {
                future.cancel(true);
            }
            throw new SecurityException("Operación cancelada por timeout");
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException("Error al leer archivo", e);
        } finally {
            executor.shutdown();
        }
    }
    
    /**
     * Deserializa un objeto de forma segura
     */
    public <T> T deserializeSecurely(byte[] data, Class<T> expectedClass) throws Exception {
        // 1. Usar filtros de deserialización (Java 9+)
        ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(
            expectedClass.getName() + ";!*"
        );
        
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            ois.setObjectInputFilter(filter);
            Object obj = ois.readObject();
            
            // 2. Verificar el tipo
            if (!expectedClass.isInstance(obj)) {
                throw new SecurityException("Tipo de objeto inesperado: " + obj.getClass());
            }
            
            // 3. Validar el objeto deserializado
            validateDeserializedObject(obj);
            
            return expectedClass.cast(obj);
        }
    }
    
    /**
     * Valida un objeto después de la deserialización
     */
    private void validateDeserializedObject(Object obj) {
        if (obj == null) {
            throw new SecurityException("Objeto deserializado es nulo");
        }
        
        // Aquí puedes agregar validaciones específicas según el tipo de objeto
        // Por ejemplo, si es un User, validar que tenga campos válidos
    }
    
    /**
     * Ejemplo de uso
     */
    public static void main(String[] args) {
        SecureFileHandler handler = new SecureFileHandler();
        Path baseDir = Paths.get(System.getProperty("user.dir"));
        
        try {
            // Ejemplo de lectura segura
            String content = handler.readFileSecurely("pom.xml", baseDir);
            System.out.println("Archivo leído exitosamente: " + content.length() + " caracteres");
            
        } catch (SecurityException e) {
            System.err.println("Error de seguridad: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de I/O: " + e.getMessage());
        }
    }
}
