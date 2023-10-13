package com.medilabosolutions.ms_gestionrisque.implimentations;

import com.medilabosolutions.ms_gestionrisque.model.RiskLevel;
import com.medilabosolutions.ms_gestionrisque.service.RiskLevelService;
import com.medilabosolutions.ms_gestionrisque.service.TriggerTermService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the RiskLevelService interface.
 * This service is responsible for determining the risk level of a patient based on their medical history, age, and gender.
 * The risk levels are determined based on the presence of specific trigger terms in the patient's medical history.
 *
 * @author Ivano
 */
@Log4j2
@Service
public class RiskLevelServiceImpl implements RiskLevelService {

    private final TriggerTermService triggerTermService;

    /**
     * List of trigger terms used to determine the risk level.
     */
    private List<String> triggerTerms;


    /**
     * Constructor for the RiskLevelServiceImpl class.
     * Initializes the TriggerTermService which provides the list of trigger terms.
     *
     * @param triggerTermService Service that provides the list of trigger terms.
     */
    @Autowired
    public RiskLevelServiceImpl(TriggerTermService triggerTermService){
        this.triggerTermService = triggerTermService;
    }

    //for test
    public RiskLevelServiceImpl(TriggerTermService triggerTermService, List<String> triggerTerms){
        this.triggerTerms = triggerTerms;
        this.triggerTermService = triggerTermService;
    }

    /**
     * Initializes the list of trigger terms after the bean is fully constructed.
     * This method ensures that the trigger terms are loaded after the TriggerTermService has been injected.
     */
    @PostConstruct
    public void init() {
        triggerTerms = triggerTermService.getListOfTriggerTerms();
    }


    /**
     * Determines the risk level of a patient based on their medical history, age, and gender.
     *
     * @param personHistory List of medical history notes for the patient.
     * @param dateOfBirth Date of birth of the patient.
     * @param gender Gender of the patient.
     * @return String representing the risk level.
     */
    @Override
    public String anticipateRisk(List<String> personHistory, String dateOfBirth, String gender) {
        //default level
        String riskLevel = RiskLevel.NONE.getDescription();

        int triggerTermOccurrences = countTermsOccurrences(triggerTerms, personHistory);
        int age = calculateAge(convertDobStringToLocalDate(dateOfBirth));

        if (triggerTermOccurrences == 0) {
            return riskLevel; // None
        } else if (triggerTermOccurrences >= 2 && triggerTermOccurrences <= 5 && age > 30) {  // borderline : patient contient entre deux et cinq déclencheurs et le patient est âgé de plus de 30 ans
            riskLevel = RiskLevel.BORDERLINE.getDescription();
        } else if (age < 30) {
            if (gender.equalsIgnoreCase("M") && triggerTermOccurrences >= 3) {  //in danger : patient est un homme de moins de 30 ans, et a trois termes déclencheurs
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            } else if (gender.equalsIgnoreCase("F") && triggerTermOccurrences >= 4) { //in danger : patient est une femme et a moins de 30 ans, il faudra quatre termes déclencheurs
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            }
            if (gender.equalsIgnoreCase("M") && triggerTermOccurrences >= 5) { // early onset : patient est un homme de moins de 30 ans, alors au moins cinq termes déclencheurs
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            } else if (gender.equalsIgnoreCase("F") && triggerTermOccurrences >= 7) { // early onset : patient est une femme et a moins de 30 ans, il faudra au moins sept termes déclencheurs
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            }
        } else if (age > 30) {
            if (triggerTermOccurrences >= 6 && triggerTermOccurrences <= 7) { // in danger : patient a plus de 30 ans, alors il en faudra six ou sept
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            } else if (triggerTermOccurrences >= 8) { // patient a plus de 30 ans, alors il en faudra huit ou plus.
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            }
        }


        return riskLevel;
    }


    /**
     * Converts a date of birth string to a LocalDate object.
     *
     * @param dateOfBirth Date of birth in string format.
     * @return LocalDate representation of the date of birth.
     */
    @Override
    public LocalDate convertDobStringToLocalDate(String dateOfBirth) {
        return LocalDate.parse(dateOfBirth);
    }

    /**
     * Calculates the age of a person based on their date of birth.
     *
     * @param dateOfBirth LocalDate representation of the person's date of birth.
     * @return Age of the person.
     */
    @Override
    public int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - dateOfBirth.getYear(); // Subtract the birth year from the current year

        // If the birthday's month and day are after the current date's month and day, subtract 1 from the age
        if (dateOfBirth.getMonthValue() > currentDate.getMonthValue() ||
                (dateOfBirth.getMonthValue() == currentDate.getMonthValue() && dateOfBirth.getDayOfMonth() > currentDate
                        .getDayOfMonth())) {
            age--;
        }

        return age;
    }

    /**
     * Counts the occurrences of trigger terms in a person's medical history.
     *
     * @param triggerTerms List of trigger terms to search for.
     * @param personHistory List of medical history notes for the patient.
     * @return Total count of trigger term occurrences.
     */
    @Override
    public int countTermsOccurrences(List<String> triggerTerms, List<String> personHistory) {
        int termsCount = 0;
        for (String searchTerm : triggerTerms) {
            String lowerSearchTerm = searchTerm.toLowerCase();
            for (String note : personHistory) {
                String lowerNote = note.toLowerCase();
                int index = 0;
                while ((index = lowerNote.indexOf(lowerSearchTerm, index)) != -1) {
                    termsCount++;
                    index += lowerSearchTerm.length();
                }
            }
        }
        return termsCount;
    }

}
