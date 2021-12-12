package com.example.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class DemoController2{

//    @Autowired
//    DemoConfig demoConfig;

    @Autowired
    ArrayList<Integer> al;

    // com.example.demobeans.DemoService@36611fc9
    // com.example.demobeans.DemoService@5c2c8492

    @Autowired
    DemoService demoService;

    @GetMapping("/demo2")
    public String demoFunc(){
//        System.out.println(this.demoService);
//        System.out.println(demoService.getDemo().getName()); // null or NPE
//        List<Integer> al = demoConfig.getMapper();
        al.add(10);
        System.out.println(al);
        return String.valueOf(new Random().nextInt());
    }
}