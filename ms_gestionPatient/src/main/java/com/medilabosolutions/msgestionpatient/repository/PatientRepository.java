package com.medilabosolutions.msgestionpatient.repository;

import com.medilabosolutions.msgestionpatient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {

}
