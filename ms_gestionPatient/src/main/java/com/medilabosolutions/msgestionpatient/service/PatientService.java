package com.medilabosolutions.msgestionpatient.service;

import com.medilabosolutions.msgestionpatient.dto.PatientDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     *Anything that returns a list of all patients
     *
     *
     * @return list of all patients
     */
    List<Patient> findAllPatients();

    List<PatientDTO> convertPatientsToPatientsDTO(List<Patient> allPatients);
}
