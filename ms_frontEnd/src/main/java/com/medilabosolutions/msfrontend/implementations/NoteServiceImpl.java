package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.beans.PatientNotesBean;
import com.medilabosolutions.msfrontend.proxies.MsGatewayProxy;
import com.medilabosolutions.msfrontend.service.NotesService;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link NotesService} interface.
 * This service provides methods to interact with the MsGatewayProxy.
 * It manages operations related to patient notes.
 *
 * @author Ivano
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class NoteServiceImpl implements NotesService {

    private final MsGatewayProxy msGatewayProxy;

    private final PatientService patientService;


    /**
     * Retrieves the notes of a specific patient by their name.
     *
     * @param patientName The name of the patient whose notes are to be retrieved.
     * @return {@link PatientNotesBean} containing the patient's notes.
     */
    @Override
    public PatientNotesBean findPatientNotesByName(String patientName) {
        log.debug("findPatientNotesByName() called with: {}", patientName );
        return msGatewayProxy.findPatientNoteByPatientName(patientName);
    }

    /**
     * Updates the note of a specific patient.
     *
     * @param patientId The ID of the patient whose note is to be updated.
     * @param note The new note to be added to the patient's note history.
     */
    @Override
    public void updatePatientNote(String patientId, String note) {
        log.debug("updatePatientNote() called with: {}, {}", patientId, note );
        //the PatientId in this context refers to PatientId from patientBean

        String patientName = patientService.findPatientById(patientId).getNom();

        //get patientId from patientNote or patientNotesId to not be confused with PatientBeanId
        String patientNoteId = findPatientNotesByName(patientName).getId();
        msGatewayProxy.updatePatientNotesById(patientNoteId, note);
    }
}
