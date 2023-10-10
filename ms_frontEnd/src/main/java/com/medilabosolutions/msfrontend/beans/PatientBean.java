package com.medilabosolutions.msfrontend.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    private String id;

    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @NotEmpty(message ="La date de naissance est obligatoire")
    String dateDeNaissance;

    @NotEmpty(message ="Le genre est obligatoire")
    String genre;

    String adressePostale;

    String numeroDeTelephone;
}
