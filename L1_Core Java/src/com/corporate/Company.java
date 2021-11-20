package com.corporate;

public class Company extends Corporate{

    private String name; // company name

    public Company(String companyName, String corporate){
        super(corporate);
        this.name = companyName;
    }

    public void func(){
        System.out.println("In func of Company class");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateName(){
        return super.getName();
    }

    public void func2(){
        System.out.println("I am in func2 of Company class");
    }

    public static void main(String[] args) {

        Company company = new Company("Zeta", "Directi");

        System.out.println("Company name = " + company.getName());
        System.out.println("Company name = " + company.getCorporateName());

    }
}
