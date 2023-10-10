package com.medilabosolutions.msfrontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    private String id;

    String prenom;

    String nom;

    String dateDeNaissance;

    String genre;

    String adressePostale;

    String numeroDeTelephone;
}
