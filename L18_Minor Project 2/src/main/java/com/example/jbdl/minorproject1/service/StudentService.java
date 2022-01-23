package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.User;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import com.example.jbdl.minorproject1.repository.StudentCacheRepository;
import com.example.jbdl.minorproject1.repository.StudentRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.sun.corba.se.impl.protocol.RequestCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    private final String BOOK_INFO_AUTHORITY;
    private final String STUDENT_ONLY_AUTHORITY;
    private final String delimiter;
    private final StudentRepository studentRepository;
    private final AdminService adminService;
    private final RequestService requestService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final StudentCacheRepository studentCacheRepository;

    public StudentService(StudentRepository studentRepository, AdminService adminService,
                          RequestService requestService, UserService userService,
                          @Value("${BOOK_INFO_AUTHORITY}") String BOOK_INFO_AUTHORITY,
                          @Value("${STUDENT_ONLY_AUTHORITY}") String STUDENT_ONLY_AUTHORITY,
                          @Value("${authorities.delimiter}") String delimiter,
                          PasswordEncoder passwordEncoder,
                          StudentCacheRepository studentCacheRepository
                          ){

        this.studentRepository = studentRepository;
        this.adminService = adminService;
        this.requestService = requestService;
        this.userService = userService;
        this.delimiter = delimiter;
        this.BOOK_INFO_AUTHORITY = BOOK_INFO_AUTHORITY;
        this.STUDENT_ONLY_AUTHORITY = STUDENT_ONLY_AUTHORITY;
        this.passwordEncoder = passwordEncoder;
        this.studentCacheRepository = studentCacheRepository;

    }

    public void createStudent(StudentCreateRequest studentRequest){

        User userFromRequest = studentRequest.toUser();
        attachAuthorities(userFromRequest);
        encodePassword(userFromRequest);

        User savedUser = userService.saveUser(userFromRequest);

        Student student = studentRequest.to(savedUser);

        // Very imp to retrieve the user, otherwise the student id will be null
        student = studentRepository.save(student);

        try{
            studentCacheRepository.saveStudent(student);
        }catch (Exception e){
            // Ignore it
        }
    }

    public Student getStudentById(int id){
        /*
            1. Search in the cache, if found return from here itself, otherwise move to next steps
            2. Get from db and save it in the cache
            3. Return the retrieved object
         */

        Student student = studentCacheRepository.getStudent(id);
        if(student == null){
            student = studentRepository.findById(id).orElse(null);

            if(student != null){
                try{
                    studentCacheRepository.saveStudent(student);
                }catch (Exception e){
                    // ignore
                }
            }
        }

        return student;
    }


    public String placeRequest(PlaceRequest placeRequest, Integer studentId){
        List<Admin> admins = adminService.getAdmins();

        Admin admin = admins.stream()
                .min(Comparator.comparingInt(a -> a.getRequestsToProcess().size()))
                .orElse(null);

        return requestService.saveOrUpdateRequest(placeRequest.toRequest(admin, studentId)).getRequestId();
    }

    private void attachAuthorities(User user){
        String authorities = STUDENT_ONLY_AUTHORITY + delimiter + BOOK_INFO_AUTHORITY;
        user.setAuthorities(authorities);
    }

    private void encodePassword(User user){
        String rawPwd = user.getPassword();
        String encodedPwd = passwordEncoder.encode(rawPwd);
        user.setPassword(encodedPwd);
    }

}
