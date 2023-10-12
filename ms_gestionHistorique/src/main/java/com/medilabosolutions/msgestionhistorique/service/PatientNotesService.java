package com.medilabosolutions.msgestionhistorique.service;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;

import java.util.List;

public interface PatientNotesService {

    List<PatientNotes> findAllPatientNotes();

}
