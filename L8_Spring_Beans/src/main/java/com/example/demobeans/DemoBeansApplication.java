package com.example.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBeansApplication {

	@Autowired
	DemoController demoController;

	public static void main(String[] args) {
		SpringApplication.run(DemoBeansApplication.class, args);
	}


}
