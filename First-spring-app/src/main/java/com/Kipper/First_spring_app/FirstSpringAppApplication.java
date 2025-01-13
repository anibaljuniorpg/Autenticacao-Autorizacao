package com.Kipper.First_spring_app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

//Classe principal
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
@ComponentScan(basePackages = {"controller", "com/Kipper/First_spring_app/domain/user/controller/service", "domain", "com.Kipper.First_spring_app.configuraton"})
@Profile("dev")
public class FirstSpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringAppApplication.class, args);
	}

}
