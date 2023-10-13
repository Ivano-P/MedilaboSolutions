package com.medilabosolutions.msgestionrisque.model;

/**
 * Represents the various risk levels associated with a patient's health condition.
 * Each risk level is associated with a description that provides a textual representation of the level.
 *
 * @author Ivano
 */
public enum RiskLevel {
    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In Danger"),
    EARLY_ONSET("Early onset");

    /** The textual description of the risk level. */
    private final String description;

    /**
     * Constructs a RiskLevel with the specified description.
     *
     * @param description The textual description of the risk level.
     */
    RiskLevel(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the risk level.
     *
     * @return The textual description of the risk level.
     */
    public String getDescription() {
        return description;
    }
}
