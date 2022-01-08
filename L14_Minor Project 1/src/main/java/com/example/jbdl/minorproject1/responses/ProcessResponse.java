package com.example.jbdl.minorproject1.responses;

import com.example.jbdl.minorproject1.models.RequestStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessResponse {

    private RequestStatus requestStatus;
    private String systemComment;
    private String adminComment;
}
