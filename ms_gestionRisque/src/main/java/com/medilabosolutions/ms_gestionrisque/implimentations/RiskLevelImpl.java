package com.medilabosolutions.ms_gestionrisque.implimentations;

import com.medilabosolutions.ms_gestionrisque.service.RiskLevelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RiskLevelImpl implements RiskLevelService {

    @Override
    public String anticipateRisk(List<String> personHistory) {
        return null;
    }


}
