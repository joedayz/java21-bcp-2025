package com.bcp;

import com.bcp.accessmodifiers.A;

public class ChildA extends A {

    public static void main(String[] args) {
        ChildA childA = new ChildA();
        System.out.println(childA.status);
        childA.dummy();

        //MALISIMO

//        A a = new A();
//        a.dummy();
    }
}
