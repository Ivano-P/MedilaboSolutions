package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.beans.PatientNotesBean;
import com.medilabosolutions.msfrontend.service.NotesService;
import com.medilabosolutions.msfrontend.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller responsible for handling patient-related web requests.
 * Provides endpoints for listing patients, viewing patient details, updating and adding patients.
 *
 * @author Ivano
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class PatientController {

    private final PatientService patientService;
    private final NotesService notesService;

    /**
     * Redirects to the home page.
     *
     * @return The name of the home view.
     */
    @GetMapping("/")
    public String goToHomePage(){
        log.debug("goToHomePage() called ");

        return "home";
    }

    /**
     * Lists all patients.
     *
     * @param model The model to which the list of patients will be added.
     * @return The name of the patients view.
     */
    @GetMapping("/patients")
    public String listPatients(Model model){
        log.debug("listPatients() called with: {} ", model);

       List<PatientForSelectionBean> patients = patientService.findAllPatients();
       model.addAttribute("patients", patients);

        return "patientsList";
    }



    /**
     * Displays information and note history for a specific patient.
     *
     * @param model The model to which the patient details will be added.
     * @param patientId The ID of the patient whose details are to be displayed.
     * @return The notesHistory view.
     */
    @GetMapping("/informations")
    public String patientNoteHistory(Model model,
                                     @RequestParam("patientId") String patientId){

        log.debug("patientNoteHistory() called with {}, {} ", model, patientId);

        PatientBean chosenPatient = patientService.findPatientById(patientId);
        PatientNotesBean patientNotes = notesService.findPatientNotesByName(chosenPatient.getNom());

        model.addAttribute("patient", chosenPatient);
        model.addAttribute("patientNotes", patientNotes);
        return "patientInfo2";
    }




    /**
     * Update personal information for a specific patient.
     *
     * @param model The model to which the patient details will be added.
     * @param patientId The ID of the patient whose details are to be displayed.
     * @return The page with update form for patient
     */
    @GetMapping("/updateInfo")
    public String goToUpdatePatientInfoPage(Model model, @RequestParam("patientId") String patientId){

        log.debug("goToUpdatePatientInfoPage() called with {}, {}", model, patientId);

        PatientBean patientBean = patientService.findPatientById(patientId);
        model.addAttribute("patient", patientBean);
        model.addAttribute("patientBean" ,new PatientBean());
        return "updatePatientInfo";
    }


    /**
     * Update personal information for a specific patient.
     *
     * @param model The model to which the patient details will be added.
     * @param patientBean The object containing patient info for update
     * @param result a {@link BindingResult} used to apply validation on form fields and display error message
     * @return The name of the patient information view or the current updatePatient view if validation isn't passed
     */
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

    /**
     * Add a new patient.
     *
     * @param model The model to which the patient details will be added.
     * @return The page with form to add personal information for new patient
     */
    @GetMapping("/addPatient")
    public String goToAddPatientPage(Model model){
        log.debug("addPatient() called with {}", model);
        model.addAttribute("patientBean" ,new PatientBean());
        return "addPatientInfo";
    }

    /**
     * Add new patient by submitting their personal information
     *
     * @param model The model to which the patient details will be added.
     * @param patientBean The object containing patient info for update
     * @param result a {@link BindingResult} used to apply validation on form fields and display error message
     * @return The list of patients view or the current add patient view if validation isn't passed
     */
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




    @PostMapping("/updateHistory")
    public String updateNoteHistory(@ModelAttribute("patientId") String patientId,
                                    @ModelAttribute("note") String note){

        log.debug("updateNoteHistory() called with {}, {} ", patientId, note);
        notesService.updatePatientNote(patientId, note);
        return "redirect:/informations?patientId=" + patientId;
    }

}
