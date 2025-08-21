package com.bcp.virtualthreads;

import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000_000; i++) {
                executor.submit(() -> {
                    Thread.sleep(1000); // Simula I/O
                    return "ok";
                });
            }
        }
    }
}
