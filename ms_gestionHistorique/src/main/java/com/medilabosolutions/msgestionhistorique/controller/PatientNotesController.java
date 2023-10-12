package com.medilabosolutions.msgestionhistorique.controller;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
