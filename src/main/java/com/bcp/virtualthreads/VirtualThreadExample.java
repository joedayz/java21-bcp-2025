package com.bcp.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Ejemplo completo de Virtual Thread Operations
 * Demuestra los métodos y conceptos clave de los hilos virtuales en Java 21
 */
public class VirtualThreadExample {

    public static void main(String[] args) {
        System.out.println("=== Virtual Thread Operations ===\n");
        
        // Ejemplo 1: Creación básica de hilos virtuales
        //demonstrateBasicVirtualThreads();
        
        // Ejemplo 2: Thread.Builder para crear hilos virtuales
        //demonstrateThreadBuilder();
        
        // Ejemplo 3: ThreadFactory para crear múltiples hilos
        //demonstrateThreadFactory();
        
        // Ejemplo 4: Comparación entre hilos virtuales y de plataforma
        demonstrateVirtualVsPlatform();
        
        // Ejemplo 5: Verificación de tipos de hilos
        //demonstrateThreadTypeChecking();
        
        // Ejemplo 6: Stack traces de hilos
        //demonstrateStackTraces();
        
        System.out.println("\n=== Todos los ejemplos completados ===");
    }
    
    /**
     * Demuestra la creación básica de hilos virtuales
     */
    private static void demonstrateBasicVirtualThreads() {
        System.out.println("1. Creación Básica de Hilos Virtuales:");
        
        // Definir tarea que realizará instrucciones
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Ejecutando tarea en " + threadName);
            
            // Simular trabajo
            try {
                Thread.sleep(1000);
                System.out.println("  Tarea completada en " + threadName);
            } catch (InterruptedException e) {
                System.out.println("  Tarea interrumpida en " + threadName);
                Thread.currentThread().interrupt();
            }
        };
        
        // Método 1: Crear hilo virtual con Thread.ofVirtual()
        Thread t1 = Thread.ofVirtual().name("acme").unstarted(task);
        System.out.println("  Hilo virtual creado: " + t1.getName() + " (Virtual: " + t1.isVirtual() + ")");
        t1.start();
        
        // Método 2: Crear e iniciar hilo virtual directamente
        Thread t2 = Thread.startVirtualThread(task);
        System.out.println("  Hilo virtual iniciado: " + t2.getName() + " (Virtual: " + t2.isVirtual() + ")");
        
