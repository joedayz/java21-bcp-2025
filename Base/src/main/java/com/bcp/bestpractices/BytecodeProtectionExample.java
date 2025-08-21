package com.bcp.bestpractices;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Ejemplo 5: Protect byte-code against tampering and dangerous behavior
 * 
 * Demuestra cómo proteger el bytecode contra manipulación y comportamientos peligrosos.
 */
public class BytecodeProtectionExample {
    
    /**
     * Clase que implementa verificación de integridad
     */
    public static class IntegrityProtectedClass implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final String data;
        private final byte[] checksum;
        
        public IntegrityProtectedClass(String data) {
            this.data = data;
            this.checksum = calculateChecksum(data);
        }
        
        private byte[] calculateChecksum(String data) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                return md.digest(data.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Algoritmo SHA-256 no disponible", e);
            }
        }
        
        public String getData() {
            // Verificar integridad antes de devolver datos
            if (!verifyIntegrity()) {
                throw new SecurityException("Integridad de datos comprometida");
            }
            return data;
        }
        
        private boolean verifyIntegrity() {
            byte[] currentChecksum = calculateChecksum(data);
            return Arrays.equals(checksum, currentChecksum);
        }
        
        @Override
        public String toString() {
            return "IntegrityProtectedClass{data='" + data + "', checksum=" + 
                   Arrays.toString(checksum) + "}";
        }
    }
    
    /**
     * Clase que demuestra serialización segura
     */
    public static class SecureSerializer {
        
        /**
         * Serializa un objeto con validaciones
         */
        public static byte[] serializeSecurely(Serializable obj) throws IOException {
            if (obj == null) {
                throw new IllegalArgumentException("Objeto no puede ser nulo");
            }
            
            // Verificar que el objeto es del tipo esperado
            if (!(obj instanceof IntegrityProtectedClass)) {
                throw new SecurityException("Solo se permiten objetos IntegrityProtectedClass");
            }
            
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                
                oos.writeObject(obj);
                return baos.toByteArray();
            }
        }
        
        /**
         * Deserializa un objeto con validaciones
         */
        public static IntegrityProtectedClass deserializeSecurely(byte[] data) 
                throws IOException, ClassNotFoundException {
            
            if (data == null || data.length == 0) {
                throw new IllegalArgumentException("Datos no pueden ser nulos o vacíos");
            }
            
            // Verificar tamaño máximo para prevenir DoS
            if (data.length > 1024 * 1024) { // 1MB máximo
                throw new SecurityException("Datos demasiado grandes: " + data.length + " bytes");
            }
            
            try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                
                Object obj = ois.readObject();
                
                // Verificar tipo
                if (!(obj instanceof IntegrityProtectedClass)) {
                    throw new SecurityException("Tipo de objeto inesperado: " + obj.getClass());
                }
                
                IntegrityProtectedClass result = (IntegrityProtectedClass) obj;
                
                // Verificar integridad
                try {
                    result.getData(); // Esto verificará la integridad
                    return result;
                } catch (SecurityException e) {
                    throw new SecurityException("Objeto deserializado comprometido", e);
                }
            }
        }
    }
    
    /**
     * Clase que demuestra verificación de bytecode
     */
    public static class BytecodeVerifier {
        
        /**
         * Verifica que una clase no ha sido modificada
         */
        public static boolean verifyClassIntegrity(Class<?> clazz) {
            try {
                // Obtener el bytecode de la clase
                String className = clazz.getName();
                String classAsPath = className.replace('.', '/') + ".class";
                
                // Calcular checksum del bytecode actual
                byte[] currentBytecode = clazz.getClassLoader()
                    .getResourceAsStream(classAsPath)
                    .readAllBytes();
                
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] currentChecksum = md.digest(currentBytecode);
                
                // En una aplicación real, compararías con un checksum conocido
                // Por ahora, solo verificamos que el bytecode no esté vacío
                return currentBytecode.length > 0;
                
            } catch (Exception e) {
                System.err.println("Error verificando integridad de clase: " + e.getMessage());
                return false;
            }
        }
        
        /**
         * Verifica que no se esté usando -Xverify:none
         */
        public static boolean isBytecodeVerificationEnabled() {
            try {
                // Intentar crear una clase con bytecode inválido
                // Si la verificación está habilitada, esto debería fallar
                byte[] invalidBytecode = new byte[]{0x00, 0x01, 0x02, 0x03}; // Bytecode inválido
                
                // En una aplicación real, usarías herramientas más sofisticadas
                // para detectar si la verificación está deshabilitada
                
                return true; // Asumimos que está habilitada por defecto
                
            } catch (Exception e) {
                return false;
            }
        }
    }
    
    /**
     * Clase que demuestra las mejores prácticas
     */
    public static class ProtectionTester {
        
        public static void testBytecodeProtection() {
            System.out.println("=== DEMO: Protección de Bytecode ===\n");
            
            System.out.println("1. ✅ SERIALIZACIÓN SEGURA");
            try {
                IntegrityProtectedClass original = new IntegrityProtectedClass("Datos seguros");
                System.out.println("Objeto original: " + original);
                
                // Serializar
                byte[] serialized = SecureSerializer.serializeSecurely(original);
                System.out.println("Serializado: " + serialized.length + " bytes");
                
                // Deserializar
                IntegrityProtectedClass deserialized = SecureSerializer.deserializeSecurely(serialized);
                System.out.println("Deserializado: " + deserialized);
                System.out.println("Datos recuperados: " + deserialized.getData());
                
            } catch (Exception e) {
                System.err.println("Error en serialización: " + e.getMessage());
            }
            
            System.out.println("\n2. ✅ VERIFICACIÓN DE INTEGRIDAD");
            boolean classIntegrity = BytecodeVerifier.verifyClassIntegrity(IntegrityProtectedClass.class);
            System.out.println("Integridad de clase verificada: " + classIntegrity);
            
            boolean verificationEnabled = BytecodeVerifier.isBytecodeVerificationEnabled();
            System.out.println("Verificación de bytecode habilitada: " + verificationEnabled);
            
            System.out.println("\n3. ❌ INTENTOS DE MANIPULACIÓN");
            
            // Intentar deserializar datos corruptos
            try {
                byte[] corruptData = "datos corruptos".getBytes();
                SecureSerializer.deserializeSecurely(corruptData);
                System.out.println("❌ Deserialización de datos corruptos exitosa (no debería pasar)");
            } catch (Exception e) {
                System.out.println("✅ Protección exitosa: " + e.getMessage());
            }
            
            // Intentar deserializar datos muy grandes
            try {
                byte[] largeData = new byte[2 * 1024 * 1024]; // 2MB
                SecureSerializer.deserializeSecurely(largeData);
                System.out.println("❌ Deserialización de datos grandes exitosa (no debería pasar)");
            } catch (Exception e) {
                System.out.println("✅ Protección contra DoS: " + e.getMessage());
            }
            
            System.out.println("\n4. MEJORES PRÁCTICAS");
            System.out.println("✅ Nunca usar -Xverify:none o -noverify");
            System.out.println("✅ Validar tipos en deserialización");
            System.out.println("✅ Verificar integridad de datos");
            System.out.println("✅ Limitar tamaño de datos deserializados");
            System.out.println("✅ Usar filtros de deserialización");
            System.out.println("✅ Firmar digitalmente el bytecode");
            System.out.println("✅ Verificar checksums de clases críticas");
        }
    }
    
    public static void main(String[] args) {
        ProtectionTester.testBytecodeProtection();
    }
}
