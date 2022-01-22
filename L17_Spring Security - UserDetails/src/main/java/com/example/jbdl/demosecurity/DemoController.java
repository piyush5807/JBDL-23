package com.example.jbdl.demosecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // This API should be invoked by only those people which USER authority
    @GetMapping("/student/hello")
    public String sayHelloToStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = (MyUser) authentication.getPrincipal();
        return "Hello Student:" + user.getUsername() + "!!!";
    }

    @GetMapping("/student/marksheet")
    public String viewMarkSheet(){
        return null;
    }

    // This API should be invoked by only those people which Admin authority
    @GetMapping("/admin/hello")
    public String sayHelloToAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = (MyUser) authentication.getPrincipal();
        return "Hello Admin:" + user.getUsername() + "!!!";
    }

    // // This API should be invoked by anyone without even logging in
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Guest!!!";
    }

    // Only this API can be invoked by student and admin both
    @GetMapping("/student/attendance")
    public Integer getAttendance(){
        return 0;
    }

    // Only by student
    @PostMapping("/student/attendance")
    public String submitAttendance(){
        return null;
    }

    // get the user details : username, pwd -> encode pwd save it in db for later retrieval user case
}
