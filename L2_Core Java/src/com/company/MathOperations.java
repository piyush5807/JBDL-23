package com.company;


public interface MathOperations{

    int var = 5; // In interfaces, all the variables are final and static

    int add(int a, int b); // In interfaces, all the methods are public and abstract automatically

    int subtract(int a, int b);

    int multiply(int a, int c);

    default int divide(int a, int b){
        return a / b;
    }

    default double power(int a, int b){
        return Math.pow(a, b);
    }

    default void testFunc() {
        System.out.println("I am in testFunc of MathOperations");
    }
}
