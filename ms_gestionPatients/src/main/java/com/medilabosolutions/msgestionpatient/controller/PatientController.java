package com.medilabosolutions.msgestionpatient.controller;


import com.medilabosolutions.msgestionpatient.dto.PatientDTO;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public List<PatientDTO> listOfAllPatients (){
        return patientService.convertPatientsToPatientsDTO(patientService.findAllPatients());
    }

}
