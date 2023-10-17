package com.medilabosolutions.msgestionpatient.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="MsGestionHistorique", url ="ms-gestionhistorique:9003")
public interface MsGestionHistoriqueProxy {
    @PostMapping("/addNew")
    void creatPatientHistory(@RequestParam int patientId, @RequestParam String patientName);
}

