package com.medilabosolutions.msgestionhistorique.implimentations;

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
        return patientNotesRepositoy.findAll();
    }
}
