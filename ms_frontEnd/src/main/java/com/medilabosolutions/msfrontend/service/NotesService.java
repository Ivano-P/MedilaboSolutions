package com.medilabosolutions.msfrontend.service;

import com.medilabosolutions.msfrontend.beans.PatientNotesBean;

public interface NotesService {
    PatientNotesBean findPatientNotesByName(String patientName);

    void updatePatientNote(int patientId, String note);

}
