package com.medilabosolutions.msgestionpatient.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientDTO {


    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @NotNull(message ="La date de naissance est obligatoire")
    LocalDate dateDeNaissance;
}
