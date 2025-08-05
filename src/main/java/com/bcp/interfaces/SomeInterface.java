package com.bcp.interfaces;

public interface SomeInterface {

    int SOME_CONSTANT = 10;
    void someMethod();

    public default void someDefaultMethod() {
        System.out.println("Some default method");
    }

    private static void someStaticMethod() {
        System.out.println("Some static method");
    }

    private void somePrivateMethod() {
    }

    public static void somePublicStaticMethod() {
        System.out.println("Some public static method");
    }
}
