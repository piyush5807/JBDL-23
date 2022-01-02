package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    AdminService adminService;

    public Request placeRequest(PlaceRequest placeRequest){
        // TODO: We need to assign an admin who will process the request

        List<Admin> admins = adminService.getAdmins();

        Admin admin = admins.stream()
                .min(Comparator.comparingInt(a -> a.getRequestsToProcess().size()))
                .orElse(null);

        return saveOrUpdateRequest(placeRequest.toRequest(admin));
    }

    public Request getRequestById(Integer id){
        return requestRepository.findById(id).orElse(null);
    }

    public Request saveOrUpdateRequest(Request request){
        return requestRepository.save(request);
    }

    // Student 1 is placing an issue request for book 40

}
