package com.corporate;

import com.company.Person;

public class CorporateMain extends Person{

    int corporateName;
    String corporateOwner;


//    @Override   // improves readability
    public String toString() {
        return "CorporateMain{" +
                "age=" + age +
                ", isSeniorCitizen=" + isSeniorCitizen +
                ", corporateName=" + corporateName +
                ", corporateOwner='" + corporateOwner + '\'' +
                '}';
    }

    public static void main(String[] args) {

        CorporateMain corporateMain = new CorporateMain();

//        int age  = corporateMain.calculateAge();

        Integer a = 5;

        System.out.println(a);
    }
}
