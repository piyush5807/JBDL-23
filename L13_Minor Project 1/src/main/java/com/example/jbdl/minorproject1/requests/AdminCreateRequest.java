package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Admin;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateRequest {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public Admin to(){
        return Admin.builder()
                .name(this.getName())
                .email(this.getEmail())
                .build();
    }
}
