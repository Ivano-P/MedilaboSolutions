package com.medilabosolutions.msfrontend.implementations;

import com.medilabosolutions.msfrontend.proxies.MsGatewayProxy;
import com.medilabosolutions.msfrontend.service.RiskService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RiskServiceImpl implements RiskService {

    private final MsGatewayProxy msGatewayProxy;

    @Override
    public String evaluatePatientRisk(int patientId) {
        return msGatewayProxy.evaluatePatientRisk(patientId);
    }
}
