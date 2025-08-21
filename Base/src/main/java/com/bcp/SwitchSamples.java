package com.bcp;

public class SwitchSamples {

    public static void main(String[] args) {
        var x = 1;
        switch (x){
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
        }

        var result = switch (x){
            case 1 -> "1";
            case 2 -> {
                System.out.println("2");
                var yield = 2;
                yield yield;
            }
            default -> "default";
        };
        System.out.println(result);

        System.out.println(8 * 8 /2 + 2 - 3 * 2);
    }
}
