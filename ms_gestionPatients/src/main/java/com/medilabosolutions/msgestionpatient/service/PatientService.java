package com.medilabosolutions.msgestionpatient.service;

import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {

    /**
     *Anything that returns a list of all patients
     *
     *
     * @return list of all patients
     */
    List<Patient> findAllPatients();

    List<PatientForSelectionDTO> convertPatientsToPatientsDTO(List<Patient> allPatients);

    Patient findPatientInfo(String prenom, String nom, LocalDate ddn);
}
