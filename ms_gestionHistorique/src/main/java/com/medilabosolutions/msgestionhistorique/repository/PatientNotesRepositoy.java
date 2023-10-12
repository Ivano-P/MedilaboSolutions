package com.medilabosolutions.msgestionhistorique.repository;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientNotesRepositoy extends MongoRepository<PatientNotes, String> {
    Optional<PatientNotes> findByPatient(String patient);

}
