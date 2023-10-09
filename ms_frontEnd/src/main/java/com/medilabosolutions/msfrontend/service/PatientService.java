package com.medilabosolutions.msfrontend.service;

import com.medilabosolutions.msfrontend.DTO.PatientDTO;
import com.medilabosolutions.msfrontend.DTO.PatientForSelectionDTO;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {
    List<PatientForSelectionDTO> findAllPatients();
    PatientDTO findPatient(String prenom, String nom, LocalDate ddn);
}
