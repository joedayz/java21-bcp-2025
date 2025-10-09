package com.bcp.comparabledemo;

public class MainCloneable {

    public static void main(String[] args) {
        try{
            Product p1 = new Product("Tea");
            Product p2 = (Product) p1.clone();

            System.out.println(p1);
            System.out.println(p2);

            System.out.println("Son el mismo objeto?:" + (p1==p2));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
