package com.medilabosolutions.msgestionrisque.beans;

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
    private int id;

    /** First name of the patient. This field is mandatory. */

    String prenom;

    /** Last name of the patient. This field is mandatory. */
    String nom;

    /** Date of birth of the patient in the format YYYY-MM-DD. This field is mandatory. */
    String dateDeNaissance;

    /** Gender of the patient. This field is mandatory. */
    String genre;

    /** Postal address of the patient. */
    String adressePostale;

    /** Phone number of the patient. */
    String numeroDeTelephone;
}
