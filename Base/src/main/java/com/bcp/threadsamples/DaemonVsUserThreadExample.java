package com.bcp.threadsamples;

/**
 * Ejemplo que demuestra la diferencia entre hilos daemon y hilos de usuario
 * Muestra cómo los hilos daemon se terminan automáticamente cuando todos
 * los hilos de usuario terminan.
 */
public class DaemonVsUserThreadExample {

    public static void main(String[] args) {
        System.out.println("=== Comparación: Daemon vs User Thread ===\n");
        
        // Crear un hilo daemon
        Thread daemonThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " (Daemon) iniciando...");
            
            // Trabajo infinito para demostrar que se termina automáticamente
            int counter = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    counter++;
                    System.out.println(threadName + " (Daemon) - Contador: " + counter);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " (Daemon) interrumpido");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println(threadName + " (Daemon) terminado");
        }, "DaemonThread");
        
        // Crear un hilo de usuario
        Thread userThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " (User) iniciando...");
            
            // Trabajo finito
            for (int i = 1; i <= 3; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(threadName + " (User) - Iteración " + i + "/3");
                } catch (InterruptedException e) {
                    System.out.println(threadName + " (User) interrumpido");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println(threadName + " (User) terminado");
        }, "UserThread");
        
        // Configurar el daemon
        daemonThread.setDaemon(true);
        
        // Mostrar información de los hilos
        System.out.println("Información de los hilos:");
        System.out.println("- " + daemonThread.getName() + " es daemon: " + daemonThread.isDaemon());
        System.out.println("- " + userThread.getName() + " es daemon: " + userThread.isDaemon());
        System.out.println();
        
        // Iniciar ambos hilos
        System.out.println("Iniciando hilos...");
        daemonThread.start();
        userThread.start();
        
        // Esperar solo al hilo de usuario
        try {
            System.out.println("Esperando a que el hilo de usuario termine...");
            userThread.join();
            System.out.println("Hilo de usuario terminó");
            
            // Verificar si el daemon sigue vivo
            if (daemonThread.isAlive()) {
                System.out.println("El hilo daemon aún está vivo después de que el usuario terminó");
                System.out.println("Esperando un poco más para ver si se termina automáticamente...");
                Thread.sleep(2000);
                
                if (daemonThread.isAlive()) {
                    System.out.println("El daemon sigue vivo, interrumpiéndolo manualmente...");
                    daemonThread.interrupt();
                } else {
                    System.out.println("El daemon se terminó automáticamente");
                }
            } else {
                System.out.println("El hilo daemon ya se terminó automáticamente");
            }
            
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido");
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== Comparación completada ===");
        System.out.println("Nota: Los hilos daemon se terminan automáticamente cuando");
        System.out.println("todos los hilos de usuario han terminado su ejecución.");
    }
}
