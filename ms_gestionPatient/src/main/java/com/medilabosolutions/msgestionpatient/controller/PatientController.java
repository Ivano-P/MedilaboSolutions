package com.medilabosolutions.msgestionpatient.controller;


import com.medilabosolutions.msgestionpatient.dto.PatientDTO;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/getAllPatients")
    public List<PatientDTO> listOfAllPatients (){
        return patientService.convertPatientsToPatientsDTO(patientService.findAllPatients());
    }

}
