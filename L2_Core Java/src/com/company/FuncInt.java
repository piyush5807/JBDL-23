package com.company;

@FunctionalInterface
public interface FuncInt<T, R> {

    R add (T a, T b);

    boolean equals(Object obj);

    default int subtract(int a, int b){
        return a - b;
    }

    default int multiply(int a, int b){
        return a * b;
    }
}
