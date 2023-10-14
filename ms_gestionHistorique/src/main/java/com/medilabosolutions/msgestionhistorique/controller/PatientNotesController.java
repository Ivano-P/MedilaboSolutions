package com.medilabosolutions.msgestionhistorique.controller;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling patient notes-related web requests.
 * Provides endpoints for retrieving and updating patient notes.
 *
 * @author Ivano
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RestController
public class PatientNotesController {

    private final PatientNotesService patientNotesService;

    /**
     * Retrieves a list of all patient notes.
     *
     * @return List of {@link PatientNotes} representing all the patient notes.
     */
    @GetMapping("/notes")
    public List<PatientNotes> getAllPatientNotes(){
        log.debug("getAllPatientNotes() called");
        return patientNotesService.findAllPatientNotes();
    }

    /**
     * Retrieves a specific patient's notes using the patient's ID.
     *
     * @param patientId The ID of the patient whose notes are to be retrieved.
     * @return {@link PatientNotes} containing the patient's notes.
     */
    @GetMapping("/noteById")
    public PatientNotes findPatientNoteById(@RequestParam String patientId){
        log.debug("getPatientNoteById() called with {}", patientId);
        return patientNotesService.findPatientNotesById(patientId);
    }

    /**
     * Retrieves a patient's notes using the patient's name.
     *
     * @param patientName The name of the patient whose notes are to be retrieved.
     * @return {@link PatientNotes} containing the patient's notes.
     */
    @GetMapping("/noteByName")
    public PatientNotes findPatientNoteByPatientName(@RequestParam String patientName){
        log.debug("findPatientNoteByPatientName() called with {}", patientName);
        return patientNotesService.findPatientNotesByPatientName(patientName);
    }

    /**
     * Updates the notes of a specific patient using the patient's ID.
     *
     * @param patientId The ID of the patient whose notes are to be updated.
     * @param note The new note to be added to the patient's note history.
     */
    @PostMapping("/updateNotes")
    public void updatePatientNotesById(@RequestParam String patientId, @RequestParam String note){
        log.debug("addNoteToPatientNotesById called with {}, {}", patientId, note);
        patientNotesService.updatePatientNotesById(patientId, note);
    }

    @PostMapping("/addNew")
    public void creatPatientHistory(@RequestParam int patientId, @RequestParam String patientName){
        log.debug("creatPatientHistoryWithId called with {}, {}", patientId, patientName);
        patientNotesService.creatPatientHistory(patientId, patientName);
    }

}
