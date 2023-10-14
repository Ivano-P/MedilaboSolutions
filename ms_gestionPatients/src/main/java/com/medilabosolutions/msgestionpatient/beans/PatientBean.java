package com.medilabosolutions.msgestionpatient.beans;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    private int id;

    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @NotEmpty(message ="La date de naissance est obligatoire")
    String dateDeNaissance;

    @NotNull(message ="Le genre est obligatoire")
    String genre;

    String adressePostale;

    String numeroDeTelephone;

}
