package com.example.jbdl.demoredis;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @NotNull
    private Long id;

    private String name;
    private Double height;
    private Integer weight;
    private Boolean seniorCitizen;
}
