package com.medilabosolutions.msgestionhistorique.service;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;

import java.util.List;

public interface PatientNotesService {

    List<PatientNotes> findAllPatientNotes();

    PatientNotes findPatientNotesById(String patientId);

    PatientNotes findPatientNotesByPatientName(String patientName);

    void updatePatientNotesById(String patientId, String note);

    void creatPatientHistory(int patientId, String patientName);
}
