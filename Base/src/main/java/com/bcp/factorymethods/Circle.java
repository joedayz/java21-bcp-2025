package com.bcp.factorymethods;


sealed interface Shape permits Circle, Rectangle {}

public record Circle(double radius) implements Shape {
    static Circle of(double radius) {
        if(radius <=0) throw new IllegalArgumentException("Radio invalido");
        return new Circle(radius);
    }
}

record Rectangle(double width, double height) implements Shape {
    static Rectangle of(double width, double height) {
        if(width <= 0 || height <= 0) throw new IllegalArgumentException("Dimensiones invalidas");
        return new Rectangle(width, height);
    }
}

class FactoryDemo{
    public static void main(String[] args) {
        Shape c = Circle.of(5);
        Shape r = Rectangle.of(3, 4);
        System.out.println(c);
        System.out.println(r);
    }
}