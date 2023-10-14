package com.medilabosolutions.msgestionhistorique.implimentations;

import com.medilabosolutions.msgestionhistorique.exception.PatientNoteNotFoundException;
import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.repository.PatientNotesRepositoy;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link PatientNotesService} interface.
 * This service provides methods to interact with the patient notes data in the database.
 * It offers functionalities such as retrieving all patient notes, finding notes by patient ID or name,
 * and updating notes for a specific patient.
 *
 * @author Ivano
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Transactional
@Service
public class PatientNotesServiceImpl implements PatientNotesService {

    /** Repository to access patientNotes data in the database. */
    private final PatientNotesRepositoy patientNotesRepositoy;

    /**
     * Retrieves all patient notes from the database.
     *
     * @return List of {@link PatientNotes} representing all the patient notes.
     */
    @Override
    public List<PatientNotes> findAllPatientNotes() {
        log.debug("findAllPatientNotes() called");
        return patientNotesRepositoy.findAll();
    }

    /**
     * Retrieves a specific patient's notes by their ID.
     *
     * @param patientId The ID of the patient whose notes are to be retrieved.
     * @return {@link PatientNotes} containing the patient's notes.
     * @throws PatientNoteNotFoundException if no notes are found for the given patient ID.
     */
    @Override
    public PatientNotes findPatientNotesById(String patientId) {
        log.debug("findPatientNotesById() called with {}", patientId);
        return patientNotesRepositoy.findById(patientId).orElseThrow(PatientNoteNotFoundException::new);
    }

    /**
     * Retrieves a specific patient's notes by their name.
     *
     * @param patient The name of the patient whose notes are to be retrieved.
     * @return {@link PatientNotes} containing the patient's notes.
     * @throws PatientNoteNotFoundException if no notes are found for the given patient name.
     */
    @Override
    public PatientNotes findPatientNotesByPatientName(String patient) {
        log.debug("findPatientNotesByPatientName() called with {}", patient);
        return patientNotesRepositoy.findByPatient(patient)
                .orElseThrow(PatientNoteNotFoundException::new);
    }

    /**
     * Updates the notes for a specific patient by adding a new note.
     *
     * @param patientId The ID of the patient whose notes are to be updated.
     * @param note The new note to be added.
     */
    @Override
    public void updatePatientNotesById(String patientId, String note) {
        log.debug("addNoteToPatientById() called with {}, {}", patientId, note);

        //find patient
        PatientNotes patientNotesToUpdate = findPatientNotesById(patientId);
        //add note to list of patient notes
        patientNotesToUpdate.getNote().add(note);
        //save note
        patientNotesRepositoy.save(patientNotesToUpdate);
    }

    @Override
    public void creatPatientHistory(int patientId, String patientName) {
        log.debug("creatPatientHistoryWithId() called with {}, {}", patientId, patientName);
        PatientNotes createdHistory = new PatientNotes(String.valueOf(patientId), patientName, new ArrayList<>());
        patientNotesRepositoy.save(createdHistory);
    }

}
