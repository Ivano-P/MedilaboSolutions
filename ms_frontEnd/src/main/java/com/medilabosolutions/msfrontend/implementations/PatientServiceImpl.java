package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.proxies.MsGatewayProxy;
import com.medilabosolutions.msfrontend.service.NotesService;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link PatientService} interface.
 * This service provides methods to interact with the MsGatewayProxy.
 *
 * @author Ivano
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PatientServiceImpl implements PatientService {

    private final MsGatewayProxy msGatewayProxy;

    /**
     * Retrieves a list of all patients for selection.
     *
     * @return List of {@link PatientForSelectionBean} representing the patients.
     */
    @Override
    public List<PatientForSelectionBean> findAllPatients(){

        log.debug("findAllPatients() called");
        return msGatewayProxy.listOfPatients();
    }


    /**
     * Retrieves details of a specific patient by their ID.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return {@link PatientBean} containing the patient's details.
     */
    @Override
    public PatientBean findPatientById(int patientId) {
        log.debug("findPatientById() called with {} ", patientId );
        return msGatewayProxy.getPatientInfo(patientId);
    }

    /**
     * Adds a new patient to the system.
     *
     * @param patientBean The {@link PatientBean} containing the details of the new patient.
     */
    @Override
    public void addPatient(PatientBean patientBean) {
        log.debug("addPatientt() called with {} ", patientBean);
        msGatewayProxy.addPatient(patientBean);
    }



    /**
     * Updates the details of a specific patient.
     *
     * @param patientBean The {@link PatientBean} containing the updated details of the patient.
     */
    @Override
    public void updatePatient(PatientBean patientBean) {

        log.debug("updatePatient() called with {} ", patientBean);
        msGatewayProxy.updatePatient(patientBean);
    }

}
