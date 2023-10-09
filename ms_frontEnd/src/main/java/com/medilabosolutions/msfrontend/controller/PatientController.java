package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.DTO.PatientDTO;
import com.medilabosolutions.msfrontend.DTO.PatientForSelectionDTO;
import com.medilabosolutions.msfrontend.proxies.MsGestionPatientProxy;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/")
    public String goToHomePage(){
        log.debug("goToHomePage() called ");

        return "home";
    }

    @GetMapping("/patients")
    public String listPatients(Model model){
        log.debug("listPatients() called with: {} ", model);

       List<PatientForSelectionDTO> patients = patientService.findAllPatients();
       model.addAttribute("patients", patients);

        return "/patients";
    }

    @GetMapping("/informations")
    public String patientInformation(Model model, String prenom, String nom, LocalDate ddn){

        log.debug("patientInformation() called with {}", model);
        PatientDTO chosenPatient = patientService.findPatient(prenom, nom, ddn);

        model.addAttribute("chosenPatient", chosenPatient);
        return "patientDetails";
    }

}
