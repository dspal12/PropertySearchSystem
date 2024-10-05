package com.dhruv.PropertySearchLogin_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PropertySearchWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertySearchWebappApplication.class, args);
	}

}
