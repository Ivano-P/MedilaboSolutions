package com.medilabosolutions.ms_gestionrisque.service;

import java.util.List;

/**
 * Interface for services that provide functionality related to trigger terms.
 * Trigger terms are specific terms or keywords used for risk assessment in the context of medical history.
 * Implementations of this interface should provide methods to retrieve and manage these trigger terms.
 *
 * @author Ivano
 */
public interface TriggerTermService {

    /**
     * Retrieves the list of trigger terms used for risk assessment.
     *
     * @return List of trigger terms.
     */
    List<String> getListOfTriggerTerms();
}
