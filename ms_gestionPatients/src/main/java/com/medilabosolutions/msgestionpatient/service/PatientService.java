package com.medilabosolutions.msgestionpatient.service;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     *Anything that returns a list of all patients
     *
     * @return list of all patients
     */
    List<Patient> findAllPatients();

    List<PatientForSelectionDTO> convertPatientsToPatientsDTO(List<Patient> allPatients);


    void updatePatient(PatientBean patientBean);

    Patient findPatientById(String patientId);

    void addPatient(PatientBean patientBean);
}
