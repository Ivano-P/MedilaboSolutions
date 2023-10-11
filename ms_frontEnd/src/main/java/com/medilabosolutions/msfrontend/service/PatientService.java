package com.medilabosolutions.msfrontend.service;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;

import java.util.List;

/**
 * Service interface for managing patients.
 * Provides methods for retrieving, updating, and adding patients.
 *
 * @author Ivano
 */
public interface PatientService {

    /**
     * Retrieves a list of all patients.
     *
     * @return A list of {@link PatientForSelectionBean} representing all patients.
     */
    List<PatientForSelectionBean> findAllPatients();


    /**
     * Updates the details of a given patient.
     *
     * @param patientBean The patient details to be updated.
     */
    void updatePatient(PatientBean patientBean);


    /**
     * Retrieves details of a specific patient by their ID.
     *
     * @param patientId The ID of the patient to be retrieved.
     * @return A {@link PatientBean} representing the patient details.
     */
    PatientBean findPatientById(String patientId);


    /**
     * Adds a new patient.
     *
     * @param patientBean The details of the patient to be added.
     */
    void addPatient(PatientBean patientBean);
}
