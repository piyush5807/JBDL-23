package com.company;

public interface MathOperations2 {

    int add(int a, int b);

    int addParent();

    default double power(int a, int b){
        return Math.pow(a, b) + 1;
    }

    default void testFunc(){
        System.out.println("I am in testFunc of MathOperations2");
    }
}
