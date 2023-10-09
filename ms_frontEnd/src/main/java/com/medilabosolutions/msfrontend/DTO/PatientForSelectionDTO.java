package com.medilabosolutions.msfrontend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientForSelectionDTO {

    String prenom;

    String nom;

    LocalDate dateDeNaissance;
}
