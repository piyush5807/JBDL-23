package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {

    // 2 functions in a class can be of same name but 2 functions in  a class cannot have the same number and type of arguments

    public void xyz(String abc){
        System.out.println("In function xyz");
    }

    public void xyz(String str, int a){
        System.out.println("In function xyz, str = " + str);
    }

    public Main(String a){
        System.out.println("In the constructor, a = " + a);
    }


    public static void main(String[] args) throws Exception {

        Main main = new Main("");
        main.xyz("John");

        Person person = new Person();
        person.setAge(2);
        person.setAge(-5);

        System.out.println(person.getAge());

//        ArrayList<String> cities = new ArrayList<>();
//        cities.add("Delhi");
//        cities.add("Mumbai");
//        cities.add("Kolkata");
//
//        System.out.println(cities);
//
//        int a = 6;
//        int b = 5;
//        int c = 7;
//        System.out.println(a + b * c - a / b * c - a + b);

    }
}
