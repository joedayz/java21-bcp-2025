package com.bcp.threadsamples;

/**
 * Ejemplo práctico de sincronización con un contador compartido
 * Demuestra la necesidad de sincronización cuando múltiples hilos
 * acceden a recursos compartidos.
 */
public class SharedCounterExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplo de Contador Compartido ===\n");
        
        // Crear un contador compartido
        SharedCounter counter = new SharedCounter();
        
        // Crear múltiples hilos que incrementan el contador
        Runnable incrementTask = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 1000; i++) {
                counter.increment();
                if (i % 200 == 0) {
                    System.out.println(threadName + " - Iteración " + i + ", Contador: " + counter.getValue());
                }
            }
        };
        
        // Crear hilos
        Thread t1 = new Thread(incrementTask, "Hilo-1");
        Thread t2 = new Thread(incrementTask, "Hilo-2");
        Thread t3 = new Thread(incrementTask, "Hilo-3");
        
        System.out.println("Iniciando hilos para incrementar contador...\n");
        
        // Iniciar hilos
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
        
        System.out.println("\n=== Resultados ===");
        System.out.println("Valor final del contador: " + counter.getValue());
        System.out.println("Valor esperado: 3000");
        System.out.println("¿Resultado correcto?: " + (counter.getValue() == 3000));
        
        // Demostrar el problema sin sincronización
        System.out.println("\n=== Demostración del problema sin sincronización ===");
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        };
        
        Thread t4 = new Thread(unsafeTask, "Hilo-4");
        Thread t5 = new Thread(unsafeTask, "Hilo-5");
        Thread t6 = new Thread(unsafeTask, "Hilo-6");
        
        t4.start();
        t5.start();
        t6.start();
        
        try {
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Valor final del contador NO seguro: " + unsafeCounter.getValue());
        System.out.println("Valor esperado: 3000");
        System.out.println("¿Resultado correcto?: " + (unsafeCounter.getValue() == 3000));
    }
}

/**
 * Contador seguro con sincronización
 */
class SharedCounter {
    private int count = 0;
    
    /**
     * Método sincronizado para incrementar el contador
     * Garantiza que solo un hilo puede ejecutar este método a la vez
     */
    public synchronized void increment() {
        count++;
    }
    
    /**
     * Método sincronizado para obtener el valor del contador
     */
    public synchronized int getValue() {
        return count;
    }
}

/**
 * Contador NO seguro sin sincronización
 * Demuestra el problema de condición de carrera
 */
class UnsafeCounter {
    private int count = 0;
    
    /**
     * Método NO sincronizado - puede causar condiciones de carrera
     */
    public void increment() {
        // Simular una operación no atómica
        int current = count;
        // Simular algún procesamiento
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        count = current + 1;
    }
    
    public int getValue() {
        return count;
    }
}
