package com.medilabosolutions.msgestionpatient.implimentations;

import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.repository.PatientRepository;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findAllPatients(){

        return patientRepository.findAll();
    }

    @Override
    public List<PatientForSelectionDTO> convertPatientsToPatientsDTO(List<Patient> allPatients){
        List<PatientForSelectionDTO> listOfPatientsDTO = new ArrayList<>();

        for(Patient patient : allPatients){
            listOfPatientsDTO.add(new PatientForSelectionDTO(patient.getPrenom(), patient.getNom(), patient.getDateDeNaissance()));
        }

        return listOfPatientsDTO;
    }

    @Override
    public Patient findPatientInfo(String prenom, String nom, LocalDate ddn) {
        log.info("findPatientInfo() called with {} , {}, {}", prenom, nom, ddn);

        String dateDeNaissance = ddn.toString();
        Optional<Patient> searchedPatient = patientRepository.findByPrenomAndNomAndDateDeNaissance(prenom, nom, dateDeNaissance);

        //TODO: finish this methode
        return null;
    }

}
