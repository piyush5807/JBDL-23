package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import com.example.jbdl.minorproject1.repository.StudentRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.sun.corba.se.impl.protocol.RequestCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    RequestService requestService;

    public void createStudent(StudentCreateRequest studentRequest){
        studentRepository.save(studentRequest.to());
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }


    public String placeRequest(PlaceRequest placeRequest){
        List<Admin> admins = adminService.getAdmins();

        Admin admin = admins.stream()
                .min(Comparator.comparingInt(a -> a.getRequestsToProcess().size()))
                .orElse(null);

        return requestService.saveOrUpdateRequest(placeRequest.toRequest(admin)).getRequestId();
    }


}
