package application;

import service.a.L;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<L> loader = ServiceLoader.load(L.class);
        L service = loader.findFirst().orElseThrow();
        service.doSomething();
    }
}