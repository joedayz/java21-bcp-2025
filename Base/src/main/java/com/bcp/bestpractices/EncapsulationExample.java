package com.bcp.bestpractices;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Ejemplo 1: Enforce tight encapsulation
 * 
 * Demuestra cómo usar encapsulación estricta para proteger clases
 * de acceso no autorizado y reflexión.
 */
public class EncapsulationExample {
    
    /**
     * Clase con encapsulación estricta - todos los campos son privados
     * y solo se acceden a través de métodos controlados
     */
    public static class SecureBankAccount {
        // Campos privados - no accesibles desde fuera
        private final String accountNumber;
        private double balance;
        private final List<String> transactionHistory;
        
        // Constructor privado - solo se puede crear a través del factory
        private SecureBankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
            this.transactionHistory = new ArrayList<>();
            addTransaction("Cuenta creada con saldo inicial: " + initialBalance);
        }
        
        // Factory method con validaciones
        public static SecureBankAccount createAccount(String accountNumber, double initialBalance) {
            if (accountNumber == null || accountNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Número de cuenta no puede estar vacío");
            }
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
            }
            return new SecureBankAccount(accountNumber, initialBalance);
        }
        
        // Métodos públicos controlados
        public String getAccountNumber() {
            return accountNumber; // String es inmutable, es seguro devolverlo
        }
        
        public double getBalance() {
            return balance;
        }
        
        // Devuelve una vista inmutable del historial
        public List<String> getTransactionHistory() {
            return Collections.unmodifiableList(transactionHistory);
        }
        
        // Método para depósito con validación
        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("El monto debe ser positivo");
            }
            balance += amount;
            addTransaction("Depósito: +" + amount);
        }
        
        // Método para retiro con validación
        public boolean withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("El monto debe ser positivo");
            }
            if (amount > balance) {
                addTransaction("Retiro fallido: saldo insuficiente para " + amount);
                return false;
            }
            balance -= amount;
            addTransaction("Retiro: -" + amount);
            return true;
        }
        
        // Método privado para agregar transacciones
        private void addTransaction(String transaction) {
            transactionHistory.add(java.time.LocalDateTime.now() + ": " + transaction);
        }
        
        @Override
        public String toString() {
            return "Cuenta: " + accountNumber + ", Saldo: " + balance;
        }
    }
    
    /**
     * Clase que demuestra intentos de acceso no autorizado
     */
    public static class SecurityTester {
        
        public static void testEncapsulation() {
            System.out.println("=== DEMO: Encapsulación Estricta ===\n");
            
            // Crear cuenta usando factory method
            SecureBankAccount account = SecureBankAccount.createAccount("12345", 1000.0);
            System.out.println("1. Cuenta creada: " + account);
            
            // Acceso legítimo a través de métodos públicos
            System.out.println("2. Número de cuenta: " + account.getAccountNumber());
            System.out.println("3. Saldo actual: " + account.getBalance());
            
            // Operaciones legítimas
            account.deposit(500.0);
            account.withdraw(200.0);
            System.out.println("4. Después de operaciones: " + account);
            
            // Mostrar historial (vista inmutable)
            System.out.println("5. Historial de transacciones:");
            account.getTransactionHistory().forEach(System.out::println);
            
            // Intentar modificar el historial (debería fallar)
            try {
                List<String> history = account.getTransactionHistory();
                history.add("Transacción maliciosa"); // Esto fallará
            } catch (UnsupportedOperationException e) {
                System.out.println("6. ✅ Protección exitosa: No se puede modificar el historial");
            }
            
            // Intentar usar reflexión para acceder a campos privados
            try {
                java.lang.reflect.Field balanceField = SecureBankAccount.class.getDeclaredField("balance");
                balanceField.setAccessible(true);
                balanceField.set(account, 999999.0);
                System.out.println("7. ❌ Acceso no autorizado exitoso: " + account.getBalance());
            } catch (Exception e) {
                System.out.println("7. ✅ Reflexión bloqueada: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SecurityTester.testEncapsulation();
    }
}
