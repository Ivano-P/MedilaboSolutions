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
@FeignClient(name="MsGateWay", url = "localhost:9103")
public interface MsGatewayProxy {

    /**
     * Retrieves a list of patients for selection.
     *
     * @return List of {@link PatientForSelectionBean} representing the patients.
     */
    @GetMapping("/gestion/patients")
    List<PatientForSelectionBean> listOfPatients();


    /**
     * Retrieves a specific patient's details.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return {@link PatientBean} containing the patient's details.
     */
    @GetMapping("/gestion/info")
    PatientBean getPatientInfo(@RequestParam("patientId") int patientId);

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


    /**
     * Retrieves a patient's notes using the patient's ID.
     *
     * @param patientId The ID of the patient whose notes are to be retrieved.
     * @return {@link PatientNotesBean} containing the patient's notes.
     */
    @GetMapping("/historique/noteById")
    PatientNotesBean findPatientNoteById(@RequestParam String patientId);

    /**
     * Retrieves a patient's notes using the patient's name.
     *
     * @param patientName The name of the patient whose notes are to be retrieved.
     * @return {@link PatientNotesBean} containing the patient's notes.
     */
    @GetMapping("/historique/noteByName")
    PatientNotesBean findPatientNoteByPatientName(@RequestParam String patientName);

    /**
     * Updates the notes of a specific patient using the patient's ID.
     *
     * @param patientId The ID of the patient whose notes are to be updated.
     * @param note The new note to be added to the patient's note history.
     */
    @PostMapping("/historique/updateNotes")
    void updatePatientNotesById(@RequestParam String patientId, @RequestParam String note);

    @GetMapping("/risque/evaluate")
    String evaluatePatientRisk(@RequestParam int patientId);

}
