package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.proxies.MsGestionPatientProxy;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PatientServiceImpl implements PatientService {

    private final MsGestionPatientProxy msGestionPatientProxy;

    @Override
    public List<PatientForSelectionBean> findAllPatients(){

        log.debug("findAllPatients() called");
        return msGestionPatientProxy.listOfPatients();
    }


    @Override
    public PatientBean findPatientById(String patientId) {
        log.debug("findPatientById() called with {} ", patientId );
        return msGestionPatientProxy.getUpdatePage(patientId);
    }

    @Override
    public void updatePatient(PatientBean patientDTO) {

        log.debug("updatePatient() called with {} ", patientDTO );
        msGestionPatientProxy.updatePatient(patientDTO);
    }

}
