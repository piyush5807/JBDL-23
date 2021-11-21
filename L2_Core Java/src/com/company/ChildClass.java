package com.company;

public class ChildClass extends AbstractClass{


    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
        ChildClass obj = new ChildClass();

        System.out.println(obj.add(2, 3));

    }
}
