package com.bcp.comparingstrings;

public class Main {


    public static void main(String[] args) {
        String a = "Hello";

        String b = "Hello";

        String c = new String("Hello");

        System.out.println(a == b);  //Pool de strings para literales

        System.out.println(a.equals(b)); //este es el recomendado para ver si 2 strings son iguales

        System.out.println(a == c);

        String d ="HELlo";

        System.out.println(a  == d);

        System.out.println(a.equalsIgnoreCase(d));
    }
}
