package com.company;

import java.util.HashMap;
import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static void main(String[] args) {

        HashMap<Person, Boolean> hashMap = new HashMap<>();
        Person person1 = new Person(1, "ABC", 10);
        Person person2 = new Person(1, "ABC", 10);
        Person person3 = new Person(2, "ABC", 15);

        System.out.println(person1 + " " + person2);// com.company.Person@____

        System.out.println(person1.hashCode() + " " + person2.hashCode() + " " +  person3.hashCode());
        System.out.println(person1.equals(person3));

        hashMap.put(person1, true);
        hashMap.put(person2, true);
        hashMap.put(person3, false);

        boolean ans = hashMap.get(person3);
        System.out.println(ans);

        System.out.println(hashMap);

    }
}
