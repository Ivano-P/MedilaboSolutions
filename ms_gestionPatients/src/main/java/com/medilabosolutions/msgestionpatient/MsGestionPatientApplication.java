package com.medilabosolutions.msgestionpatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionPatientApplication{

	public static void main(String[] args) {
		SpringApplication.run(MsGestionPatientApplication.class, args);
	}

}
