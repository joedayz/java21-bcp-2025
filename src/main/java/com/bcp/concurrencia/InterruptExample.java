package com.bcp.concurrencia;

public class InterruptExample {

    public static void main(String[] args) {


        Runnable r = () -> {
            Thread ct = Thread.currentThread(); // localizar el objeto Thread actual

            while (!ct.isInterrupted()) { // verificar señal de interrupción mientras corre
                // Acciones del hilo
                System.out.println("Ejecutando: " + ct.getName());

                try {
                    Thread.sleep(1000); // entrar en estado TIMED_WAITING por 1 segundo
                } catch (InterruptedException ex) {
                    // Acciones cuando el hilo es interrumpido mientras espera
                    System.out.println("Hilo interrumpido durante la espera");
                    return; // salir del run() y terminar el hilo
                }
            }

        };

        Thread t = new Thread(r);
        t.start();

        try {
            Thread.sleep(3000); // dejar que el hilo trabaje un poco
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        t.interrupt(); //envia senal de interrupcion





    }
}
