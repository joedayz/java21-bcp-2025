package com.bcp.genericos;

public class Box<T> {

    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public static void main(String[] args) {
        Box<String> boxString = new Box<>();
        boxString.setContent("Hola Genéricos!");
        System.out.println("Contenido de la caja de String: " + boxString.getContent());

        // Caja para Integer
        Box<Integer> boxInt = new Box<>();
        boxInt.setContent(123);
        System.out.println("Contenido de la caja de Integer: " + boxInt.getContent());

        // Caja para Double
        Box<Double> boxDouble = new Box<>();
        boxDouble.setContent(45.67);
        System.out.println("Contenido de la caja de Double: " + boxDouble.getContent());
    }
}
