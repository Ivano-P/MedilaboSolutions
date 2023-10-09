package com.medilabosolutions.msfrontend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private String id;

    String prenom;

    String nom;

    LocalDate dateDeNaissance;

    String genre;

    String adressePostale;

    String numeroDeTelephone;
}
