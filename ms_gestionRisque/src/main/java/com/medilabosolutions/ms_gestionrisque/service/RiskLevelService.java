package com.medilabosolutions.ms_gestionrisque.service;

import java.util.List;

public interface RiskLevelService {
    String anticipateRisk(List<String> personHistory);

}
