package com.medilabosolutions.msfrontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Bean representing a patient for selection purposes, it's a simpler version of PatientBean used for displaying in list
 * Contains basic patient details such as ID, first name, last name, and date of birth.
 *
 * @author Ivano
 */
@Data
@AllArgsConstructor
public class PatientForSelectionBean {

    /** The unique identifier for the patient. */
    private int id;

    /** The first name of the patient. */
    String prenom;

    /** The last name of the patient. */
    String nom;

    /** The date of birth of the patient. */
    String dateDeNaissance;
}
