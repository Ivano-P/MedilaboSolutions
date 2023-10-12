package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.beans.PatientNotesBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy interface for interacting with backend microservices through the MsGateway.
 * This interface defines the contract for communication with the MsGateway microservice.
 *
 * @author Ivano
 */
@FeignClient(name="sGestionPatientProxy", url = "localhost:9103")
public interface MsGatewayProxy {

    /**
     * Retrieves a list of patients for selection.
     *
     * @return List of {@link PatientForSelectionBean} representing the patients.
     */
    @GetMapping("/gestion/patients")
    List<PatientForSelectionBean> listOfPatients();

    /**
     * Retrieves a specific patient's details for updating.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return {@link PatientBean} containing the patient's details.
     */
    @GetMapping("/gestion/update")
    PatientBean getUpdatePage(@RequestParam("patientId") String patientId);

    /**
     * Updates the details of a specific patient.
     *
     * @param patientBean The {@link PatientBean} containing the updated details of the patient.
     */
    @PostMapping ("/gestion/update")
    void updatePatient(@RequestBody PatientBean patientBean);

    /**
     * Adds a new patient to the system.
     *
     * @param patientBean The {@link PatientBean} containing the details of the new patient.
     */
    @PostMapping("/gestion/add")
    void addPatient(@RequestBody PatientBean patientBean);


    @GetMapping("/historique/noteById")
    PatientNotesBean findPatientNoteById(@RequestParam String patientId);

    @GetMapping("/historique/noteByName")
    PatientNotesBean findPatientNoteByPatientName(@RequestParam String patientName);

    @PostMapping("/historique/updateNotes")
    void updatePatientNotesById(@RequestParam String patientId, @RequestParam String note);

}
