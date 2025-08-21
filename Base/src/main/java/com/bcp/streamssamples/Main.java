package com.bcp.streamssamples;

import java.util.function.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // ❌ MALO - Auto-boxing/Unboxing
        Stream.of(1, 2, 3, 4)
                .map(x -> x * 2)        // int -> Integer (boxing)
                .filter(x -> x > 5)     // Integer -> int (unboxing)
                .forEach(x -> System.out.println(x));


        ToIntFunction<String> tif = s -> s.length();  // String -> int
        ToDoubleFunction<Integer> tdf = x -> x * 1.5; // Integer -> double


        IntPredicate p = x -> x > 10;           // int -> boolean
        IntConsumer c = x -> System.out.println(x); // int -> void
        IntFunction<String> f = x -> "Número: " + x; // int -> String

        IntUnaryOperator iuo = x -> x * 2;       // int -> int
        IntToDoubleFunction idf = x -> x * 1.5;   // int -> double

        IntSupplier is = () -> 42;               // () -> int
        DoubleSupplier ds = () -> Math.random(); // () -> double

        //ANTES
        Stream.of("ONE","TWO","THREE","FOUR")
                .map(s -> s.length())           // String -> Integer (boxing)
                .peek(i -> System.out.println(i)) // Integer -> void
                .filter(i -> i > 3)             // Integer -> boolean (unboxing)
                .reduce(0, Integer::sum);       // Integer (boxing/unboxing)


        Stream.of("ONE","TWO","THREE","FOUR")
                .mapToInt(s -> s.length())      // String -> int (directo)
                .peek(i -> System.out.println(i)) // int -> void (directo)
                .filter(i -> i > 3)             // int -> boolean (directo)
                .sum();                         // int (directo)




    }
}
