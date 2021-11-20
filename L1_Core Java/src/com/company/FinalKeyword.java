package com.company;

public class FinalKeyword {

    /** Final
    /* 1. variables - variables can be defined only once
    /* 2. Functions - final functions cannot be overridden in the child class
    /* 3. classes - final classes cannot be inherited or you can a final class
     is nothing a class with all the methods as final
   **/

    /** Static keyword
     * Non static variables are not accessible in a static context
     * but vice versa is not true
     */

    /** This keyword
     * Refers to the current object, accessible only inside non static context
     */

    private final int a; // instance variable or non static variable

    private static int b;
    private int c;

    public FinalKeyword() {
        a = 6;
    }

    public void func(){
        b = 10;
        System.out.println(this.b);
    }

    public static void main(String[] args) {
        FinalKeyword obj1 = new FinalKeyword();
        obj1.b = 10;
        obj1.c = 30;

        FinalKeyword obj2 = new FinalKeyword();
        obj2.b = 20;

        System.out.println(obj1.b + " " + obj2.b + " " + FinalKeyword.b);

        obj1.func();
        obj2.func();
    }
}
