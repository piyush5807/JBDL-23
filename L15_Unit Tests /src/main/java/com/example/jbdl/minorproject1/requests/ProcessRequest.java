package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.RequestStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequest {

    @NotNull
    private int adminId;

    @NotNull
    private int requestId;

    @NotNull
    private RequestStatus requestStatus;

    private String comment;

}
