package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Min(1)
    private int age;

    @NotNull
    private String rollNo;

    public Student to(){
        return to(null);
    }

    public Student to(User user){
        return Student.builder()
                .age(this.getAge())
                .name(this.getName())
                .rollNo(this.getRollNo())
                .user(user)
                .build();
    }


    public User toUser(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
