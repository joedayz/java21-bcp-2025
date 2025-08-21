package com.bcp.threadsamples;

import java.util.concurrent.*;

/**
 * Ejemplo de gestión del ciclo de vida del ExecutorService
 * Demuestra cómo iniciar, detener la aceptación de nuevas tareas,
 * esperar la finalización y detener concurrentemente.
 */
public class ExecutorServiceLifecycleExample {

    public static void main(String[] args) {
        System.out.println("=== Gestión del Ciclo de Vida del ExecutorService ===\n");
        
        // Ejemplo 1: Gestión básica del ciclo de vida
        demonstrateBasicLifecycle();
        
        // Ejemplo 2: Gestión con timeout
        demonstrateLifecycleWithTimeout();
        
        // Ejemplo 3: Gestión con interrupción
        demonstrateLifecycleWithInterruption();
        
        System.out.println("\n=== Ejemplos de ciclo de vida completados ===");
    }
    
    /**
     * Demuestra la gestión básica del ciclo de vida
     */
    private static void demonstrateBasicLifecycle() {
        System.out.println("1. Gestión Básica del Ciclo de Vida:");
        
        // Crear pool de 3 hilos
        ExecutorService es = Executors.newFixedThreadPool(3);
        
        System.out.println("  ExecutorService creado con pool de 3 hilos");
        
        // Lanzar 10 tareas Runnable con máximo 3 ejecutándose simultáneamente
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            es.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("    Tarea " + taskId + " ejecutándose en " + threadName);
                
                // Realizar acciones concurrentes y verificar interrupción
                try {
                    Thread.sleep(1000); // Simular trabajo
                    
                    // Verificar si el hilo fue interrumpido
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("    Tarea " + taskId + " detectó interrupción");
                        return;
                    }
                    
                    System.out.println("    Tarea " + taskId + " completada en " + threadName);
                } catch (InterruptedException e) {
                    System.out.println("    Tarea " + taskId + " interrumpida durante el trabajo");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Cerrar el executor de forma segura
        shutdownExecutorGracefully(es, "ExecutorService Básico");
    }
    
    /**
     * Demuestra la gestión del ciclo de vida con timeout
     */
    private static void demonstrateLifecycleWithTimeout() {
        System.out.println("\n2. Gestión del Ciclo de Vida con Timeout:");
        
        ExecutorService es = Executors.newFixedThreadPool(2);
        
        System.out.println("  ExecutorService creado con pool de 2 hilos");
        
        // Lanzar tareas que tardan más tiempo
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            es.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("    Tarea " + taskId + " iniciando en " + threadName);
                
                try {
                    Thread.sleep(3000); // Tareas que tardan 3 segundos
                    System.out.println("    Tarea " + taskId + " completada en " + threadName);
                } catch (InterruptedException e) {
                    System.out.println("    Tarea " + taskId + " interrumpida");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Cerrar con timeout de 5 segundos
        shutdownExecutorWithTimeout(es, "ExecutorService con Timeout", 5);
    }
    
    /**
     * Demuestra la gestión del ciclo de vida con interrupción
     */
    private static void demonstrateLifecycleWithInterruption() {
        System.out.println("\n3. Gestión del Ciclo de Vida con Interrupción:");
        
        ExecutorService es = Executors.newFixedThreadPool(2);
        
        System.out.println("  ExecutorService creado con pool de 2 hilos");
        
        // Lanzar tareas largas
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            es.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("    Tarea " + taskId + " iniciando en " + threadName);
                
                try {
                    Thread.sleep(5000); // Tareas muy largas
                    System.out.println("    Tarea " + taskId + " completada en " + threadName);
                } catch (InterruptedException e) {
                    System.out.println("    Tarea " + taskId + " interrumpida");
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Simular interrupción del hilo principal después de 2 segundos
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("  Interrumpiendo hilo principal...");
                mainThread.interrupt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        
        // Intentar cerrar el executor (será interrumpido)
        shutdownExecutorWithInterruption(es, "ExecutorService con Interrupción");
    }
    
    /**
     * Cierra un ExecutorService de forma elegante
     */
    private static void shutdownExecutorGracefully(ExecutorService es, String name) {
        try {
            System.out.println("  Cerrando " + name + "...");
            
            // Detener la aceptación de nuevas tareas
            es.shutdown();
            System.out.println("  " + name + " ya no acepta nuevas tareas");
            
            // Esperar a que las tareas existentes terminen y verificar si todas se detuvieron realmente
            if (!es.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("  " + name + " no terminó en 30 segundos");
                
                // Solicitar cancelación de tareas que aún están ejecutándose
                System.out.println("  Solicitando cancelación de tareas en ejecución...");
                es.shutdownNow();
                
                // Esperar un poco más
                if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("  " + name + " no pudo ser cerrado completamente");
                } else {
                    System.out.println("  " + name + " cerrado después de cancelación forzada");
                }
            } else {
                System.out.println("  " + name + " cerrado exitosamente");
            }
            
        } catch (InterruptedException e) {
            System.out.println("  Hilo principal interrumpido mientras esperaba la terminación");
            
            // Solicitar cancelación de tareas en ejecución cuando el hilo lanzador fue interrumpido
            System.out.println("  Solicitando cancelación de tareas en ejecución...");
            es.shutdownNow();
            
            // Continuar el proceso de interrupción del hilo lanzador
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Cierra un ExecutorService con timeout específico
     */
    private static void shutdownExecutorWithTimeout(ExecutorService es, String name, int timeoutSeconds) {
        try {
            System.out.println("  Cerrando " + name + " con timeout de " + timeoutSeconds + " segundos...");
            
            es.shutdown();
            
            if (!es.awaitTermination(timeoutSeconds, TimeUnit.SECONDS)) {
                System.out.println("  " + name + " no terminó en " + timeoutSeconds + " segundos, forzando cierre...");
                es.shutdownNow();
                
                if (!es.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.out.println("  " + name + " no pudo ser cerrado completamente");
                } else {
                    System.out.println("  " + name + " cerrado después de timeout");
                }
            } else {
                System.out.println("  " + name + " cerrado exitosamente dentro del timeout");
            }
            
        } catch (InterruptedException e) {
            System.out.println("  Interrumpido durante el cierre de " + name);
            es.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Cierra un ExecutorService manejando interrupciones
     */
    private static void shutdownExecutorWithInterruption(ExecutorService es, String name) {
        try {
            System.out.println("  Cerrando " + name + "...");
            
            es.shutdown();
            
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("  " + name + " no terminó en 10 segundos, forzando cierre...");
                es.shutdownNow();
            }
            
        } catch (InterruptedException e) {
            System.out.println("  Hilo principal interrumpido durante el cierre de " + name);
            
            // Solicitar cancelación de tareas en ejecución cuando el hilo lanzador fue interrumpido
            es.shutdownNow();
            
            // Continuar el proceso de interrupción del hilo lanzador
            Thread.currentThread().interrupt();
        }
    }
}
