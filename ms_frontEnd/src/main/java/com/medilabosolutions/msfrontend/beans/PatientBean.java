package com.medilabosolutions.msfrontend.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a patient, this PatientBean is used to store Patient information when received from backend microservices
 *
 * @author Ivano
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    /** Unique identifier for the patient. */
    private String id;

    /** First name of the patient. This field is mandatory. */
    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    /** Last name of the patient. This field is mandatory. */
    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    /** Date of birth of the patient in the format YYYY-MM-DD. This field is mandatory. */
    @NotEmpty(message ="La date de naissance est obligatoire")
    String dateDeNaissance;

    /** Gender of the patient. This field is mandatory. */
    @NotEmpty(message ="Le genre est obligatoire")
    String genre;

    /** Postal address of the patient. */
    String adressePostale;

    /** Phone number of the patient. */
    String numeroDeTelephone;
}
