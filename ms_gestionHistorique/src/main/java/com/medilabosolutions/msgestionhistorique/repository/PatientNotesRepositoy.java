package com.medilabosolutions.msgestionhistorique.repository;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and manipulating the patient notes data in the MongoDB database.
 * This interface extends the {@link MongoRepository} interface, providing CRUD operations for the {@link PatientNotes} model.
 * The repository is associated with the "patientNotes" collection in the MongoDB database.
 *
 * @author Ivano
 */
@Repository
public interface PatientNotesRepositoy extends MongoRepository<PatientNotes, String> {

    /**
     * Retrieves the patient notes associated with a specific patient's name.
     *
     * @param patient The name of the patient whose notes are to be retrieved.
     * @return An {@link Optional} containing the {@link PatientNotes} if found, or an empty Optional if not found.
     */
    Optional<PatientNotes> findByPatient(String patient);

}
