package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.beans.PatientNotesBean;
import com.medilabosolutions.msfrontend.proxies.MsGatewayProxy;
import com.medilabosolutions.msfrontend.service.NotesService;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class NoteServiceImpl implements NotesService {

    private final MsGatewayProxy msGatewayProxy;

    private final PatientService patientService;

    @Override
    public PatientNotesBean findPatientNotesByName(String patientName) {
        log.debug("findPatientNotesByName() called with: {}", patientName );
        return msGatewayProxy.findPatientNoteByPatientName(patientName);
    }

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
