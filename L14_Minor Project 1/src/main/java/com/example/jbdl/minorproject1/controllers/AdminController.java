package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody AdminCreateRequest adminCreateRequest){
        adminService.createAdmin(adminCreateRequest);
    }

    @GetMapping("/admin/{adminId}")
    public Admin getAdmin(@PathVariable("adminId") int id){
        return adminService.getAdmin(id);
    }

    @PostMapping("/admin/process")
    public void processRequest(@RequestBody ProcessRequest processRequest) throws Exception {
        adminService.processRequest(processRequest);
    }
}
