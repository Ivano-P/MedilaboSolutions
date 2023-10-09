package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.DTO.PatientDTO;
import com.medilabosolutions.msfrontend.DTO.PatientForSelectionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name="MsGateway", url = "localhost:9103")
public interface MsGestionPatientProxy {

    @GetMapping("/gestion/patients")
    List<PatientForSelectionDTO> listOfPatients();

    @GetMapping("/patient/information")
    PatientDTO getPatientInfo(String prenom, String nom, LocalDate ddn);

}
