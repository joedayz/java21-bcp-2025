package com.acme;

import com.bcp.X;

public class Y extends X {

    public static void main(String[] args) {
        var y = new Y();
        //System.out.println(y.m3);
        y.m4 = "Hello World!";
        System.out.println(y.m2);

//        var x = new X();
//        System.out.println(x.m2);
    }
}
