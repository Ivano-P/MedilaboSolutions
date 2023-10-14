package com.medilabosolutions.msfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Entry point for the MsFrontEndApplication.
 * This application is configured to be a Spring Boot application,
 * with enabled discovery client for service registration and discovery,
 * and also enabled Feign clients for declarative web service clients.
 *
 * @author Ivano
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsFrontEndApplication {

	/**
	 * Main method used to run the Spring Boot application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsFrontEndApplication.class, args);
	}

}
