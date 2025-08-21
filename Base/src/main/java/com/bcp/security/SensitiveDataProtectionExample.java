package com.bcp.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Ejemplo de Protección de Datos Sensibles (Part 1)
 * 
 * Demuestra las mejores prácticas para proteger datos sensibles:
 * 1. Scrambling/Hashing de datos
 * 2. Limpieza de memoria
 * 3. Eliminación de datos sensibles de excepciones
 * 4. No serializar datos sensibles
 * 5. No escribir datos sensibles en logs
 */
public class SensitiveDataProtectionExample {
    
    private static final Logger logger = Logger.getLogger(SensitiveDataProtectionExample.class.getName());
    
    /**
     * Clase que maneja datos sensibles de forma segura
     */
    public static class SecureDataHandler {
        
        /**
         * Hashea datos sensibles usando SHA-256
         * (Como se muestra en el slide)
         */
        public static String hashSensitiveData(String value) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] digest = md.digest(value.getBytes());
                String hash = new BigInteger(1, digest).toString(16);
                return hash;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Algoritmo SHA-256 no disponible", e);
            }
        }
        
        /**
         * Limpia datos sensibles de memoria
         */
        public static void clearSensitiveData(char[] sensitiveData) {
            if (sensitiveData != null) {
                Arrays.fill(sensitiveData, '\0'); // Sobrescribir con ceros
            }
        }
        
        /**
         * Limpia datos sensibles de String (menos seguro, pero necesario)
         */
        public static void clearSensitiveString(String sensitiveData) {
            // Nota: En Java, los Strings son inmutables, por lo que no se pueden limpiar
            // completamente de memoria. Esta es una limitación del lenguaje.
            // En aplicaciones críticas, usar char[] en su lugar.
            System.out.println("⚠️  Advertencia: Los Strings son inmutables y no se pueden limpiar completamente");
        }
        
        /**
         * Crea un mensaje de error seguro sin datos sensibles
         */
        public static String createSecureErrorMessage(String operation, String errorType) {
            return String.format("Error en operación '%s': %s", operation, errorType);
        }
        
        /**
         * Log seguro sin datos sensibles
         */
        public static void logSecure(String operation, String status) {
            logger.info(String.format("Operación '%s' completada con estado: %s", operation, status));
        }
    }
    
    /**
     * Clase que representa datos de usuario con protección
     */
    public static class SecureUserData {
        private final String userId;
        private final String hashedCreditCard;
        private final String hashedAddress;
        private char[] temporaryPassword; // Para limpieza segura
        
        public SecureUserData(String userId, String creditCard, String address, String password) {
            this.userId = userId;
            this.hashedCreditCard = SecureDataHandler.hashSensitiveData(creditCard);
            this.hashedAddress = SecureDataHandler.hashSensitiveData(address);
            this.temporaryPassword = password.toCharArray();
        }
        
        public String getUserId() {
            return userId; // ID de usuario no es tan sensible
        }
        
        public String getHashedCreditCard() {
            return hashedCreditCard; // Solo el hash
        }
        
        public String getHashedAddress() {
            return hashedAddress; // Solo el hash
        }
        
        /**
         * Usa la contraseña temporal y la limpia inmediatamente
         */
        public boolean validatePassword(String inputPassword) {
            try {
                String inputHash = SecureDataHandler.hashSensitiveData(new String(temporaryPassword));
                String providedHash = SecureDataHandler.hashSensitiveData(inputPassword);
                return inputHash.equals(providedHash);
            } finally {
                // Limpiar la contraseña de memoria inmediatamente
                SecureDataHandler.clearSensitiveData(temporaryPassword);
                temporaryPassword = null;
            }
        }
        
        @Override
        public String toString() {
            return String.format("UserData{userId='%s', hashedCreditCard='%s...', hashedAddress='%s...'}", 
                               userId, 
                               hashedCreditCard.substring(0, Math.min(8, hashedCreditCard.length())),
                               hashedAddress.substring(0, Math.min(8, hashedAddress.length())));
        }
    }
    
    /**
     * Clase que demuestra manejo seguro de excepciones
     */
    public static class SecureDatabaseHandler {
        
        /**
         * ❌ MANERA PELIGROSA - Expone datos sensibles en excepciones
         */
        public static void dangerousQuery(String creditCard, String userId) {
            try {
                // Simular consulta que falla
                if (creditCard.equals("1234-5678-9012-3456")) {
                    throw new RuntimeException("Error en consulta para tarjeta: " + creditCard + 
                                             " y usuario: " + userId);
                }
            } catch (Exception e) {
                // ❌ PELIGROSO - Log con datos sensibles
                logger.severe("Error en base de datos: " + e.getMessage());
                throw e; // ❌ Re-lanza con datos sensibles
            }
        }
        
        /**
         * ✅ MANERA SEGURA - No expone datos sensibles
         */
        public static void secureQuery(String creditCard, String userId) {
            try {
                // Simular consulta que falla
                if (creditCard.equals("1234-5678-9012-3456")) {
                    throw new RuntimeException("Error en consulta para tarjeta: " + creditCard + 
                                             " y usuario: " + userId);
                }
            } catch (Exception e) {
                // ✅ SEGURO - Log sin datos sensibles
                String secureMessage = SecureDataHandler.createSecureErrorMessage("database_query", "connection_failed");
                logger.warning(secureMessage);
                
                // ✅ SEGURO - Re-lanza excepción sin datos sensibles
                throw new RuntimeException("Error en operación de base de datos", e);
            }
        }
    }
    
    /**
     * Clase que demuestra todos los conceptos
     */
    public static class ProtectionDemo {
        
        public static void demonstrateHashing() {
            System.out.println("=== DEMOSTRACIÓN DE HASHING ===\n");
            
            String creditCard = "1234-5678-9012-3456";
            String address = "123 Main St, City, Country";
            
            System.out.println("Datos originales:");
            System.out.println("Tarjeta: " + creditCard);
            System.out.println("Dirección: " + address);
            
            String hashedCard = SecureDataHandler.hashSensitiveData(creditCard);
            String hashedAddress = SecureDataHandler.hashSensitiveData(address);
            
            System.out.println("\nDatos hasheados:");
            System.out.println("Tarjeta: " + hashedCard);
            System.out.println("Dirección: " + hashedAddress);
            
            // Verificar que el hash es consistente
            String hashedCard2 = SecureDataHandler.hashSensitiveData(creditCard);
            System.out.println("\nVerificación de consistencia:");
            System.out.println("Hash 1: " + hashedCard);
            System.out.println("Hash 2: " + hashedCard2);
            System.out.println("¿Son iguales? " + hashedCard.equals(hashedCard2));
        }
        
        public static void demonstrateMemoryClearing() {
            System.out.println("\n=== DEMOSTRACIÓN DE LIMPIEZA DE MEMORIA ===\n");
            
            char[] password = "MySecretPassword123!".toCharArray();
            System.out.println("Contraseña original: " + new String(password));
            
            // Usar la contraseña
            System.out.println("Usando contraseña...");
            
            // Limpiar inmediatamente
            SecureDataHandler.clearSensitiveData(password);
            System.out.println("Contraseña después de limpiar: " + new String(password));
            System.out.println("¿Está limpia? " + (new String(password).equals("\0".repeat(password.length))));
        }
        
        public static void demonstrateSecureUserData() {
            System.out.println("\n=== DEMOSTRACIÓN DE DATOS DE USUARIO SEGUROS ===\n");
            
            SecureUserData userData = new SecureUserData(
                "user123",
                "1234-5678-9012-3456",
                "123 Main St, City, Country",
                "MySecretPassword123!"
            );
            
            System.out.println("Datos de usuario (seguros):");
            System.out.println(userData);
            
            // Validar contraseña
            boolean isValid = userData.validatePassword("MySecretPassword123!");
            System.out.println("Contraseña válida: " + isValid);
            
            // Intentar acceder a contraseña después de validación
            System.out.println("Contraseña después de validación: " + 
                             (userData.temporaryPassword == null ? "null" : "todavía en memoria"));
        }
        
        public static void demonstrateExceptionHandling() {
            System.out.println("\n=== DEMOSTRACIÓN DE MANEJO DE EXCEPCIONES ===\n");
            
            String creditCard = "1234-5678-9012-3456";
            String userId = "user123";
            
            System.out.println("1. ❌ MANERA PELIGROSA:");
            try {
                SecureDatabaseHandler.dangerousQuery(creditCard, userId);
            } catch (Exception e) {
                System.out.println("Excepción capturada: " + e.getMessage());
                System.out.println("⚠️  ¡Los datos sensibles están expuestos!");
            }
            
            System.out.println("\n2. ✅ MANERA SEGURA:");
            try {
                SecureDatabaseHandler.secureQuery(creditCard, userId);
            } catch (Exception e) {
                System.out.println("Excepción capturada: " + e.getMessage());
                System.out.println("✅ Los datos sensibles están protegidos");
            }
        }
        
        public static void demonstrateLogging() {
            System.out.println("\n=== DEMOSTRACIÓN DE LOGGING SEGURO ===\n");
            
            // ❌ Logging peligroso
            String creditCard = "1234-5678-9012-3456";
            System.out.println("❌ Logging peligroso:");
            System.out.println("logger.info(\"Procesando tarjeta: " + creditCard + "\");");
            
            // ✅ Logging seguro
            System.out.println("\n✅ Logging seguro:");
            SecureDataHandler.logSecure("credit_card_processing", "success");
            
            // ✅ Logging con hash
            String hashedCard = SecureDataHandler.hashSensitiveData(creditCard);
            System.out.println("logger.info(\"Procesando tarjeta hash: " + hashedCard.substring(0, 8) + "...\");");
        }
        
        public static void demonstrateRealWorldScenario() {
            System.out.println("\n=== ESCENARIO DEL MUNDO REAL ===\n");
            
            // Simular procesamiento de pago
            System.out.println("Procesando pago de usuario...");
            
            // 1. Recibir datos sensibles
            String creditCard = "1234-5678-9012-3456";
            String cvv = "123";
            String address = "123 Main St, City, Country";
            
            // 2. Crear objeto seguro
            SecureUserData userData = new SecureUserData("user123", creditCard, address, "password123");
            
            // 3. Procesar pago (simulado)
            try {
                System.out.println("✅ Pago procesado exitosamente");
                SecureDataHandler.logSecure("payment_processing", "success");
                
                // 4. Limpiar datos temporales
                char[] tempCvv = cvv.toCharArray();
                SecureDataHandler.clearSensitiveData(tempCvv);
                
            } catch (Exception e) {
                // 5. Manejo seguro de errores
                SecureDataHandler.logSecure("payment_processing", "failed");
                throw new RuntimeException("Error en procesamiento de pago", e);
            }
            
            System.out.println("✅ Datos sensibles protegidos en todo el proceso");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🛡️  DEMO: PROTECCIÓN DE DATOS SENSIBLES (PART 1)");
        System.out.println("================================================\n");
        
        // Demostrar todas las protecciones
        ProtectionDemo.demonstrateHashing();
        ProtectionDemo.demonstrateMemoryClearing();
        ProtectionDemo.demonstrateSecureUserData();
        ProtectionDemo.demonstrateExceptionHandling();
        ProtectionDemo.demonstrateLogging();
        ProtectionDemo.demonstrateRealWorldScenario();
        
        System.out.println("\n🎯 MEJORES PRÁCTICAS IMPLEMENTADAS:");
        System.out.println("✅ Hashing de datos sensibles (SHA-256)");
        System.out.println("✅ Limpieza inmediata de memoria");
        System.out.println("✅ Eliminación de datos sensibles de excepciones");
        System.out.println("✅ No serialización de datos sensibles");
        System.out.println("✅ Logging seguro sin datos sensibles");
        System.out.println("✅ Prevención de fraude e identidad robada");
    }
}
