package com.medilabosolutions.ms_gestionrisque.controller;

import com.medilabosolutions.ms_gestionrisque.service.RiskLevelService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class RiskLevelController {

    private final RiskLevelService riskLevelService;

    @GetMapping("/risk")
    public String evaluateRiskLevel(@RequestParam("history") List<String> personHistory){
        log.debug("evaluateRiskLevel() called with {}", personHistory);
        return riskLevelService.anticipateRisk(personHistory);
    }

}
