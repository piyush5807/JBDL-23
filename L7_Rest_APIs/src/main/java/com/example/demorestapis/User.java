package com.example.demorestapis;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Builder
public class User {

    private Integer id;
    private String name;
    private Integer age;
}

