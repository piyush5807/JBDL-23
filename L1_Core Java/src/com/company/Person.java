package com.company;

public class Person {

    private String firstName; // Accessible only in the current class
    String lastName; // default - accessible only inside the same package
    protected int age; // accessible inside the current package and in child class of diff packages
    public boolean isSeniorCitizen; // everywhere


    public static void main(String[] args) {

        Person person = new Person();

        person.firstName = "Robert";
    }

    public void xyz(){

        // doing some logic on age
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    // This function will ensure that nobody sets the age as negative
    public void setAge(int age) throws Exception {
        if(age < 0){
            throw new Exception("Age cannot be negative");
        }
        this.age = age;
    }

    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    public void setSeniorCitizen(boolean seniorCitizen) {
        isSeniorCitizen = seniorCitizen;
    }
}
