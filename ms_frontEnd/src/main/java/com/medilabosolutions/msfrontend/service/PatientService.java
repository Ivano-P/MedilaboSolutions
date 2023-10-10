package com.medilabosolutions.msfrontend.service;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {
    List<PatientForSelectionBean> findAllPatients();

    void updatePatient(PatientBean patientBean);

    PatientBean findPatientById(String patientId);

    void addPatient(PatientBean patientBean);
}
