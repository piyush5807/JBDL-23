package com.company;

import java.util.ArrayList;

public class FuncInterfaces {

    public static void main(String[] args) {

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("I am in the run function");
//            }
//        };
//
//        // lambda
//        Runnable runnable2 = () -> System.out.println("I am in lambda of the run function");
//
//
//        runnable.run();
//        runnable2.run();

//        FuncInt funcInt1 = new FuncInt() {
//            @Override
//            public int add(int a, int b) {
//                System.out.println("Got the arguments as " + a + " " + b);
//
//                // 10 -15
//                return a + b;
//            }
//
//            @Override
//            public int divide(int a, int b, int c) {
//                return 0;
//            }
//
//        };

        FuncInt<String, String> funcInt = (c, d) -> {
            System.out.println("Got the arguments as " + c + " " + d);
            return c + d;
        };

        System.out.println(funcInt.add("6", "7"));
        System.out.println(funcInt.subtract(6, 3));
        System.out.println(funcInt.equals(3));
    }
}
