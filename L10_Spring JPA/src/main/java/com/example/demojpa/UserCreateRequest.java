package com.example.demojpa;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @Min(value = 1)
    @Max(value = 100)
    private int age;

    @Size(max = 10)
    private String lastName;

    @NotBlank  // Size not 0 + Not null
    @Size(min = 1, max = 20)
    private String firstName;

    @Email
    private String email;

    private Boolean isSeniorCitizen;

    private boolean checkIfSeniorCitizen(){
        return this.age >= 60;
    }

    public User toUser(){
        return User.builder()
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .age(age)
                .isSeniorCitizen(isSeniorCitizen == null ? checkIfSeniorCitizen(): isSeniorCitizen)
                .build();
    }
}

