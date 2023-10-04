package com.medilabosolutions.msfrontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientDtoBean {

    String prenom;

    String nom;

    LocalDate dateDeNaissance;
}
