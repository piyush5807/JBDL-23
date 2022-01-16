package com.example.jbdl.demoredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootApplication
public class DemoRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRedisApplication.class, args);

		Class<Person> clss = Person.class;
		Field [] fields = clss.getDeclaredFields();

		Arrays.stream(fields)
				.forEach(x -> System.out.println(x.getName()));

		Class<DemoController> demoControllerClass = DemoController.class;

		Method[] methods = demoControllerClass.getDeclaredMethods();
		Arrays.stream(methods).forEach(x -> System.out.println(x.getName()));
	}

}
