package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.RequestStatus;
import com.example.jbdl.minorproject1.models.User;
import com.example.jbdl.minorproject1.repository.AdminRepository;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.responses.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

  private static final String REJECTED_STATUS = "Rejected";
  private static final String APPROVED_STATUS = "Approved";

  @Value("${ADMIN_AUTHORITY}")
  private String ADMIN_AUTHORITY;

  @Value("${BOOK_INFO_AUTHORITY}")
  private String BOOK_INFO_AUTHORITY;

  @Value("${STUDENT_INFO_AUTHORITY}")
  private String STUDENT_INFO_AUTHORITY;

  @Value("${authorities.delimiter}")
  private String delimiter;

  @Autowired AdminRepository adminRepository;

  @Autowired RequestService requestService;

  @Autowired TransactionService transactionService;

  @Autowired UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void createAdmin(AdminCreateRequest adminCreateRequest) {

    User userFromRequest = adminCreateRequest.toUser();
    attachAuthorities(userFromRequest);
    encodePassword(userFromRequest);
    User savedUser = userService.saveUser(userFromRequest);
    adminRepository.save(adminCreateRequest.to(savedUser));
  }

  public Admin getAdmin(Integer id) {
    return adminRepository.findById(id).orElse(null);
  }

  public List<Admin> getAdmins() {
    return adminRepository.findAll();
  }

  public ProcessResponse processRequest(ProcessRequest processRequest, Integer adminId) throws Exception {

    Request request = requestService.getRequestById(processRequest.getRequestId());
    if (request == null) {
      throw new Exception("Request does not exist");
    }

    if (request.getAdmin() == null || request.getAdmin().getId() != adminId) {
      throw new Exception("Request is not assigned to this admin " + adminId);
    }

    if (!RequestStatus.PENDING.equals(request.getRequestStatus())) {
      throw new Exception("Request is already processed");
    }

    // Admin approval

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

    request.setRequestStatus(RequestStatus.ACCEPTED);
    request.setAdminComment(processRequest.getComment());
    request.setSystemComment(APPROVED_STATUS);
    Request savedRequest = requestService.saveOrUpdateRequest(request);
    transactionService.createTransaction(savedRequest);
    return ProcessResponse.builder()
        .systemComment(APPROVED_STATUS)
        .requestStatus(RequestStatus.ACCEPTED)
        .adminComment(processRequest.getComment())
        .build();
  }

  private void attachAuthorities(User user){

    String authorities = BOOK_INFO_AUTHORITY + delimiter +
            ADMIN_AUTHORITY + delimiter +
            STUDENT_INFO_AUTHORITY;

    user.setAuthorities(authorities);
  }

  private void encodePassword(User user){
    String rawPwd = user.getPassword();
    String encodedPwd = passwordEncoder.encode(rawPwd);
    user.setPassword(encodedPwd);
  }
}
