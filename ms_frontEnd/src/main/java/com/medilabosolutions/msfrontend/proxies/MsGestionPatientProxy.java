package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy interface for interacting with the MsGestionPatient service through the MsGateway.
 * This interface defines the contract for communication with the MsGestionPatient service.
 *
 * @author Ivano
 */
@FeignClient(name="MsGateway", url = "localhost:9103")
public interface MsGestionPatientProxy {

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

}
