package com.example.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class DemoController{

    @Autowired
    DemoService demoService; // Field injection


    @Autowired
    DemoConfig demoConfig;

//    private DemoService demoService;
//
////    @Autowired
//    DemoController(DemoService demoService){
//        this.demoService = demoService;
//    }

    @GetMapping("/")
    public String demoFunc(){
        demoService.testFunc();
//        DemoService ds = new DemoService();
//        System.out.println(ds.getDemo());
        System.out.println(this.demoService);
        List<Integer> al = demoConfig.getMapper();
        al.add(10);
        System.out.println(al);
//        System.out.println(demoService.getDemo().getName()); // null or NPE
        return String.valueOf(new Random().nextInt());
    }
    // Arraylist@5078

    @GetMapping("/demo")
    public String demoFunc2(){
        System.out.println(this.demoService);
        return String.valueOf(new Random().nextInt());
    }

    // com.example.demobeans.DemoService@41d945ae

    // com.example.demobeans.DemoService@41d945ae
}
