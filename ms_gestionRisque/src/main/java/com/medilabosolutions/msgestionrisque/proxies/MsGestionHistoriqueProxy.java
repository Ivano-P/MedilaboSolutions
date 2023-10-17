package com.medilabosolutions.msgestionrisque.proxies;

import com.medilabosolutions.msgestionrisque.beans.PatientNotesBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface defines the contract for communication with the ms_gestionHistorique microservice.
 *
 * @author Ivano
 */
@FeignClient(name="MsGestionHistorique", url ="ms-gestionhistorique:9003")
public interface MsGestionHistoriqueProxy {

    /**
     * Retrieves a patient's notes using the patient's ID.
     *
     * @param patientId The ID of the patient whose notes are to be retrieved.
     * @return {@link PatientNotesBean} containing the patient's notes.
     */
    @GetMapping("/noteById")
    PatientNotesBean findPatientNoteById(@RequestParam String patientId);

}
