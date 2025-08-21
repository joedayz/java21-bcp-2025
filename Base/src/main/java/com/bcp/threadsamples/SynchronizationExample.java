package com.bcp.threadsamples;

/**
 * Ejemplo que demuestra diferentes tipos de sincronización en Java
 * y los monitores utilizados en cada caso.
 */
public class SynchronizationExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplo de Sincronización en Java ===\n");
        
        // Crear una instancia de la clase Some
        Some s = new Some();
        
        // Crear múltiples hilos que ejecutarán diferentes métodos sincronizados
        Runnable r = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " iniciando ejecución...");
            
            // Llamar al método sincronizado de instancia (monitor: this)
            System.out.println(threadName + " llamando al método sincronizado de instancia...");
            s.a();
            
            // Llamar al método sincronizado estático (monitor: Some.class)
            System.out.println(threadName + " llamando al método sincronizado estático...");
            Some.b();
            
            // Usar un bloque sincronizado (monitor: objeto s)
            System.out.println(threadName + " usando bloque sincronizado...");
            synchronized (s) {
                s.c();
            }
            
            System.out.println(threadName + " terminando ejecución.\n");
        };
        
        // Crear y ejecutar múltiples hilos
        Thread t1 = new Thread(r, "Hilo-1");
        Thread t2 = new Thread(r, "Hilo-2");
        Thread t3 = new Thread(r, "Hilo-3");
        
        System.out.println("Iniciando hilos...\n");
        t1.start();
        t2.start();
        t3.start();
        
        // Esperar a que todos los hilos terminen
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hilo principal interrumpido");
        }
        
        System.out.println("=== Todos los hilos han terminado ===");
    }
}

/**
 * Clase que demuestra diferentes tipos de sincronización
 */
class Some {
    
    /**
     * Método sincronizado de instancia
     * Monitor: el objeto actual (this)
     */
    public synchronized void a() {
        String threadName = Thread.currentThread().getName();
        System.out.println("  " + threadName + " ejecutando método 'a' (sincronizado de instancia)");
        System.out.println("  Monitor: objeto actual (this)");
        
        // Simular trabajo
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("  " + threadName + " terminó método 'a'");
    }
    
    /**
     * Método sincronizado estático
     * Monitor: la clase Some.class
     */
    public static synchronized void b() {
        String threadName = Thread.currentThread().getName();
        System.out.println("  " + threadName + " ejecutando método 'b' (sincronizado estático)");
        System.out.println("  Monitor: Some.class");
        
        // Simular trabajo
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("  " + threadName + " terminó método 'b'");
    }
    
    /**
     * Método no sincronizado
     * Se puede llamar desde un bloque sincronizado
     */
    public void c() {
        String threadName = Thread.currentThread().getName();
        System.out.println("  " + threadName + " ejecutando método 'c' (no sincronizado)");
        System.out.println("  Monitor: objeto s (del bloque sincronizado)");
        
        // Simular trabajo
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("  " + threadName + " terminó método 'c'");
    }
}
