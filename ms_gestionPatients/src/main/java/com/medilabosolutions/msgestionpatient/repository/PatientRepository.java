package com.medilabosolutions.msgestionpatient.repository;

import com.medilabosolutions.msgestionpatient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

}
