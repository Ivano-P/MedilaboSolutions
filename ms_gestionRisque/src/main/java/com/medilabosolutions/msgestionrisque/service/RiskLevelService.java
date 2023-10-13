package com.medilabosolutions.ms_gestionrisque.service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for determining the risk level of a patient based on their medical history, age, and gender.
 * The risk levels are determined based on the presence of specific trigger terms in the patient's medical history.
 *
 * @author Ivano
 */
public interface RiskLevelService {

    /**
     * Determines the risk level of a patient based on their medical history, age, and gender.
     *
     * @param personHistory List of medical history notes for the patient.
     * @param dateOfBirth Date of birth of the patient.
     * @param gender Gender of the patient.
     * @return String representing the risk level.
     */
    String anticipateRisk(List<String> personHistory, String dateOfBirth, String gender);

    /**
     * Converts a date of birth string to a LocalDate object.
     *
     * @param dateOfBirth Date of birth in string format.
     * @return LocalDate representation of the date of birth.
     */
    LocalDate convertDobStringToLocalDate(String dateOfBirth);

    /**
     * Calculates the age of a person based on their date of birth.
     *
     * @param dateOfBirth LocalDate representation of the person's date of birth.
     * @return Age of the person.
     */
    int calculateAge(LocalDate dateOfBirth);

    /**
     * Counts the occurrences of trigger terms in a person's medical history.
     *
     * @param triggerTerms List of trigger terms to search for.
     * @param personHistory List of medical history notes for the patient.
     * @return Total count of trigger term occurrences.
     */
    int countTermsOccurrences(List<String> triggerTerms, List<String> personHistory);

}
