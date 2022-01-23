package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public Admin to(){
        return to(null);
    }

    public Admin to(User user){
        return Admin.builder()
                .name(this.getName())
                .email(this.getEmail())
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
