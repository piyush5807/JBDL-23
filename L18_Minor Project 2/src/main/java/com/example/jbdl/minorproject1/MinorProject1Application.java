package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner {

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		AdminCreateRequest adminCreateRequest = AdminCreateRequest.builder()
//				.name("peter")
//				.email("peter@gmail.com")
//				.username("peter4509")
//				.password("peter@123pwd")
//				.build();
//		adminService.createAdmin(adminCreateRequest);
	}
}
