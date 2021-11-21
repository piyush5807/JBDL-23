package com.company;

public class Person {

    private int id;
    private String name;
    private int age;

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

    private static Person person;

    private Person(){
        System.out.println("In the constructor");
    }

    public static Person getPerson(){
        if(person == null){
            person = new Person();
        }

        return person;
    }

}
