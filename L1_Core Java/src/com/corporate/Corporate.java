package com.corporate;

public class Corporate {

    private String name; // corporate name

    public Corporate(String name){
        this.name = name;
        System.out.println("I am in constructor of Corporate class");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void func(){
        System.out.println("I am in func of Corporate class");
    }

    public void finalFunction(){
        System.out.println("In final function of corporate");
    }

    public static void main(String[] args) {
        Corporate corporate = new Corporate("Accenture");
        corporate.getName();

    }
}
