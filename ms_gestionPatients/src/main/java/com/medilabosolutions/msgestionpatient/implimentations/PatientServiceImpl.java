package com.medilabosolutions.msgestionpatient.implimentations;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.exceptions.PatientNotFoundExcetion;
import com.medilabosolutions.msgestionpatient.model.Genre;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.proxies.MsGestionHistoriqueProxy;
import com.medilabosolutions.msgestionpatient.repository.PatientRepository;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link PatientService} interface.
 * This service provides methods to interact with the patient data in the database.
 *
 * @author [Ivano]
 * @version 1.0
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Transactional
@Service
public class PatientServiceImpl implements PatientService {

    /** Repository to access patient data in the database. */
    private final PatientRepository patientRepository;

    private final MsGestionHistoriqueProxy msGestionHistoriqueProxy;

    /**
     * Retrieves all patients from the database.
     *
     * @return List of all patients.
     */
    @Override
    public List<Patient> findAllPatients(){
        return patientRepository.findAll();
    }

    /**
     * Converts a list of {@link Patient} objects to a list of {@link PatientForSelectionDTO} objects.
     * This is useful for creating a selection list with only the essential patient details.
     *
     * @param allPatients List of patients to convert.
     * @return List of converted patient DTOs.
     */
    @Override
    public List<PatientForSelectionDTO> convertPatientsToPatientsDTO(List<Patient> allPatients){
        List<PatientForSelectionDTO> listOfPatientsDTO = new ArrayList<>();

        for(Patient patient : allPatients){
            listOfPatientsDTO.add(new PatientForSelectionDTO(patient.getId(), patient.getPrenom(), patient.getNom(), patient.getDateDeNaissance()));
        }

        return listOfPatientsDTO;
    }

    /**
     * Searches for a patient in the database using the provided ID.
     *
     * @param patientId ID of the patient to search for.
     * @return The found patient.
     * @throws PatientNotFoundExcetion if no patient is found with the provided ID.
     */
    @Override
    public Patient findPatientById(int patientId) {
        log.debug("findPatientById() called with: {}", patientId);
        return patientRepository.findById(patientId).orElseThrow(PatientNotFoundExcetion::new);
    }



    /**
     * Updates the details of a patient in the database.
     *
     * @param patientBean The patient with updated details.
     */
    @Override
    public void updatePatient(PatientBean patientBean) {

        log.debug("updatePatient() called with: {}", patientBean);
        Patient patient = convertPatientBeanToPatient(patientBean);
        Patient patientToUpdate = findPatientById(patient.getId());

        //copy all properties from `patient` to `patientToUpdate`
        BeanUtils.copyProperties(patient, patientToUpdate);
        patientRepository.save(patientToUpdate);
    }

    @Override
    public void addPatient(PatientBean patientBean) throws NoSuchFieldException {
        log.debug("addPatient() called with: {}", patientBean);

        Patient patient = new Patient();
        patient.setPrenom(patientBean.getPrenom());
        patient.setNom(patientBean.getNom());
        patient.setDateDeNaissance(patientBean.getDateDeNaissance());
        patient.setGenre(Genre.valueOf(patientBean.getGenre()));
        patient.setAdressePostale(patientBean.getAdressePostale());
        patient.setNumeroDeTelephone(patientBean.getNumeroDeTelephone());
        patientRepository.save(patient);

        creatNewNoteHistoryForNewPatient(patientBean);
    }

    private void creatNewNoteHistoryForNewPatient(PatientBean patientBean) {
        log.debug("creatNewNoteHistoryForNewPatient() called with {}", patientBean);
       Optional<Patient>  patientOptional = patientRepository.findByPrenomAndNomAndDateDeNaissance(patientBean
               .getPrenom(), patientBean.getNom(), patientBean.getDateDeNaissance());

       Patient searchedPatient = patientOptional.orElseThrow(PatientNotFoundExcetion::new);

        msGestionHistoriqueProxy.creatPatientHistory(searchedPatient.getId(), searchedPatient.getNom());
    }

    private Patient convertPatientBeanToPatient(PatientBean patientBean){

        return new Patient(patientBean.getId(), patientBean.getPrenom(), patientBean.getNom(), patientBean
                .getDateDeNaissance(), Genre.valueOf(patientBean.getGenre()), patientBean
                .getAdressePostale(), patientBean.getNumeroDeTelephone());
    }

}
