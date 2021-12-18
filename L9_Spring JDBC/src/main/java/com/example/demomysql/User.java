package com.example.demomysql;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User { // Entity


    private int id; // primary key
    private String firstName; // Raj
    private String lastName; // Mehta
    private int age; // 45
    private boolean isSeniorCitizen; // False
    private String email; // raj@gmail.com
    // 20 params
}
