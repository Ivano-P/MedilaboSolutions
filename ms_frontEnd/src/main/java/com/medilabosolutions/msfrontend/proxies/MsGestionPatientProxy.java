package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.beans.PatientDtoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="MsGateway", url = "localhost:9103")
public interface MsGestionPatientProxy {

    @GetMapping("/gestion/patients")
    public List<PatientDtoBean> listOfPatients();

}
