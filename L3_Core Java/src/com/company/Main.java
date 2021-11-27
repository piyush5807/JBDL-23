package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    // 1. Correctness
    // 2. Optimization / performance

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        int[] numbers = {15000, 52000, 80000, 60000, 70000, 6000, 80000, 24000, 40000, 300, 400, 5000, 6000};
        // Q1. You need to print the factorial of all these numbers


        long start = System.currentTimeMillis();
        Arrays.stream(numbers)
                .mapToObj(num -> getFactorial(num))
//                .collect(Collectors.toList());
                .forEach(num -> System.out.println(num));

        System.out.println("Time taken is " + (System.currentTimeMillis() - start));

//        for(int i = 0; i < numbers.length; i++){
//            System.out.println(getFactorial(numbers[i]));
//        }
    }

    public static BigInteger getFactorial(Integer num){
        System.out.println("num = " + num + ", current thread - " + Thread.currentThread().getName());
        BigInteger result = BigInteger.ONE;

        for(int i = 2; i <= num; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
