package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.RequestStatus;
import com.example.jbdl.minorproject1.repository.AdminRepository;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.responses.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class AdminService {

    private static final String REJECTED_STATUS = "Rejected";
    private static final String APPROVED_STATUS = "Approved";

    @Autowired AdminRepository adminRepository;

  @Autowired RequestService requestService;

  @Autowired
  TransactionService transactionService;

  public void createAdmin(AdminCreateRequest adminCreateRequest) {
    adminRepository.save(adminCreateRequest.to());
  }

  public Admin getAdmin(Integer id) {
    return adminRepository.findById(id).orElse(null);
  }

  public List<Admin> getAdmins() {
    return adminRepository.findAll();
  }

  public ProcessResponse processRequest(ProcessRequest processRequest) throws Exception {

    Request request = requestService.getRequestById(processRequest.getRequestId());
    if (request == null) {
      throw new Exception("Request does not exist");
    }

    if (request.getAdmin() == null || request.getAdmin().getId() != processRequest.getAdminId()) {
      throw new Exception("Request is not assigned to this admin " + processRequest.getAdminId());
    }

    if (!RequestStatus.PENDING.equals(request.getRequestStatus())) {
      throw new Exception("Request is already processed");
    }

    // L1 - Admin approval, L2 - System approval

    if (RequestStatus.REJECTED.equals(processRequest.getRequestStatus())) {
      request.setRequestStatus(RequestStatus.REJECTED);
      request.setAdminComment(processRequest.getComment());
      request.setSystemComment(REJECTED_STATUS);
      requestService.saveOrUpdateRequest(request);
      return ProcessResponse.builder()
          .systemComment(REJECTED_STATUS)
              .requestStatus(RequestStatus.REJECTED)
          .adminComment(processRequest.getComment())
          .build();
    }

    switch (request.getRequestType()) {
      case ISSUE:
        // TODO: 1. Book exists and not issued, 2. Check for student's threshold 3. APPROVE
        // STATUS AND CREATE TRANSACTION
          return ProcessResponse.builder().build();
      case RETURN:
          request.setRequestStatus(RequestStatus.ACCEPTED);
          request.setAdminComment(processRequest.getComment());
          request.setSystemComment(APPROVED_STATUS);
          requestService.saveOrUpdateRequest(request);
//          transactionService.createTransaction();
          return ProcessResponse.builder()
                  .systemComment(APPROVED_STATUS)
                  .requestStatus(RequestStatus.ACCEPTED)
                  .adminComment(processRequest.getComment())
                  .build();
    }

    return null;
  }
}
