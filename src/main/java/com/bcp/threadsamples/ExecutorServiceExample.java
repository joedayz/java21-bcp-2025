package com.bcp.threadsamples;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ejemplo completo de ExecutorService
 * Demuestra los diferentes tipos de ExecutorService disponibles en java.util.concurrent.Executors
 */
public class ExecutorServiceExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplos de ExecutorService ===\n");
        
        // Ejemplo 1: Fixed Thread Pool
        demonstrateFixedThreadPool();
        
        // Ejemplo 2: Cached Thread Pool
        demonstrateCachedThreadPool();
        
        // Ejemplo 3: Single Thread Executor
        demonstrateSingleThreadExecutor();
        
        // Ejemplo 4: Work Stealing Pool
        demonstrateWorkStealingPool();
        
        // Ejemplo 5: Scheduled Thread Pool
        demonstrateScheduledThreadPool();
        
        // Ejemplo 6: Single Thread Scheduled Executor
        demonstrateSingleThreadScheduledExecutor();
        
        // Ejemplo 7: Unconfigurable Executor Service
        demonstrateUnconfigurableExecutorService();
        
        System.out.println("\n=== Todos los ejemplos completados ===");
    }
    
    /**
     * Fixed Thread Pool: Reutiliza un número fijo de hilos
     */
    private static void demonstrateFixedThreadPool() {
        System.out.println("1. Fixed Thread Pool (3 hilos):");
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Lanzar 10 tareas con máximo 3 ejecutándose simultáneamente
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea " + taskId + " ejecutándose en " + threadName);
                
                // Simular trabajo
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("  Tarea " + taskId + " completada en " + threadName);
            });
        }
        
        // Cerrar el executor
        shutdownExecutor(executor, "Fixed Thread Pool");
    }
    
    /**
     * Cached Thread Pool: Crea nuevos hilos según sea necesario o reutiliza existentes
     */
    private static void demonstrateCachedThreadPool() {
        System.out.println("\n2. Cached Thread Pool:");
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // Lanzar tareas con diferentes duraciones
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea " + taskId + " iniciando en " + threadName);
                
                try {
                    Thread.sleep(1000 + (taskId * 200)); // Diferentes duraciones
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.println("  Tarea " + taskId + " terminando en " + threadName);
            });
        }
        
        shutdownExecutor(executor, "Cached Thread Pool");
    }
    
    /**
     * Single Thread Executor: Usa un solo hilo trabajador
     */
    private static void demonstrateSingleThreadExecutor() {
        System.out.println("\n3. Single Thread Executor:");
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        // Las tareas se ejecutan secuencialmente
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea " + taskId + " ejecutándose en " + threadName);
                
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        shutdownExecutor(executor, "Single Thread Executor");
    }
    
    /**
     * Work Stealing Pool: Mantiene suficientes hilos para soportar el nivel de paralelismo dado
     */
    private static void demonstrateWorkStealingPool() {
        System.out.println("\n4. Work Stealing Pool:");
        
        ExecutorService executor = Executors.newWorkStealingPool(4); // 4 hilos
        
        // Lanzar tareas que pueden ser "robadas" por otros hilos
        for (int i = 0; i < 8; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea " + taskId + " en " + threadName);
                
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        shutdownExecutor(executor, "Work Stealing Pool");
    }
    
    /**
     * Scheduled Thread Pool: Programa tareas para ejecutarse con retraso y/o periódicamente
     */
    private static void demonstrateScheduledThreadPool() {
        System.out.println("\n5. Scheduled Thread Pool:");
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        
        // Tarea con retraso inicial
        executor.schedule(() -> {
            System.out.println("  Tarea programada ejecutándose después de 2 segundos");
        }, 2, TimeUnit.SECONDS);
        
        // Tarea periódica
        ScheduledFuture<?> periodicTask = executor.scheduleAtFixedRate(() -> {
            System.out.println("  Tarea periódica ejecutándose cada 1 segundo");
        }, 1, 1, TimeUnit.SECONDS);
        
        // Cancelar la tarea periódica después de 5 segundos
        executor.schedule(() -> {
            periodicTask.cancel(false);
            System.out.println("  Tarea periódica cancelada");
        }, 5, TimeUnit.SECONDS);
        
        // Esperar un poco para ver las tareas programadas
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        shutdownExecutor(executor, "Scheduled Thread Pool");
    }
    
    /**
     * Single Thread Scheduled Executor: Programa tareas usando un solo hilo trabajador
     */
    private static void demonstrateSingleThreadScheduledExecutor() {
        System.out.println("\n6. Single Thread Scheduled Executor:");
        
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        
        // Tarea con retraso usando un solo hilo
        executor.schedule(() -> {
            System.out.println("  Tarea única programada ejecutándose");
        }, 1, TimeUnit.SECONDS);
        
        // Tarea periódica con un solo hilo
        ScheduledFuture<?> singleThreadTask = executor.scheduleAtFixedRate(() -> {
            System.out.println("  Tarea periódica en hilo único ejecutándose");
        }, 2, 2, TimeUnit.SECONDS);
        
        // Cancelar después de 6 segundos
        executor.schedule(() -> {
            singleThreadTask.cancel(false);
            System.out.println("  Tarea periódica de hilo único cancelada");
        }, 6, TimeUnit.SECONDS);
        
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        shutdownExecutor(executor, "Single Thread Scheduled Executor");
    }
    
    /**
     * Unconfigurable Executor Service: Proporciona una forma de "congelar" otra configuración de ExecutorService
     */
    private static void demonstrateUnconfigurableExecutorService() {
        System.out.println("\n7. Unconfigurable Executor Service:");
        
        // Crear un ScheduledExecutorService
        ScheduledExecutorService originalExecutor = Executors.newScheduledThreadPool(2);
        
        // Hacerlo no configurable
        ExecutorService unconfigurableExecutor = Executors.unconfigurableExecutorService(originalExecutor);
        
        System.out.println("  ExecutorService original creado");
        System.out.println("  ExecutorService hecho no configurable");
        
        // Intentar usar el executor no configurable
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            unconfigurableExecutor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea " + taskId + " ejecutándose en " + threadName);
                
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        shutdownExecutor(unconfigurableExecutor, "Unconfigurable Executor Service");
    }
    
    /**
     * Método auxiliar para cerrar un ExecutorService de forma segura
     */
    private static void shutdownExecutor(ExecutorService executor, String name) {
        try {
            System.out.println("  Cerrando " + name + "...");
            executor.shutdown(); // Dejar de aceptar nuevas tareas
            
            // Esperar a que las tareas existentes terminen
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("  " + name + " no terminó en 5 segundos, forzando cierre...");
                executor.shutdownNow(); // Cancelar tareas que aún están ejecutándose
                
                // Esperar un poco más
                if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.out.println("  " + name + " no pudo ser cerrado completamente");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("  Interrumpido mientras se cerraba " + name);
            executor.shutdownNow(); // Cancelar tareas cuando el hilo lanzador fue interrumpido
            Thread.currentThread().interrupt(); // Continuar el proceso de interrupción del hilo lanzador
        }
        
        System.out.println("  " + name + " cerrado");
    }
}
