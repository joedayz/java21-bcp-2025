package com.bcp.accessmodifiers;

public class B {


    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.price);

        System.out.println(a.status);

        a.dummy();
    }


}
