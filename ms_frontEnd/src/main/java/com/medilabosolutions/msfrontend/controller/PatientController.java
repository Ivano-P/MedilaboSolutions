package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientDtoBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PatientController {

    @GetMapping("/patients")
    public  String listPatients(Model model){
        //Mock Patient
        PatientDtoBean patientDto = new PatientDtoBean("ivano", "petty",
                LocalDate.of(2001, 01, 01));

        PatientDtoBean patientDto2 = new PatientDtoBean("meryl", "cavil",
                LocalDate.of(2001, 01, 01));

        PatientDtoBean patientDto3 = new PatientDtoBean("aaron", "petty",
                LocalDate.of(2001, 01, 01));

       List<PatientDtoBean> patients = List.of(patientDto, patientDto2, patientDto3);
       model.addAttribute("patients", patients);

        return "/patients";
    }

}
