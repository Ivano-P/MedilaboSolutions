package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientDtoBean;
import com.medilabosolutions.msfrontend.proxies.MsGestionPatientProxy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final MsGestionPatientProxy msGestionPatientProxy;

    @GetMapping("/")
    public String goToHomePage(){
        return "home";
    }

    @GetMapping("/patients")
    public  String listPatients(Model model){

       List<PatientDtoBean> patients = msGestionPatientProxy.listOfPatients();
       model.addAttribute("patients", patients);

        return "/patients";
    }

}
