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

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Transactional
@Service
public class PatientNotesServiceImpl implements PatientNotesService {

    /** Repository to access patientNotes data in the database. */
    private final PatientNotesRepositoy patientNotesRepositoy;

    @Override
    public List<PatientNotes> findAllPatientNotes() {
        log.debug("findAllPatientNotes() called");
        return patientNotesRepositoy.findAll();
    }

    @Override
    public PatientNotes findPatientNotesById(String patientId) {
        log.debug("findPatientNotesById() called with {}", patientId);
        return patientNotesRepositoy.findById(patientId).orElseThrow(PatientNoteNotFoundException::new);
    }

    @Override
    public PatientNotes findPatientNotesByPatientName(String patient) {
        log.debug("findPatientNotesByPatientName() called with {}", patient);
        return patientNotesRepositoy.findByPatient(patient)
                .orElseThrow(PatientNoteNotFoundException::new);
    }

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

}
