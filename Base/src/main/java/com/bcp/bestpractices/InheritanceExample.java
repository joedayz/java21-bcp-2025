package com.bcp.bestpractices;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo 3: Do not break subclass assumptions about inherited code
 * Ejemplo 4: Design classes and methods for inheritance or declare them final or private
 * 
 * Demuestra los problemas de herencia y cómo evitarlos.
 */
public class InheritanceExample {
    
    /**
     * ❌ CLASE PROBLEMÁTICA - No diseñada para herencia
     */
    public static class BadSuperclass {
        private List<String> items = new ArrayList<>();
        
        public BadSuperclass() {
            // ❌ Llamar método sobreescribible en constructor
            initializeItems();
        }
        
        // ❌ Método sobreescribible que se llama en constructor
        public void initializeItems() {
            items.add("Item 1");
            items.add("Item 2");
        }
        
        // ❌ Método sobreescribible sin validaciones
        public void addItem(String item) {
            items.add(item);
        }
        
        public List<String> getItems() {
            return items;
        }
        
        public int getItemCount() {
            return items.size();
        }
    }
    
    /**
     * ❌ SUBCLASE MALICIOSA - Explota las debilidades de la superclase
     */
    public static class MaliciousSubclass extends BadSuperclass {
        private boolean initialized = false;
        
        @Override
        public void initializeItems() {
            // ❌ Comportamiento inesperado en constructor
            if (!initialized) {
                System.out.println("⚠️  Constructor de superclase ejecutándose...");
                initialized = true;
            }
        }
        
        @Override
        public void addItem(String item) {
            // ❌ Comportamiento malicioso
            if (item != null && item.contains("hack")) {
                System.out.println("🚨 Intento de hack detectado: " + item);
                return; // Bloquear el hack
            }
            super.addItem(item);
        }
        
        @Override
        public int getItemCount() {
            // ❌ Mentir sobre el conteo
            return super.getItemCount() * 2; // Reportar el doble
        }
    }
    
    /**
     * ✅ CLASE BIEN DISEÑADA - Final o diseñada para herencia
     */
    public static final class SecureClass {
        private final List<String> items = new ArrayList<>();
        
        public SecureClass() {
            // ✅ Constructor simple, sin llamadas a métodos sobreescribibles
            initializeItemsInternal();
        }
        
        // ✅ Método privado - no puede ser sobreescribido
        private void initializeItemsInternal() {
            items.add("Secure Item 1");
            items.add("Secure Item 2");
        }
        
        // ✅ Método final - no puede ser sobreescribido
        public final void addItem(String item) {
            if (item == null || item.trim().isEmpty()) {
                throw new IllegalArgumentException("Item no puede ser nulo o vacío");
            }
            items.add(item);
        }
        
        // ✅ Método final - no puede ser sobreescribido
        public final List<String> getItems() {
            return new ArrayList<>(items); // Copia defensiva
        }
        
        // ✅ Método final - no puede ser sobreescribido
        public final int getItemCount() {
            return items.size();
        }
    }
    
    /**
     * ✅ CLASE DISEÑADA PARA HERENCIA - Con hooks protegidos
     */
    public static abstract class WellDesignedSuperclass {
        private final List<String> items = new ArrayList<>();
        
        public WellDesignedSuperclass() {
            // ✅ Constructor llama método final que usa hook
            initializeItems();
        }
        
        // ✅ Método final que usa hook protegido
        public final void initializeItems() {
            items.add("Base Item 1");
            items.add("Base Item 2");
            // ✅ Hook que las subclases pueden sobreescribir
            addCustomItems();
        }
        
        // ✅ Hook protegido - las subclases pueden sobreescribir
        protected void addCustomItems() {
            // Implementación por defecto vacía
        }
        
        // ✅ Método final para agregar items
        public final void addItem(String item) {
            validateItem(item);
            items.add(item);
            onItemAdded(item);
        }
        
        // ✅ Validación en método final
        private void validateItem(String item) {
            if (item == null || item.trim().isEmpty()) {
                throw new IllegalArgumentException("Item inválido");
            }
        }
        
        // ✅ Hook para notificar cuando se agrega un item
        protected void onItemAdded(String item) {
            // Las subclases pueden sobreescribir este método
        }
        
        public final List<String> getItems() {
            return new ArrayList<>(items);
        }
        
        public final int getItemCount() {
            return items.size();
        }
    }
    
    /**
     * ✅ SUBCLASE SEGURA - Usa los hooks correctamente
     */
    public static class GoodSubclass extends WellDesignedSuperclass {
        private int customItemCount = 0;
        
        @Override
        protected void addCustomItems() {
            // ✅ Usar hook de forma segura
            super.addItem("Custom Item " + (++customItemCount));
        }
        
        @Override
        protected void onItemAdded(String item) {
            // ✅ Hook para logging
            System.out.println("Item agregado: " + item);
        }
    }
    
    /**
     * Clase que demuestra los problemas y soluciones
     */
    public static class InheritanceTester {
        
        public static void testInheritanceProblems() {
            System.out.println("=== DEMO: Problemas de Herencia ===\n");
            
            System.out.println("1. ❌ CLASE PROBLEMÁTICA");
            BadSuperclass badClass = new BadSuperclass();
            badClass.addItem("Item normal");
            System.out.println("Items: " + badClass.getItems());
            System.out.println("Conteo: " + badClass.getItemCount());
            
            System.out.println("\n2. ❌ SUBCLASE MALICIOSA");
            MaliciousSubclass maliciousClass = new MaliciousSubclass();
            maliciousClass.addItem("Item normal");
            maliciousClass.addItem("hack attempt");
            System.out.println("Items: " + maliciousClass.getItems());
            System.out.println("Conteo (mentiroso): " + maliciousClass.getItemCount());
            
            System.out.println("\n3. ✅ CLASE SEGURA (FINAL)");
            SecureClass secureClass = new SecureClass();
            secureClass.addItem("Item seguro");
            System.out.println("Items: " + secureClass.getItems());
            System.out.println("Conteo: " + secureClass.getItemCount());
            
            System.out.println("\n4. ✅ CLASE DISEÑADA PARA HERENCIA");
            GoodSubclass goodSubclass = new GoodSubclass();
            goodSubclass.addItem("Item de subclase");
            System.out.println("Items: " + goodSubclass.getItems());
            System.out.println("Conteo: " + goodSubclass.getItemCount());
            
            System.out.println("\n5. LECCIONES APRENDIDAS");
            System.out.println("✅ Usar 'final' para clases que no deben heredarse");
            System.out.println("✅ Usar 'final' para métodos críticos");
            System.out.println("✅ No llamar métodos sobreescribibles en constructores");
            System.out.println("✅ Usar hooks protegidos para extensibilidad");
            System.out.println("✅ Validar en métodos finales");
            System.out.println("✅ Hacer copias defensivas en getters");
        }
    }
    
    public static void main(String[] args) {
        InheritanceTester.testInheritanceProblems();
    }
}