        // Esperar a que terminen
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra el uso de Thread.Builder
     */
    private static void demonstrateThreadBuilder() {
        System.out.println("\n2. Thread.Builder para crear Thread y ThreadFactory:");
        
        // Crear Thread.Builder para hilos virtuales
        Thread.Builder.OfVirtual virtualBuilder = Thread.ofVirtual()
            .name("virtual-worker-", 0) // Nombre con contador
            .inheritInheritableThreadLocals(false); // No heredar thread locals
        
        // Crear múltiples hilos virtuales usando el builder
        for (int i = 0; i < 3; i++) {
            Thread virtualThread = virtualBuilder.unstarted(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Hilo virtual " + threadName + " ejecutándose");
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            virtualThread.start();
            System.out.println("  Creado: " + virtualThread.getName() + " (Virtual: " + virtualThread.isVirtual() + ")");
        }
        
        // Crear Thread.Builder para hilos de plataforma
        Thread.Builder.OfPlatform platformBuilder = Thread.ofPlatform()
            .name("platform-worker-", 0)
            .daemon(false);
        
        Thread platformThread = platformBuilder.unstarted(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Hilo de plataforma " + threadName + " ejecutándose");
        });
        
        platformThread.start();
        System.out.println("  Creado: " + platformThread.getName() + " (Virtual: " + platformThread.isVirtual() + ")");
        
        // Esperar un poco
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra el uso de ThreadFactory
     */
    private static void demonstrateThreadFactory() {
        System.out.println("\n3. ThreadFactory para crear múltiples hilos con propiedades idénticas:");
        
        // Crear ThreadFactory para hilos virtuales
        ThreadFactory virtualThreadFactory = Thread.ofVirtual()
            .name("virtual-factory-", 0)
            .factory();
        
        // Crear múltiples hilos usando la factory
        for (int i = 0; i < 5; i++) {
            Thread virtualThread = virtualThreadFactory.newThread(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("  Hilo de factory " + threadName + " ejecutándose");
                
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            virtualThread.start();
        }
        
        // Crear ThreadFactory para hilos de plataforma
        ThreadFactory platformThreadFactory = Thread.ofPlatform()
            .name("platform-factory-", 0)
            .daemon(true)
            .factory();
        
        Thread platformThread = platformThreadFactory.newThread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Hilo de plataforma de factory " + threadName + " ejecutándose");
        });
        
        platformThread.start();
        
        // Esperar un poco
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra la comparación entre hilos virtuales y de plataforma
     */
    private static void demonstrateVirtualVsPlatform() {
        System.out.println("\n4. Comparación: Hilos Virtuales vs Hilos de Plataforma:");
        
        // Crear hilo virtual
        Thread virtualThread = Thread.ofVirtual().name("virtual-demo").unstarted(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Hilo virtual: " + threadName);
            System.out.println("    - Es virtual: " + Thread.currentThread().isVirtual());
            System.out.println("    - Es daemon: " + Thread.currentThread().isDaemon());
            System.out.println("    - Prioridad: " + Thread.currentThread().getPriority());
            System.out.println("    - Grupo: " + Thread.currentThread().getThreadGroup().getName());
        });
        
        // Crear hilo de plataforma
        Thread platformThread = Thread.ofPlatform().name("platform-demo").unstarted(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  Hilo de plataforma: " + threadName);
            System.out.println("    - Es virtual: " + Thread.currentThread().isVirtual());
            System.out.println("    - Es daemon: " + Thread.currentThread().isDaemon());
            System.out.println("    - Prioridad: " + Thread.currentThread().getPriority());
            System.out.println("    - Grupo: " + Thread.currentThread().getThreadGroup().getName());
        });
        
        virtualThread.start();
        platformThread.start();
        
        try {
            virtualThread.join();
            platformThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra la verificación de tipos de hilos
     */
    private static void demonstrateThreadTypeChecking() {
        System.out.println("\n5. Verificación de tipos de hilos con Thread.isVirtual():");
        
        // Crear diferentes tipos de hilos
        Thread virtualThread = Thread.startVirtualThread(() -> {
            System.out.println("  Hilo virtual ejecutándose");
        });
        
        Thread platformThread = Thread.ofPlatform().start(() -> {
            System.out.println("  Hilo de plataforma ejecutándose");
        });
        
        Thread mainThread = Thread.currentThread();
        
        // Verificar tipos
        System.out.println("  Hilo principal es virtual: " + mainThread.isVirtual());
        System.out.println("  Hilo virtual es virtual: " + virtualThread.isVirtual());
        System.out.println("  Hilo de plataforma es virtual: " + platformThread.isVirtual());
        
        try {
            virtualThread.join();
            platformThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Demuestra el uso de getAllStackTraces()
     */
    private static void demonstrateStackTraces() {
        System.out.println("\n6. Thread.getAllStackTraces() - Retorna solo hilos de plataforma:");
        
        // Crear algunos hilos virtuales y de plataforma
        for (int i = 0; i < 3; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Obtener stack traces (solo hilos de plataforma)
        var stackTraces = Thread.getAllStackTraces();
        System.out.println("  Número de hilos en getAllStackTraces: " + stackTraces.size());
        
        System.out.println("  Hilos de plataforma encontrados:");
        stackTraces.keySet().forEach(thread -> {
            System.out.println("    - " + thread.getName() + " (Virtual: " + thread.isVirtual() + ")");
        });
        
        // Esperar un poco para que terminen los hilos
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
