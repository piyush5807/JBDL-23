package com.example.jbdl.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Value("${ADMIN_AUTHORITY}")
	private String ADMIN_AUTHORITY;

	@Value("${STUDENT_ATTENDANCE_AUTHORITY}")
	private String STUDENT_ATTENDANCE_AUTHORITY;

	@Value("${STUDENT_ONLY_AUTHORITY}")
	private String STUDENT_ONLY_AUTHORITY;

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurityApplication.class, args);
	}

	@Override
	public void run(String...args){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		MyUser myUser = MyUser.builder()
				.user_name("dheeraj")
				.password(bCryptPasswordEncoder.encode("dheeraj1234"))
				.authorities(ADMIN_AUTHORITY + ":" + STUDENT_ATTENDANCE_AUTHORITY)
				.build();

		userRepository.save(myUser);
	}
}
