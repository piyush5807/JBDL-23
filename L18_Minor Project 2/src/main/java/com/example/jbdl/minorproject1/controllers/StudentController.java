package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.User;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    // Not authenticated
    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentRequest){
        studentService.createStudent(studentRequest);
    }

    // Admin
    @GetMapping("/studentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int id){
        return studentService.getStudentById(id);
    }

    // Student
    @GetMapping("/student")
    public Student getStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        int studentId = user.getStudent().getId();

        return studentService.getStudentById(studentId);
    }

    // Student
    @PostMapping("/student/request")
    public String placeRequest(@Valid @RequestBody PlaceRequest placeRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        int studentId = user.getStudent().getId();

        return studentService.placeRequest(placeRequest, studentId);
    }
}
