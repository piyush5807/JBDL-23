package com.company;

import java.util.Arrays;
import java.util.List;

public class ParrallelStreams {

    public static void main(String[] args) {

        List<Integer> al = Arrays.asList(2, 31, 43, 57, 42, 6, 53 , 72, 1, 36, 56, 34, 23, 80, 50, 30);

        System.out.println(al.stream().parallel().filter(num -> {
            System.out.println("got number = " + num + ", thread = " + Thread.currentThread().getName());
            return num % 6 == 0;
        }).findFirst());
    }
}
