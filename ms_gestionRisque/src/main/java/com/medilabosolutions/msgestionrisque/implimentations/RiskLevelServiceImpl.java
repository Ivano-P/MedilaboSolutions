package com.medilabosolutions.msgestionrisque.implimentations;

import com.medilabosolutions.msgestionrisque.beans.PatientBean;
import com.medilabosolutions.msgestionrisque.model.RiskLevel;
import com.medilabosolutions.msgestionrisque.proxies.MsGestionHistoriqueProxy;
import com.medilabosolutions.msgestionrisque.proxies.MsGestionPatientsProxy;
import com.medilabosolutions.msgestionrisque.service.RiskLevelService;
import com.medilabosolutions.msgestionrisque.service.TriggerTermService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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


    private final MsGestionHistoriqueProxy msGestionHistoriqueProxy;
    private final MsGestionPatientsProxy msGestionPatientsProxy;
    private final TriggerTermService triggerTermService;

    /**
     * List of trigger terms used to determine the risk level.
     */
    private List<String> triggerTerms;


    /**
     * Constructor for the RiskLevelServiceImpl class.
     * Initializes the TriggerTermService which provides the list of trigger terms.
     *
     * @param msGestionHistoriqueProxy
     * @param msGestionPatientsProxy
     * @param triggerTermService       Service that provides the list of trigger terms.
     */
    @Autowired
    public RiskLevelServiceImpl(MsGestionHistoriqueProxy msGestionHistoriqueProxy, MsGestionPatientsProxy msGestionPatientsProxy, TriggerTermService triggerTermService){
        this.msGestionHistoriqueProxy = msGestionHistoriqueProxy;
        this.msGestionPatientsProxy = msGestionPatientsProxy;
        this.triggerTermService = triggerTermService;
    }

    //for test
    public RiskLevelServiceImpl(MsGestionHistoriqueProxy msGestionHistoriqueProxy, MsGestionPatientsProxy msGestionPatientsProxy, TriggerTermService triggerTermService, List<String> triggerTerms){
        this.msGestionHistoriqueProxy = msGestionHistoriqueProxy;
        this.msGestionPatientsProxy = msGestionPatientsProxy;
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
     * gathers the information necessary to anticipate risk level and call's anticipateRisk() method with these values
     *the value-anticipated risk level is then returned
     *
     * @param patientId unique identifier that represent the patient
     * @return String representing the risk level.
     */
    @Override
    public String getRiskLevel(int patientId) {
        log.debug("getRiskLevel() called with {}", patientId);
        List<String> personHistory = msGestionHistoriqueProxy.findPatientNoteById(String.valueOf(patientId)).getNote();
        PatientBean patient = msGestionPatientsProxy.getPatientInfo(patientId);
        String dateOfBirth = patient.getDateDeNaissance();
        String gender = patient.getGenre();
        return anticipateRisk(personHistory, dateOfBirth, gender);
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
        log.debug("anticipateRisk() called with {}, {}, {}", personHistory, dateOfBirth, gender);
        //default level
        String riskLevel = RiskLevel.NONE.getDescription();

        int triggerTermOccurrences = countTermsOccurrences(triggerTerms, personHistory);
        int age = calculateAge(convertDobStringToLocalDate(dateOfBirth));


        if (triggerTermOccurrences < 2) { //aucune ou un terme déclencheur cas pour un terme.
            return riskLevel; // None
        }

        if(age > 30) {//Patient plus de 30 ans
            if(triggerTermOccurrences >= 2 && triggerTermOccurrences <= 5){ // borderline : patient contient entre deux et cinq déclencheurs et le patient est âgé de plus de 30 ans
                riskLevel = RiskLevel.BORDERLINE.getDescription();
            }else if(triggerTermOccurrences >= 6 && triggerTermOccurrences <= 7) { // in danger : patient a plus de 30 ans, alors il en faudra six ou sept
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            } else if(triggerTermOccurrences >= 8) { // early onset : Si le patient a plus de 30 ans, alors il en faudra huit ou plus.
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            }

        }else if(age < 30 && gender.equals("M")) {//homme moins de 30 ans
            if(triggerTermOccurrences >= 3 && triggerTermOccurrences <= 4){ //in danger : patient est un homme de moins de 30 ans, et a trois termes déclencheurs
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            } else if(triggerTermOccurrences >= 5){ // early onset : patient est un homme de moins de 30 ans, alors au moins cinq termes déclencheurs
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            }

        }else if (age < 30 && gender.equals("F")){ //femme moins de 30 ans
            if(triggerTermOccurrences >= 4 && triggerTermOccurrences <= 6){ //in danger : patient est une femme de moins de 30 ans, et a quatre termes déclencheurs
                riskLevel = RiskLevel.IN_DANGER.getDescription();
            }else if(triggerTermOccurrences >= 7){ // early onset : patient est une femme de moins de 30 ans, alors au moins sept termes déclencheurs
                riskLevel = RiskLevel.EARLY_ONSET.getDescription();
            }

        }

        log.debug( "trigger terms : " + triggerTermOccurrences);
        log.debug("patient age :" + age);
        log.debug("patient gender :" +gender);
        log.debug("risk level :" +riskLevel);
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
        log.debug("convertDobStringToLocalDate() called with {}", dateOfBirth);
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
        log.debug("calculateAge() called with {}", dateOfBirth);
        LocalDate currentDate = LocalDate.now();

        return Period.between(dateOfBirth, currentDate).getYears();
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
        log.debug("countTermsOccurrences() called with {}, {}", triggerTerms, personHistory);
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
