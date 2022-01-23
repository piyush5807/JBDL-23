package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.User;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    // Admin
    @PostMapping("/admin")
    public void createAdmin(@RequestBody AdminCreateRequest adminCreateRequest){
        adminService.createAdmin(adminCreateRequest);
    }

    // Admin - Give the details of the person who is requesting
    @GetMapping("/admin")
    public Admin getAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        int adminId = user.getAdmin().getId();

        return adminService.getAdmin(adminId);
    }

    // Admin
    @PostMapping("/admin/process")
    public void processRequest(@RequestBody ProcessRequest processRequest) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        int adminId = user.getAdmin().getId();

        adminService.processRequest(processRequest, adminId);
    }
}
