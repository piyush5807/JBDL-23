package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request placeRequest(PlaceRequest placeRequest){
        // TODO: We need to assign an admin who will process the request

        /*
            1. A1 -> 5
            2. A2 -> 4
         */
        return requestRepository.save(placeRequest.toRequest());
    }

    // Student 1 is placing an issue request for book 40

}
