package com.medilabosolutions.msgestionpatient.controller;


import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/update")
    Patient getUpdatePage(@RequestParam("patientId") String patientId){

        log.debug("getUpdatePage() called with: {}", patientId);
        return patientService.findPatientById(patientId);
    }

    @PutMapping ("/update")
    void updatePatient(@RequestBody PatientBean patientBean){

        log.debug("updatePatient() called with: {}", patientBean);
        patientService.updatePatient(patientBean);
    }

    @PostMapping ("/add")
    void addPatient(@RequestBody PatientBean patientBean){

        log.debug("addPatient() called with: {}", patientBean);
        patientService.addPatient(patientBean);
    }


}
