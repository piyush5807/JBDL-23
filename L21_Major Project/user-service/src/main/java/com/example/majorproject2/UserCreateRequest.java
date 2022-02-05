package com.example.majorproject2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    private String phone;

    @NotNull
    private String country;

    @NotNull
    private String nationalID;

    private String password;

    public User to(){
        return User.builder()
                .country(country)
                .name(name)
                .nationalID(nationalID)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
    }
}
