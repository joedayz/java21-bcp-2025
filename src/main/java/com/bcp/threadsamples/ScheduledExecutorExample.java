package com.bcp.threadsamples;

import java.util.concurrent.*;

/**
 * Ejemplo específico de ScheduledExecutorService
 * Demuestra el uso de scheduleAtFixedRate y unconfigurableExecutorService
 * como se muestra en la documentación de Executors
 */
public class ScheduledExecutorExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplo de ScheduledExecutorService ===\n");
        
        // Definir la tarea que realizará acciones concurrentes
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Ejecutando tarea programada en " + threadName + " - " + 
                             java.time.LocalTime.now());
            
            // Realizar acciones concurrentes
            try {
                // Simular algún trabajo
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("  Tarea interrumpida");
                Thread.currentThread().interrupt();
            }
        };
        
        // Crear un ScheduledExecutorService con pool de 3 hilos
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        System.out.println("ScheduledExecutorService creado con pool de 3 hilos");
        
        // Programar una o más tareas con diferentes retrasos y períodos usando el mismo pool de hilos
        System.out.println("Programando tarea para ejecutarse cada 5 segundos con retraso inicial de 10 segundos...");
        ScheduledFuture<?> scheduledTask = ses.scheduleAtFixedRate(task, 10, 5, TimeUnit.SECONDS);
        
        // Crear un ExecutorService no configurable que "congela" la configuración
        ExecutorService es = Executors.unconfigurableExecutorService(ses);
        System.out.println("ExecutorService no configurable creado");
        
        // Usar el executor no configurable para algunas tareas adicionales
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            es.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Tarea adicional " + taskId + " ejecutándose en " + threadName);
                
                try {
                    Thread.sleep(1000);
                    System.out.println("  Tarea adicional " + taskId + " completada");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Esperar un tiempo para ver las tareas programadas en acción
        System.out.println("\nEsperando 25 segundos para ver las tareas programadas...");
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Cancelar la tarea programada
        System.out.println("Cancelando tarea programada...");
        scheduledTask.cancel(false);
        
        // Cerrar los executors
        System.out.println("Cerrando executors...");
        shutdownExecutors(ses, es);
        
        System.out.println("\n=== Ejemplo completado ===");
    }
    
    /**
     * Cierra ambos executors de forma segura
     */
    private static void shutdownExecutors(ScheduledExecutorService ses, ExecutorService es) {
        try {
            // Cerrar el executor no configurable primero
            es.shutdown();
            System.out.println("ExecutorService no configurable cerrado");
            
            // Cerrar el scheduled executor
            ses.shutdown();
            System.out.println("ScheduledExecutorService cerrado");
            
            // Esperar a que ambos terminen
            if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("ExecutorService no configurable no terminó en 5 segundos");
                es.shutdownNow();
            }
            
            if (!ses.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("ScheduledExecutorService no terminó en 5 segundos");
                ses.shutdownNow();
            }
            
        } catch (InterruptedException e) {
            System.out.println("Interrumpido durante el cierre");
            es.shutdownNow();
            ses.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
