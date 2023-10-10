package com.medilabosolutions.msgestionpatient.implimentations;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.exceptions.PatientNotFoundExcetion;
import com.medilabosolutions.msgestionpatient.model.Genre;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.repository.PatientRepository;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Patient findPatientById(String patientId) {
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

        //convert patientBean info Patient
        Patient patient = new Patient(patientBean.getId(), patientBean.getPrenom(), patientBean.getNom(), patientBean
                .getDateDeNaissance(), Genre.valueOf(patientBean.getGenre()), patientBean
                .getAdressePostale(), patientBean.getNumeroDeTelephone());


        Patient patientToUpdate = findPatientById(patient.getId());

        //copy all properties from `patient` to `patientToUpdate`
        BeanUtils.copyProperties(patient, patientToUpdate);

        patientRepository.save(patientToUpdate);
    }

}
