package com.medilabosolutions.msgestionpatient.controller;


import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RestController
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public List<PatientForSelectionDTO> listOfAllPatients(){
        log.debug("listOfAllPatients() called");

        return patientService.convertPatientsToPatientsDTO(patientService.findAllPatients());
    }

    @GetMapping("/information")
    Patient getPatientInfo(String prenom, String nom, LocalDate ddn){
        log.debug("getPatientInfo() called with {}, {}, {} " + prenom, nom, ddn);

        return patientService.findPatientInfo(prenom, nom, ddn);
    }


}
