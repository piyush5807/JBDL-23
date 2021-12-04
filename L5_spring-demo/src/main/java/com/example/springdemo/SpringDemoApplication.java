package com.example.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class SpringDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);

		logger.error("This is an error log");
		logger.warn("This is a warn log");
		logger.info("This is an info log");
		logger.debug("This is a debug log");
		logger.trace("This is a trace log");


		MyThread thread = new MyThread();
		thread.start();

	}

	private static class MyThread extends Thread{
		@Override
		public void run() {
			logger.info("This is an info log in my thread");
		}
	}

	// main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1069 ms

}
