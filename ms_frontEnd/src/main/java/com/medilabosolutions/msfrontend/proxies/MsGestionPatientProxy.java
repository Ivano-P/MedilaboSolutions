package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="MsGateway", url = "localhost:9103")
public interface MsGestionPatientProxy {

    @GetMapping("/gestion/patients")
    List<PatientForSelectionBean> listOfPatients();


    @GetMapping("/gestion/update")
    PatientBean getUpdatePage(@RequestParam("patientId") String patientId);

    @PostMapping("/gestion/update")
    void updatePatient(@RequestBody PatientBean patientBean);

}
