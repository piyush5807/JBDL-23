package com.example.demobeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
//@Scope("prototype")
public class DemoConfig {

    @Bean // @Component
    @Scope("singleton") // --> prototype
    @Primary
    public ArrayList<Integer> getMapper(){
        System.out.println("Creating an instance of arraylist");
        return new ArrayList<>();
    }

    @Bean // @Component
    @Scope("singleton") // --> prototype
    public ArrayList<Integer> getMapper1(){
        System.out.println("Creating an instance of arraylist");
        return new ArrayList<>();
    }
}
