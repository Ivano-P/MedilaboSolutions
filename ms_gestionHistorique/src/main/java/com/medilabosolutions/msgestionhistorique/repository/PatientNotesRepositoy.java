package com.medilabosolutions.msgestionhistorique.repository;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientNotesRepositoy extends MongoRepository<PatientNotes, String> {

}
