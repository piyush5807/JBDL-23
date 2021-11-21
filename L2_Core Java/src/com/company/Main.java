package com.company;

public class Main {

    public static void main(String[] args) {

        Person p1 = Person.getPerson();
        Person p2 = Person.getPerson();

        Person p3 = Person.getPerson();
        Person p4 = Person.getPerson();

        p1.setAge(20);

        System.out.println(p2.getAge());
    }
}
