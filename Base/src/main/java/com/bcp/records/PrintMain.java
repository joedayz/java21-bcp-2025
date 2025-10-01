package com.bcp.records;

public class PrintMain {

    static void printSum(Object obj){
        if(obj instanceof Point(var x, var y)){
            System.out.println("Suma = " + (x+y));
        }
    }


    static String formatShape(Object shape){
        return switch (shape){
            case Point(int x, int y) when x == y -> "Punto en diagonal";
            case Point(int x, int y) -> "Punto con suma " + (x+y);
            case null -> "Es null";
            default -> "Otro tipo";
        };
    }

    public static void main(String[] args) {
        printSum(new Point(1,2));
    }
}
