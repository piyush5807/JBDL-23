package com.example.demobeans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Component
@Scope("singleton")
public class DemoService {


    // demoController - com.example.demobeans.DemoService@5e671e20
    // demoController2 - com.example.demobeans.DemoService@1de0a46c
    // com.example.demobeans.DemoService@1de0a46c

    /*
     * Whenever you are defining a parameterized constructor of a bean,
     * you always need to pass bean(s) in the constructor arguments
    */

    private String prop;

    @Autowired
    public DemoService(ObjA a,
                       ObjB b,
                       @Value("${my_app.test_prop}") String prop) {
        this.prop = prop;
        System.out.println("Creating an instance of demoService...." + a + " "
                + b + " "
                + prop);
    }

    public void testFunc(){
        System.out.println(prop);
    }

    public DemoService() {
    }

    //    public DemoService(int a){
//        System.out.println("Creating an instance of demoService...." + a);
//    }
}
