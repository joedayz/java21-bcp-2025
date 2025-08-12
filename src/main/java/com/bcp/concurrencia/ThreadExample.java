package com.bcp.concurrencia;

public class ThreadExample {

    public static void main(String[] args) {

        Runnable r = () -> {
            /* run method implementing thread logic */
            System.out.println("Ejecutando hilo: " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        boolean x  = t2.isAlive();

        System.out.println("¿t2 está vivo?: " + x);

        Thread.State phase = t1.getState();
        System.out.println("Estado de t1: " + phase);

        //t1.start();

    }
}
