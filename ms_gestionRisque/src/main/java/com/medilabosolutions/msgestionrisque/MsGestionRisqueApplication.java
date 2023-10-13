package com.medilabosolutions.msgestionrisque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionRisqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionRisqueApplication.class, args);
	}

}
