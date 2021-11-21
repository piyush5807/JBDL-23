package com.company;

public class MathOps implements MathOperations, MathOperations2{

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int addParent() {
        return 0;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public double power(int a, int b) {
        return MathOperations.super.power(a, b);
    }

    @Override
    public void testFunc() {
        MathOperations2.super.testFunc();
    }

    public static void main(String[] args) {
        MathOperations mathOperations = new MathOperations() {
            @Override
            public int add(int a, int b) {
                return a + b + 1;
            }

            @Override
            public int subtract(int a, int b) {
                return a - b + 1;
            }

            @Override
            public int multiply(int a, int b) {
                return a * b + 1;
            }

            @Override
            public int divide(int a, int b) {
                return a / b + 1;
            }
        };

        MathOperations mathOperations2 = new MathOps();

        mathOperations2.power(4, 3);

        System.out.println(mathOperations2.add(2, 3) + " " +
                mathOperations.add(2, 3));
    }

//    @Override
//    public void run() {
//
//    }
}
