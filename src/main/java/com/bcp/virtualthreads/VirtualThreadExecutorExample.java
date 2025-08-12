package com.bcp.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ejemplo de uso de hilos virtuales con ExecutorService
 * Demuestra las ventajas de rendimiento de los hilos virtuales
 */
public class VirtualThreadExecutorExample {

    public static void main(String[] args) {
        System.out.println("=== Virtual Threads con ExecutorService ===\n");
        
        // Ejemplo 1: Comparación de rendimiento
        comparePerformance();
        
        // Ejemplo 2: Uso de ExecutorService con hilos virtuales
        demonstrateVirtualThreadExecutor();
        
        // Ejemplo 3: Manejo de excepciones en hilos virtuales
        demonstrateExceptionHandling();
        
        System.out.println("\n=== Ejemplos completados ===");
    }
    
    /**
     * Compara el rendimiento entre hilos de plataforma y virtuales
     */
    private static void comparePerformance() {
        System.out.println("1. Comparación de Rendimiento:");
        
        int taskCount = 1000;
        
        // Probar con hilos de plataforma (limitado por recursos del sistema)
        System.out.println("  Probando con hilos de plataforma (limitado a 100 tareas)...");
        long startTime = System.currentTimeMillis();
        
        try (ExecutorService platformExecutor = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < taskCount; i++) {
                final int taskId = i;
                platformExecutor.submit(() -> {
                    try {
                        Thread.sleep(100); // Simular trabajo I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            platformExecutor.shutdown();
            platformExecutor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        long platformTime = System.currentTimeMillis() - startTime;
        System.out.println("  Tiempo con hilos de plataforma: " + platformTime + "ms");
        
        // Probar con hilos virtuales (puede manejar muchos más)
        System.out.println("  Probando con hilos virtuales (" + taskCount + " tareas)...");
        startTime = System.currentTimeMillis();
        
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                final int taskId = i;
                virtualExecutor.submit(() -> {
                    try {
                        Thread.sleep(100); // Simular trabajo I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            virtualExecutor.shutdown();
            virtualExecutor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        long virtualTime = System.currentTimeMillis() - startTime;
        System.out.println("  Tiempo con hilos virtuales: " + virtualTime + "ms");
        
        System.out.println("  Mejora de rendimiento: " + 
                          String.format("%.1f", (double) platformTime / virtualTime) + "x");
    }
    
    /**
     * Demuestra el uso de ExecutorService con hilos virtuales
     */
    private static void demonstrateVirtualThreadExecutor() {
        System.out.println("\n2. ExecutorService con Hilos Virtuales:");
        
        // Crear executor con hilos virtuales
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            AtomicInteger completedTasks = new AtomicInteger(0);
            int totalTasks = 50;
            
            System.out.println("  Enviando " + totalTasks + " tareas al executor...");
            
            // Enviar múltiples tareas
            for (int i = 0; i < totalTasks; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("    Tarea " + taskId + " ejecutándose en " + threadName + 
                                     " (Virtual: " + Thread.currentThread().isVirtual() + ")");
                    
                    try {
                        // Simular trabajo que incluye I/O
                        Thread.sleep(200);
                        
                        // Simular algún procesamiento
                        int result = taskId * taskId;
                        
                        completedTasks.incrementAndGet();
                        System.out.println("    Tarea " + taskId + " completada. Resultado: " + result);
                        
                    } catch (InterruptedException e) {
                        System.out.println("    Tarea " + taskId + " interrumpida");
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Cerrar el executor y esperar a que terminen todas las tareas
            System.out.println("  Cerrando executor...");
            executor.shutdown();
            
            if (executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("  Todas las tareas completadas. Total: " + completedTasks.get());
            } else {
                System.out.println("  Timeout esperando la terminación de tareas");
                executor.shutdownNow();
            }
            
        } catch (InterruptedException e) {
            System.out.println("  Interrumpido durante la ejecución");
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra el manejo de excepciones en hilos virtuales
     */
    private static void demonstrateExceptionHandling() {
        System.out.println("\n3. Manejo de Excepciones en Hilos Virtuales:");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            // Tarea que lanza una excepción
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea problemática ejecutándose en " + threadName);
                
                // Simular algún trabajo
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                
                // Lanzar una excepción
                throw new RuntimeException("Error simulado en hilo virtual");
            });
            
            // Tarea normal
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea normal ejecutándose en " + threadName);
                
                try {
                    Thread.sleep(300);
                    System.out.println("  Tarea normal completada exitosamente");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            // Tarea con manejo de excepciones
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea con manejo de excepciones en " + threadName);
                
                try {
                    Thread.sleep(400);
                    
                    // Intentar algo que podría fallar
                    if (Math.random() > 0.5) {
                        throw new IllegalStateException("Error aleatorio");
                    }
                    
                    System.out.println("  Tarea con manejo completada exitosamente");
                    
                } catch (Exception e) {
                    System.out.println("  Excepción capturada en hilo virtual: " + e.getMessage());
                }
            });
            
            // Cerrar y esperar
            executor.shutdown();
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            
        } catch (InterruptedException e) {
            System.out.println("  Interrumpido durante el manejo de excepciones");
            Thread.currentThread().interrupt();
        }
    }
}
