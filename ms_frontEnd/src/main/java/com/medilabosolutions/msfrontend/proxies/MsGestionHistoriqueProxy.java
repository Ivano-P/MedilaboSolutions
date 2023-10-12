package com.medilabosolutions.msfrontend.proxies;

import com.medilabosolutions.msfrontend.beans.PatientNotesBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="MsGateway", url = "localhost:9103")
public interface MsGestionHistoriqueProxy {

    @GetMapping("/historique/noteById")
    PatientNotesBean findPatientNoteById(@RequestParam String patientId);

    @GetMapping("/historique/noteByName")
    PatientNotesBean findPatientNoteByPatientName(@RequestParam String patientName);

    @PostMapping("/historique/updateNotes")
    void updatePatientNotesById(@RequestParam String patientId, @RequestParam String note);

    /*
    @GetMapping("/gestion/patients")
    List<PatientForSelectionBean> listOfPatients();
     */
}
