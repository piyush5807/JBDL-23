package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request getRequestById(Integer id){
        return requestRepository.findById(id).orElse(null);
    }

    public Request saveOrUpdateRequest(Request request){
        return requestRepository.save(request);
    }
}
