package com.medilabosolutions.msgestionhistorique.controller;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RestController
public class PatientNotesController {

    private final PatientNotesService patientNotesService;

    @GetMapping("/notes")
    public List<PatientNotes> getAllPatientNotes(){
        log.debug("getAllPatientNotes() called");
        return patientNotesService.findAllPatientNotes();
    }

    @GetMapping("/noteById")
    public PatientNotes findPatientNoteById(@RequestParam String patientId){
        log.debug("getPatientNoteById() called with {}", patientId);
        return patientNotesService.findPatientNotesById(patientId);
    }

    @GetMapping("/noteByName")
    public PatientNotes findPatientNoteByPatientName(@RequestParam String patientName){
        log.debug("findPatientNoteByPatientName() called with {}", patientName);
        return patientNotesService.findPatientNotesByPatientName(patientName);
    }

    @PostMapping("/updateNotes")
    public void updatePatientNotesById(@RequestParam String patientId, @RequestParam String note){
        log.debug("addNoteToPatientNotesById called with {}, {}", patientId, note);
        patientNotesService.updatePatientNotesById(patientId, note);
    }

}
