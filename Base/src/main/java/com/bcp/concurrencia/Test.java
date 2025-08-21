package com.bcp.concurrencia;

public class Test {

    public static void main(String[] args) {
        Runnable la = () -> {
            System.out.println("Acciones del hilo: " + Thread.currentThread().getName());
        };

        new Thread(la).start();
        new Thread(la).start();
        new Thread(la).start();
        new Thread(la).start();

        // Acciones del hilo principal
        System.out.println("Acciones del hilo principal");
    }

}


//class Lateral implements Runnable {
//    @Override
//    public void run() {
//        // Acciones de cada hilo
//        System.out.println("Acciones del hilo: " + Thread.currentThread().getName());
//    }
//}