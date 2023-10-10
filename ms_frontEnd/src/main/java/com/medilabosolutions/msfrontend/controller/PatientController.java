package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
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

       List<PatientForSelectionBean> patients = patientService.findAllPatients();
       model.addAttribute("patients", patients);

        return "/patients";
    }

    @GetMapping("/informations")
    public String patientInformation(Model model,
                                     @RequestParam("patientId") String patientId){

        log.debug("patientInformation() called with {}, {} ", model, patientId);
        PatientBean chosenPatient = patientService.findPatientById(patientId);
        model.addAttribute("patient", chosenPatient);
        return "patientInfo";
    }

    @GetMapping("/majinfo")
    public String goToUpdatePatientInfoPage(Model model, @RequestParam("patientId") String patientId){

        log.debug("goToUpdatePatientInfoPage() called with {}, {}", model, patientId);

        PatientBean patientDTO = patientService.findPatientById(patientId);
        model.addAttribute("patient", patientDTO);
        return "updatePatientInfo";
    }

    @PostMapping("/majInfoPatient")
    public String updatePatientInfo(Model model, @ModelAttribute PatientBean patientDTO){

        log.debug("updatePatientInfo() called with {}, {}", model, patientDTO);
        patientService.updatePatient(patientDTO);
        return "redirect:/informations?patientId=" + patientDTO.getId();
    }

}
