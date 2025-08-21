package com.bcp.threadsamples;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ejemplo clásico de Productor-Consumidor
 * Demuestra el uso de wait() y notify() para comunicación entre hilos
 */
public class ProducerConsumerExample {

    public static void main(String[] args) {
        System.out.println("=== Ejemplo Productor-Consumidor ===\n");
        
        // Crear el buffer compartido
        SharedBuffer buffer = new SharedBuffer(5);
        
        // Crear productor
        Producer producer = new Producer(buffer, "Productor");
        
        // Crear consumidor
        Consumer consumer = new Consumer(buffer, "Consumidor");
        
        // Iniciar hilos
        producer.start();
        consumer.start();
        
        // Esperar un tiempo para que se ejecute
        try {
            Thread.sleep(10000); // 10 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Interrumpir hilos para terminar
        producer.interrupt();
        consumer.interrupt();
        
        System.out.println("\n=== Ejemplo terminado ===");
    }
}

/**
 * Buffer compartido entre productor y consumidor
 */
class SharedBuffer {
    private Queue<Integer> buffer;
    private int maxSize;
    
    public SharedBuffer(int maxSize) {
        this.buffer = new LinkedList<>();
        this.maxSize = maxSize;
    }
    
    /**
     * Método sincronizado para producir un elemento
     * Si el buffer está lleno, el hilo espera
     */
    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() >= maxSize) {
            System.out.println("Buffer lleno. Productor esperando...");
            wait(); // Libera el monitor y espera
        }
        
        buffer.offer(item);
        System.out.println("Producido: " + item + " | Buffer: " + buffer.size() + "/" + maxSize);
        
        // Notificar a los consumidores que hay elementos disponibles
        notify();
    }
    
    /**
     * Método sincronizado para consumir un elemento
     * Si el buffer está vacío, el hilo espera
     */
    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer vacío. Consumidor esperando...");
            wait(); // Libera el monitor y espera
        }
        
        int item = buffer.poll();
        System.out.println("Consumido: " + item + " | Buffer: " + buffer.size() + "/" + maxSize);
        
        // Notificar a los productores que hay espacio disponible
        notify();
        
        return item;
    }
    
    /**
     * Obtener el tamaño actual del buffer
     */
    public synchronized int getSize() {
        return buffer.size();
    }
}

/**
 * Clase Productor que genera elementos
 */
class Producer extends Thread {
    private SharedBuffer buffer;
    private int counter = 0;
    
    public Producer(SharedBuffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Producir un elemento
                buffer.produce(counter++);
                
                // Simular tiempo de producción
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Clase Consumidor que procesa elementos
 */
class Consumer extends Thread {
    private SharedBuffer buffer;
    
    public Consumer(SharedBuffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Consumir un elemento
                int item = buffer.consume();
                
                // Simular tiempo de procesamiento
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}
