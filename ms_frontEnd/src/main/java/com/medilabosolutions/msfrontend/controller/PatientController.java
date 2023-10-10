package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/updateInfo")
    public String goToUpdatePatientInfoPage(Model model, @RequestParam("patientId") String patientId){

        log.debug("goToUpdatePatientInfoPage() called with {}, {}", model, patientId);

        PatientBean patientBean = patientService.findPatientById(patientId);
        model.addAttribute("patient", patientBean);
        model.addAttribute("patientBean" ,new PatientBean());
        return "updatePatientInfo";
    }

    @PostMapping ("/updatePatient")
    public String updatePatientInfo(Model model, @Valid @ModelAttribute PatientBean patientBean, BindingResult result){
        log.debug("updatePatientInfo() called with {}, {}, {}", model, patientBean, result);
        if(result.hasErrors()){
            return "updatePatientInfo";
        }else{
            patientService.updatePatient(patientBean);
        }
        return "redirect:/informations?patientId=" + patientBean.getId();
    }

    @GetMapping("/addPatient")
    public String goToAddPatientPage(Model model){
        log.debug("addPatient() called with {}", model);
        model.addAttribute("patientBean" ,new PatientBean());
        return "addPatientInfo";
    }

    @PostMapping("/addPatient")
    public String addPatient(Model model, @Valid @ModelAttribute PatientBean patientBean, BindingResult result){
        log.debug("addPatient() called with {}, {}, {}", model, patientBean, result);
        if(result.hasErrors()){
            return "addPatientInfo";
        }else{
            patientService.addPatient(patientBean);
        }
        return "redirect:/patients";
    }

}
