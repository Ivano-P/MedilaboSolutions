package com.medilabosolutions.msgestionrisque.controller;

import com.medilabosolutions.msgestionrisque.service.RiskLevelService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * This controller handles the risk evaluation for a patient based on their medical history,
 * date of birth, and gender. It provides an endpoint to fetch the anticipated risk level.
 *
 * @author Ivano
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class RiskLevelController {

    /** Service to handle risk level calculations. */
    private final RiskLevelService riskLevelService;

    /**
     * Evaluates the risk level of a patient based on their PatientId.
     *
     * @param patientId a unique id that represents the patient in db.
     * @return A string representing the anticipated risk level for the patient.
     */
    @GetMapping("/evaluate")
    String evaluatePatientRisk(@RequestParam ("patientId") int patientId){
        log.debug("evaluatePatientRisk() called with {}", patientId);
        return riskLevelService.getRiskLevel(patientId);
    }

}
