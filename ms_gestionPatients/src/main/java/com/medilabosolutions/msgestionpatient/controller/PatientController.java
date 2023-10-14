package com.medilabosolutions.msgestionpatient.controller;


import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all patient-related operations such as retrieving a list of all patients,
 * fetching patient information, updating patient details, and adding a new patient.
 * <p>
 * The controller uses the {@link PatientService} to interact with the underlying data source
 * and perform the necessary CRUD operations.
 * </p>
 *
 * @author Ivano
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RestController
public class PatientController {

    /**
     * Service to handle patient-related operations.
     */
    private final PatientService patientService;

    /**
     * Retrieves a list of all patients in the system.
     *
     * @return List of {@link PatientForSelectionDTO} representing all patients.
     */
    @GetMapping("/patients")
    public List<PatientForSelectionDTO> listOfAllPatients(){
        log.debug("listOfAllPatients() called");

        return patientService.convertPatientsToPatientsDTO(patientService.findAllPatients());
    }

    /**
     * Fetches the details of a specific patient based on the provided patient ID.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return {@link Patient} object containing patient details.
     */
    @GetMapping("/info")
    public Patient getPatientInfo(@RequestParam("patientId") int patientId){

        log.debug("getPatientInfo() called with: {}", patientId);
        return patientService.findPatientById(patientId);
    }

    /**
     * Updates the details of a patient based on the provided patient data.
     *
     * @param patientBean {@link PatientBean} object containing updated patient details.
     */
    @PostMapping ("/update")
    public void updatePatient(@Valid @RequestBody PatientBean patientBean){
        log.debug("updatePatient() called with: {}", patientBean);
        patientService.updatePatient(patientBean);
    }

    /**
     * Adds a new patient to the system.
     *
     * @param patientBean {@link PatientBean} object containing details of the new patient.
     * @throws NoSuchFieldException if a required field is missing.
     */
    @PostMapping ("/add")
    public void addPatient(@Valid @RequestBody PatientBean patientBean) throws NoSuchFieldException {
        log.debug("addPatient() called with: {}", patientBean);
        patientService.addPatient(patientBean);
    }


}
