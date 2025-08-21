package com.bcp.threadsamples;

/**
 * Ejemplo sencillo de Daemon Thread
 * Demuestra la creación y gestión básica de un hilo daemon
 */
public class DaemonThreadExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplo de Daemon Thread ===\n");
        
        // Definir la tarea que ejecutará el hilo
        Runnable r = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " iniciando trabajo...");
            
            // Simular trabajo del hilo
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000); // Esperar 1 segundo
                    System.out.println(threadName + " - Iteración " + i + " completada");
                } catch (InterruptedException e) {
                    System.out.println(threadName + " interrumpido durante el trabajo");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            
            System.out.println(threadName + " terminó su trabajo");
        };
        
        // Crear el hilo con nombre
        Thread t = new Thread(r, "My Thread");
        
        // Configurar como hilo daemon
        t.setDaemon(true);
        System.out.println("Hilo configurado como daemon: " + t.isDaemon());
        
        // Iniciar el hilo
        t.start();
        System.out.println("Hilo iniciado");
        
        // Obtener el ID del hilo
        long id = t.getId();
        System.out.println("ID del hilo: " + id);
        
        // Verificar si es daemon
        if (t.isDaemon()) {
            System.out.println("Es un hilo daemon - se auto-terminará una vez que todos los hilos de usuario hayan terminado");
        }
        
        // Establecer prioridad del hilo (1-10, donde 5 es normal)
        t.setPriority(3);
        System.out.println("Prioridad del hilo establecida en: " + t.getPriority());
        
        // Esperar a que el hilo termine
        try {
            System.out.println("Esperando a que el hilo termine...");
            t.join(); // wait for the thread to terminate
            System.out.println("Hilo terminó completamente");
        } catch (InterruptedException ex) {
            System.out.println("Hilo principal interrumpido mientras esperaba");
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== Ejemplo completado ===");
    }
}
