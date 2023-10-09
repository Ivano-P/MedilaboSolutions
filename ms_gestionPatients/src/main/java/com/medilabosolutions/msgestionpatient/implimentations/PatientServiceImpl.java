package com.medilabosolutions.msgestionpatient.implimentations;

import com.medilabosolutions.msgestionpatient.dto.PatientDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.repository.PatientRepository;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findAllPatients(){

        return patientRepository.findAll();
    }

    @Override
    public List<PatientDTO> convertPatientsToPatientsDTO(List<Patient> allPatients){
        List<PatientDTO> listOfPatientsDTO = new ArrayList<>();

        for(Patient patient : allPatients){
            listOfPatientsDTO.add(new PatientDTO(patient.getPrenom(), patient.getNom(), patient.getDateDeNaissance()));
        }

        return listOfPatientsDTO;
    }

}
