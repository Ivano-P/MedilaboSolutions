package com.medilabosolutions.msgestionpatient;

import com.medilabosolutions.msgestionpatient.dto.PatientDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Log4j2
@SpringBootApplication
public class MsGestionPatientApplication implements CommandLineRunner {

	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(MsGestionPatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start run!");

		List<PatientDTO> allPatients = patientService.convertPatientsToPatientsDTO(patientService.findAllPatients());
		allPatients.forEach(log::info);

		log.info("End run!");
	}
}
