package com.medilabosolutions.msgestionrisque.proxies;

import com.medilabosolutions.msgestionrisque.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface defines the contract for communication with the ms_gestionPatient microservice.
 *
 * @author Ivano
 */
@FeignClient(name="MsGestionPatients", url ="ms-gestionpatient:9002")
public interface MsGestionPatientsProxy {

    /**
     * Retrieves a specific patient's details.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return {@link PatientBean} containing the patient's details.
     */
    @GetMapping("/info")
    PatientBean getPatientInfo(@RequestParam("patientId") int patientId);

}
