package com.medilabosolutions.msgestionpatient.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientForSelectionDTO {

    @NotEmpty(message ="identifiant obligatoire")
    private String id;

    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @NotEmpty(message ="La date de naissance est obligatoire")
    String dateDeNaissance;
}
