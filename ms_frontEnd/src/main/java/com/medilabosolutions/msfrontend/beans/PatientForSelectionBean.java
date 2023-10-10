package com.medilabosolutions.msfrontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientForSelectionBean {

    private String id;

    String prenom;

    String nom;

    String dateDeNaissance;
}
