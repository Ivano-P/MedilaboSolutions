package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.DTO.PatientDTO;
import com.medilabosolutions.msfrontend.DTO.PatientForSelectionDTO;
import com.medilabosolutions.msfrontend.proxies.MsGestionPatientProxy;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientServiceImpl implements PatientService {

    private final MsGestionPatientProxy msGestionPatientProxy;

    @Override
    public List<PatientForSelectionDTO> findAllPatients(){

        log.debug("findAllPatients() called");
        return msGestionPatientProxy.listOfPatients();
    }

    @Override
    public PatientDTO findPatient(String prenom, String nom, LocalDate ddn) {

        log.debug("findPatient() called with {}, {}, {}", prenom, nom, ddn);
        return msGestionPatientProxy.getPatientInfo(prenom, nom, ddn);
    }

}
