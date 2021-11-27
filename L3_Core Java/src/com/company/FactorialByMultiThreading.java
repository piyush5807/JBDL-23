package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FactorialByMultiThreading  {

    public static void main(String[] args) {
        int[] numbers = {15000, 52000, 80000, 60000, 70000, 6000, 80000, 24000, 40000, 300, 400, 5000, 6000};

        FactorialThread[] threads = new FactorialThread[numbers.length];

//        for(int i = 0; i < numbers.length; i++){
//            threads[i] = new FactorialThread(numbers[i]);
//        }

        long start = System.currentTimeMillis();

        IntStream.range(0, numbers.length).forEach(i -> {
            threads[i] = new FactorialThread(numbers[i]);
            threads[i].start();
        });

        Arrays.stream(threads).forEach(factorialThread -> {
            try {
                factorialThread.join(); // Blocking call, wait until threads completes / dies
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        IntStream.range(0, numbers.length)
                .forEach(i -> System.out.println("For number - " + threads[i].num +
                ", ans is " + threads[i].result));

        System.out.println("time taken = " + (System.currentTimeMillis() - start));

    }

    private static class FactorialThread extends Thread{

        private int num;
        private BigInteger result;

        public FactorialThread(int num) {
            this.num = num;
            this.result = BigInteger.ONE;
        }

        @Override
        public void run() {
            getFactorial();
        }

        public void getFactorial(){
            System.out.println("num = " + this.num + ", current thread - "
                    + Thread.currentThread().getName());

            for(int i = 2; i <= this.num; i++){
                this.result = this.result.multiply(BigInteger.valueOf(i));
            }
        }
    }
}
