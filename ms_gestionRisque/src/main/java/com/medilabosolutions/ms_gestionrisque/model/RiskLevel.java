package com.medilabosolutions.ms_gestionrisque.model;

public enum RiskLevel {
    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In Danger"),
    EARLY_ONSET("Early onset");

    private final String description;

    RiskLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
