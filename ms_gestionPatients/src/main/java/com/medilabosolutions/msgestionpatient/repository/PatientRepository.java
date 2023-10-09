package com.medilabosolutions.msgestionpatient.repository;

import com.medilabosolutions.msgestionpatient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findByPrenomAndNom(String prenom, String Nom);
    Optional<Patient> findByPrenomAndNomAndDateDeNaissance(String prenom, String nom, String dateDeNaissance);


}
